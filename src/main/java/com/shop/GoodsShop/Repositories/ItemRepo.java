package com.shop.GoodsShop.Repositories;

import com.shop.GoodsShop.Model.Category;
import com.shop.GoodsShop.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item, Long> {
    List<Item> findByName(String name);
    List<Item> findByPrice(Double price);
    List<Item> findByCategory(Category category);
    Item findByCode(String code);
}
