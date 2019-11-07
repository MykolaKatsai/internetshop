package katsai.nikolai.internetshop.services.impl;

import java.sql.SQLException;

import java.util.List;

import katsai.nikolai.internetshop.dao.ItemDao;
import katsai.nikolai.internetshop.lib.Inject;
import katsai.nikolai.internetshop.lib.Service;
import katsai.nikolai.internetshop.models.Item;
import katsai.nikolai.internetshop.services.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
    @Inject
    private static ItemDao itemDao;

    @Override
    public Item add(Item item) {
        return itemDao.add(item);
    }

    @Override
    public Item get(Long itemId) {
        return itemDao.get(itemId);
    }

    @Override
    public Item update(Item item) {
        return itemDao.update(item);
    }

    @Override
    public Item delete(Long itemId) {
        return itemDao.delete(itemId);
    }

    @Override
    public List<Item> getAllItems() throws SQLException {
        return itemDao.getAllItems();
    }
}
