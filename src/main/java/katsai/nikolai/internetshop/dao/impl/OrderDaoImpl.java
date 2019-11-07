package katsai.nikolai.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;

import katsai.nikolai.internetshop.dao.OrderDao;
import katsai.nikolai.internetshop.lib.Dao;
import katsai.nikolai.internetshop.models.Order;
import katsai.nikolai.internetshop.storage.Storage;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order add(Order order) {
        Storage.ordersStorage.add(order);
        return order;
    }

    @Override
    public Order get(Long orderId) {
        return Storage.ordersStorage.stream()
                .filter(order -> order.getOrderId() == orderId)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Order update(Order order) {
        Order oldOrder = get(order.getOrderId());
        if (oldOrder == null) {
            throw new NoSuchElementException();
        }
        oldOrder.setItems(order.getItems());
        oldOrder.setUser(order.getUser());
        return order;
    }

    @Override
    public Order delete(Long orderId) {
        Order order = get(orderId);
        Storage.ordersStorage.removeIf(b -> b.getOrderId().equals(orderId));
        return order;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return null;
    }
}
