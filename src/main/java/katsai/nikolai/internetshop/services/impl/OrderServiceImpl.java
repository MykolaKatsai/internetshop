package katsai.nikolai.internetshop.services.impl;

import java.util.List;

import katsai.nikolai.internetshop.dao.OrderDao;
import katsai.nikolai.internetshop.lib.Inject;
import katsai.nikolai.internetshop.lib.Service;
import katsai.nikolai.internetshop.models.Item;
import katsai.nikolai.internetshop.models.Order;
import katsai.nikolai.internetshop.models.User;
import katsai.nikolai.internetshop.services.OrderService;
import katsai.nikolai.internetshop.services.UserService;

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
        User user = userService.get(order.getUser().getUserId());
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
        User user = userService.get(userId);

        Order order = new Order();
        order.setUser(user);
        order.setItems(items);
        order = orderDao.add(order);
        user.getOrders().add(order);
        userService.update(user);
        return order;
    }
}
