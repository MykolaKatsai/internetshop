package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.models.Order;
import mate.academy.internetshop.storage.Storage;

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
        oldOrder.setOrders(order.getOrders());
        oldOrder.setUserId(order.getUserId());
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
