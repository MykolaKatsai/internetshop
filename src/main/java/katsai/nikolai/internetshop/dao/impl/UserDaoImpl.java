package katsai.nikolai.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import katsai.nikolai.internetshop.exceptions.AuthenticationException;
import katsai.nikolai.internetshop.dao.UserDao;
import katsai.nikolai.internetshop.lib.Dao;
import katsai.nikolai.internetshop.models.User;
import katsai.nikolai.internetshop.storage.Storage;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User add(User user) {
        Storage.usersStorage.add(user);
        return user;
    }

    @Override
    public User get(Long userId) {
        return Storage.usersStorage.stream()
                .filter(user -> user.getUserId() == userId)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User update(User user) {
        User oldUser = get(user.getUserId());
        if (oldUser == null) {
            throw new NoSuchElementException();
        }
        oldUser.setOrders(user.getOrders());
        oldUser.setBucket(user.getBucket());
        oldUser.setName(user.getName());
        return user;
    }

    @Override
    public User delete(Long userId) {
        User user = get(userId);
        Storage.usersStorage.removeIf(b -> b.getUserId().equals(userId));
        return user;
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return Storage.usersStorage.stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return Storage.usersStorage;
    }

    @Override
    public Optional<User> getByToken(String token) {
        return Storage.usersStorage.stream()
                .filter(u -> u.getToken().equals(token))
                .findFirst();
    }
}
