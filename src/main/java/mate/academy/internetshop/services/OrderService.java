package mate.academy.internetshop.services;

import java.util.List;

import mate.academy.internetshop.models.Item;
import mate.academy.internetshop.models.Order;

public interface OrderService {
    Order add(Order order);

    Order get(Long orderId);

    Order update(Order order);

    Order delete(Long orderId);

    Order completeOrder(List<Item> items, Long userId);
}
