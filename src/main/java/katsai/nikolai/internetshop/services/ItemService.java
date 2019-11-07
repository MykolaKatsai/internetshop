package katsai.nikolai.internetshop.services;

import java.sql.SQLException;

import java.util.List;

import katsai.nikolai.internetshop.models.Item;

public interface ItemService {
    Item add(Item item);

    Item get(Long itemId);

    Item update(Item item);

    Item delete(Long itemId);

    List<Item> getAllItems() throws SQLException;
}
