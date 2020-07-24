package com.shop.GoodsShop.Service;

import com.shop.GoodsShop.Exception.NoItemException;
import com.shop.GoodsShop.Model.Category;
import com.shop.GoodsShop.Model.ClientItem;
import com.shop.GoodsShop.Model.Item;
import com.shop.GoodsShop.Repositories.ClientItemRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ClientItemServiceTest {
    @Autowired
    private ClientItemService clientItemService;

    @MockBean
    private ClientItemRepo clientItemRepo;

    private ClientItem clientItem;

    @BeforeEach
    public void init() {
        Category books = new Category("Books");
        Category book = new Category("Book", books);
        Item item = new Item("item", 30L, 3D
                , 600D, "123");
        item.setDescription("description...");
        item.setCharacteristics("characteristics...");
        item.setCategory(book);
        item.setId(1L);
        this.clientItem = new ClientItem(item, 3);
    }

    @Test
    public void shouldCalculateGeneralPrice() {
        double price = clientItemService.generalPrice(Collections.singleton(clientItem));
        assertThat(price).isEqualTo(1800D);
    }

    @Test
    public void shouldCalculateGeneralWeight() {
        double weight = clientItemService.generalWeight(Collections.singleton(clientItem));
        assertThat(weight).isEqualTo(9D);
    }

    @Test
    public void shouldFindClientItemById() {
        Mockito
                .doReturn(Optional.of(clientItem))
                .when(clientItemRepo)
                .findById(1L);

        ClientItem item = clientItemService.findById(1L);

        assertThat(item.getQuantity()).isEqualTo(3);
        assertThat(item.getItem().getName()).isEqualTo("item");
        Mockito.verify(clientItemRepo, Mockito.times(1))
                .findById(1L);
    }

    @Test
    public void shouldRaiseExceptionWhenFindClientItemByIncorrectId() {
        assertThrows(NoItemException.class,
                () -> clientItemService.findById(1L));

        Mockito.verify(clientItemRepo, Mockito.times(1))
                .findById(1L);
    }

    @Test
    public void shouldSaveClientItem() {
        clientItemService.save(clientItem);

        Mockito.verify(clientItemRepo, Mockito.times(1))
                .save(clientItem);
    }

    @Test
    public void shouldDeleteClientItem() {
        clientItemService.delete(clientItem);

        Mockito.verify(clientItemRepo, Mockito.times(1))
                .delete(clientItem);
    }

    @Test
    public void shouldDeleteClientItemById() {
        clientItemService.deleteById(1L);

        Mockito.verify(clientItemRepo, Mockito.times(1))
                .deleteById(1L);
    }

    @Test
    public void shouldDeleteClientItemsSet() {
        clientItemService.deleteSetItems(new HashSet<>(Collections.singleton(clientItem)));

        Mockito.verify(clientItemRepo, Mockito.times(1))
                .delete(clientItem);
    }
}
