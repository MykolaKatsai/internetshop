package katsai.nikolai.internetshop.dao;

import java.util.List;

import katsai.nikolai.internetshop.models.Order;

public interface OrderDao {
    Order add(Order order);

    Order get(Long orderId);

    Order update(Order order);

    Order delete(Long orderId);

    List<Order> getUserOrders(Long userId);
}
