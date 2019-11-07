package katsai.nikolai.internetshop.services;

import java.util.List;

import katsai.nikolai.internetshop.models.Item;
import katsai.nikolai.internetshop.models.Order;

public interface OrderService {
    Order add(Order order);

    Order get(Long orderId);

    Order update(Order order);

    Order delete(Long orderId);

    List<Order> getOrders(Long userId);

    Order completeOrder(List<Item> items, Long userId);
}
