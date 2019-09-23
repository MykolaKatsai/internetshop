package mate.academy.internetshop.services;

import java.util.List;

import mate.academy.internetshop.models.Item;

public interface ItemService {
    Item add(Item item);

    Item get(Long itemId);

    Item update(Item item);

    Item delete(Long itemId);

    List<Item> getAllItems();
}
