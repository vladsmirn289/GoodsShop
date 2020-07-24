package com.shop.GoodsShop.Service;

import com.shop.GoodsShop.Exception.NoItemException;
import com.shop.GoodsShop.Model.ClientItem;
import com.shop.GoodsShop.Repositories.ClientItemRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ClientItemServiceImpl implements ClientItemService {
    private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

    private ClientItemRepo clientItemRepo;

    @Autowired
    public void setClientItemRepo(ClientItemRepo clientItemRepo) {
        logger.debug("Setting clientItemRepo");
        this.clientItemRepo = clientItemRepo;
    }

    @Override
    public double generalPrice(Set<ClientItem> basket) {
        return basket.stream()
                .map(item -> item.getItem().getPrice() * item.getQuantity())
                .reduce(Double::sum).orElse(0D);
    }

    @Override
    public double generalWeight(Set<ClientItem> basket) {
        return basket.stream()
                .map(item -> item.getItem().getWeight() * item.getQuantity())
                .reduce(Double::sum).orElse(0D);
    }

    @Override
    public ClientItem findById(Long id) {
        logger.info("Finding client item by id = " + id);
        return clientItemRepo.findById(id).orElseThrow(NoItemException::new);
    }

    @Override
    public void save(ClientItem clientItem) {
        logger.info("Saving client item to database");
        clientItemRepo.save(clientItem);
    }

    @Override
    public void delete(ClientItem clientItem) {
        logger.info("Deleting client item with id = " + clientItem.getId() + " from database");
        clientItemRepo.delete(clientItem);
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Deleting client item with id = " + id + " from database");
        clientItemRepo.deleteById(id);
    }

    @Override
    public void deleteSetItems(Set<ClientItem> clientItemSet) {
        logger.info("Deleting set of client items from database");
        for (ClientItem clientItem : clientItemSet) {
            delete(clientItem);
        }
    }
}
