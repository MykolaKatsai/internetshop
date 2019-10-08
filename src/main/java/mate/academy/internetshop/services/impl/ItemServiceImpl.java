package mate.academy.internetshop.services.impl;

import java.sql.SQLException;

import java.util.List;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.models.Item;
import mate.academy.internetshop.services.ItemService;

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
