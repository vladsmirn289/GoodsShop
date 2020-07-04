package com.shop.GoodsShop.Controller;

import com.shop.GoodsShop.Model.*;
import com.shop.GoodsShop.Service.ClientItemService;
import com.shop.GoodsShop.Service.ClientService;
import com.shop.GoodsShop.Service.OrderService;
import com.shop.GoodsShop.Utils.ValidateUtil;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Set;

@Controller
@RequestMapping("/order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private ClientService clientService;
    private OrderService orderService;
    private ClientItemService clientItemService;

    @Autowired
    public void setClientService(ClientService clientService) {
        logger.debug("Setting clientService");
        this.clientService = clientService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        logger.debug("Setting orderService");
        this.orderService = orderService;
    }

    @Autowired
    public void setClientItemService(ClientItemService clientItemService) {
        logger.debug("Setting clientItemService");
        this.clientItemService = clientItemService;
    }

    @GetMapping
    @Transactional
    @PreAuthorize("isAuthenticated()")
    public String clientOrders(@AuthenticationPrincipal Client client,
                               Model model) {
        logger.info("Called clientOrders method");
        Client persistentClient = clientService.findByLogin(client.getLogin());
        Hibernate.initialize(persistentClient.getOrders());
        model.addAttribute("orders", persistentClient.getOrders());

        return "ordersList";
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public String concreteClientOrder(@PathVariable("id") Long orderId,
                                      Model model) {
        logger.info("Called concreteClientOrder method");
        Order order = orderService.findById(orderId);
        model.addAttribute("order", order);

        return "concreteOrder";
    }

    @GetMapping("/checkout")
    @PreAuthorize("isAuthenticated()")
    @Transactional
    public String checkoutAllItems(@AuthenticationPrincipal Client client,
                                   Model model,
                                   HttpServletRequest request) {
        logger.info("Called checkoutAllItems method");
        Client persistentClient = clientService.findByLogin(client.getLogin());
        Set<ClientItem> basket = persistentClient.getBasket();
        double generalPrice = basket
                .stream()
                .map(item -> item.getItem().getPrice() * item.getQuantity())
                .reduce(Double::sum).orElse(0D);

        model.addAttribute("client", client);
        model.addAttribute("generalPrice", generalPrice);

        request.getSession().setAttribute("orderedItems", basket);

        return "checkoutPage";
    }

    @GetMapping("/checkout/{itemId}")
    @PreAuthorize("isAuthenticated()")
    @Transactional
    public String checkoutItem(@AuthenticationPrincipal Client client,
                               @PathVariable("itemId") Long id,
                               Model model,
                               HttpServletRequest request) {
        logger.info("Called checkoutItem method");
        Client persistentClient = clientService.findByLogin(client.getLogin());
        ClientItem item = clientItemService.findById(id);

        model.addAttribute("client", persistentClient);
        model.addAttribute("generalPrice", item.getItem().getPrice());

        request.getSession().setAttribute("orderedItems", Collections.singleton(item));

        return "checkoutPage";
    }

    @PostMapping("/checkout")
    @PreAuthorize("isAuthenticated()")
    @Transactional
    public String checkoutOrder(@AuthenticationPrincipal Client client,
                                @RequestParam("payment") String paymentMethod,
                                @RequestParam("generalPrice") String generalPrice,
                                @Valid @ModelAttribute("orderContacts") Contacts contacts,
                                BindingResult bindingResult,
                                Model model,
                                HttpServletRequest request) {
        logger.info("Called checkoutOrder method");
        if (bindingResult.hasErrors()) {
            logger.warn("Form with contact data contains errors");
            model.mergeAttributes(ValidateUtil.validate(bindingResult));
            model.addAttribute("contactsData", contacts);
            model.addAttribute("generalPrice", generalPrice);

            return "checkoutPage";
        }

        @SuppressWarnings("unchecked")
        Set<ClientItem> items = (Set<ClientItem>) request.getSession().getAttribute("orderedItems");
        request.getSession().removeAttribute("orderedItems");
        clientService.deleteBasketItems(items, client.getLogin());

        Client persistentClient = clientService.findByLogin(client.getLogin());

        Order newOrder = new Order(items, contacts, paymentMethod);
        newOrder.setOrderStatus(OrderStatus.NEW);
        newOrder.setClient(persistentClient);
        orderService.save(newOrder);

        items.forEach(i -> {
            i.setOrder(newOrder);
            clientItemService.save(i);
        });

        if (paymentMethod.equals("Наложенный платёж")) {
            logger.info("Method payment is C.O.D");
            //Nothing
        } else if (paymentMethod.equals("Карта")) {
            logger.info("Method payment is Card");
            //Realization of card payment ...
        }

        return "messages/orderCreated";
    }

    @GetMapping("/manager")
    public String customOrders(@AuthenticationPrincipal Client manager,
                               Model model) {
        logger.info("Called customOrders method");
        model.addAttribute("orders", orderService.findOrdersForManagers());
        model.addAttribute("manager", manager);

        return "customOrders";
    }
}