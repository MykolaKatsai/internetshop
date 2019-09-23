package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.models.User;
import mate.academy.internetshop.storage.Storage;

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
        oldUser.setBucketId(user.getBucketId());
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
    public List<User> getAllUsers() {
        return Storage.usersStorage;
    }
}
