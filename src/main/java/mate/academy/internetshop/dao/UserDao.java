package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.models.User;

public interface UserDao {
    User add(User user);

    User get(Long userId);

    User update(User user);

    User delete(Long userId);

    User login(String login, String password) throws AuthenticationException;

    List<User> getAllUsers();

    Optional<User> getByToken(String token);
}
