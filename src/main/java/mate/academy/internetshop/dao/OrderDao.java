package mate.academy.internetshop.dao;

import mate.academy.internetshop.models.Order;

public interface OrderDao {
    Order add(Order order);

    Order get(Long orderId);

    Order update(Order order);

    Order delete(Long orderId);
}
