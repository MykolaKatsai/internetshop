package katsai.nikolai.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;

import katsai.nikolai.internetshop.dao.ItemDao;
import katsai.nikolai.internetshop.lib.Dao;
import katsai.nikolai.internetshop.models.Item;
import katsai.nikolai.internetshop.storage.Storage;

@Dao
public class ItemDaoImpl implements ItemDao {
    @Override
    public Item add(Item item) {
        Storage.itemsStorage.add(item);
        return item;
    }

    @Override
    public Item get(Long itemId) {
        return Storage.itemsStorage.stream()
                .filter(i -> i.getItemId() == itemId)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Item update(Item item) {
        Item oldItem = get(item.getItemId());
        if (oldItem == null) {
            throw new NoSuchElementException();
        }
        oldItem.setName(item.getName());
        oldItem.setPrice(item.getPrice());
        return item;
    }

    @Override
    public Item delete(Long itemId) {
        Item item = get(itemId);
        Storage.itemsStorage.removeIf(b -> b.getItemId().equals(itemId));
        return item;
    }

    @Override
    public List<Item> getAllItems() {
        return Storage.itemsStorage;
    }
}
