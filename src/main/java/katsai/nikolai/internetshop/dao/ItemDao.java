package katsai.nikolai.internetshop.dao;

import java.sql.SQLException;

import java.util.List;

import katsai.nikolai.internetshop.models.Item;

public interface ItemDao {
    Item add(Item item);

    Item get(Long itemId);

    Item update(Item item);

    Item delete(Long itemId);

    List<Item> getAllItems() throws SQLException;
}
