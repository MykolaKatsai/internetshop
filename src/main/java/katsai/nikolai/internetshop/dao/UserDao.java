package katsai.nikolai.internetshop.dao;

import java.util.List;
import java.util.Optional;

import katsai.nikolai.internetshop.exceptions.AuthenticationException;
import katsai.nikolai.internetshop.models.User;

public interface UserDao {
    User add(User user);

    User get(Long userId);

    User update(User user);

    User delete(Long userId);

    Optional<User> getByLogin(String login);

    List<User> getAllUsers();

    Optional<User> getByToken(String token);
}
