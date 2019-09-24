package mate.academy.internetshop.services;

import java.util.List;

import mate.academy.internetshop.models.Order;
import mate.academy.internetshop.models.User;

public interface UserService {
    User add(User user);

    User get(Long userId);

    User update(User user);

    User delete(Long userId);

    User login(String login, String password);

    List<Order> getOrders(Long userId);

    List<User> getAllUsers();
}
