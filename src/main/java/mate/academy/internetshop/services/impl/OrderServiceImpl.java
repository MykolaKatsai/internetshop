package mate.academy.internetshop.services.impl;

import java.util.List;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.models.Item;
import mate.academy.internetshop.models.Order;
import mate.academy.internetshop.models.User;
import mate.academy.internetshop.services.OrderService;
import mate.academy.internetshop.services.UserService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private static OrderDao orderDao;
    @Inject
    private static UserService userService;

    @Override
    public Order add(Order order) {
        return orderDao.add(order);
    }

    @Override
    public Order get(Long orderId) {
        return orderDao.get(orderId);
    }

    @Override
    public Order update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public Order delete(Long orderId) {
        Order order = orderDao.delete(orderId);
        User user = userService.get(order.getUserId());
        user.getOrders().removeIf(o -> o.getOrderId().equals(orderId));
        userService.update(user);
        return order;
    }

    @Override
    public List<Order> getOrders(Long userId) {
        return orderDao.getUserOrders(userId);
    }

    @Override
    public Order completeOrder(List<Item> items, Long userId) {
        Order order = new Order(userId);
        order.setOrders(items);
        order = orderDao.add(order);
        User user = userService.get(userId);
        user.getOrders().add(order);
        userService.update(user);
        return order;
    }
}
