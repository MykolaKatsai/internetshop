package mate.academy.internetshop.dao;

import java.util.List;

import java.sql.SQLException;
import mate.academy.internetshop.models.Item;

public interface ItemDao {
    Item add(Item item);

    Item get(Long itemId);

    Item update(Item item);

    Item delete(Long itemId);

    List<Item> getAllItems() throws SQLException;
}
