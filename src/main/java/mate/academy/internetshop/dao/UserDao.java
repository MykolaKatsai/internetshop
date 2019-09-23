package mate.academy.internetshop.dao;

import java.util.List;

import mate.academy.internetshop.models.User;

public interface UserDao {
    User add(User user);

    User get(Long userId);

    User update(User user);

    User delete(Long userId);

    List<User> getAllUsers();
}
