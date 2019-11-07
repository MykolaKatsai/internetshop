package katsai.nikolai.internetshop.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import katsai.nikolai.internetshop.dao.UserDao;
import katsai.nikolai.internetshop.exceptions.AuthenticationException;
import katsai.nikolai.internetshop.lib.Inject;
import katsai.nikolai.internetshop.lib.Service;
import katsai.nikolai.internetshop.models.Bucket;
import katsai.nikolai.internetshop.models.User;
import katsai.nikolai.internetshop.services.OrderService;
import katsai.nikolai.internetshop.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private static UserDao userDao;
    @Inject
    private static OrderService orderService;

    @Override
    public User add(User user) {
        user.setToken(getToken());
        user.setBucket(new Bucket());
        user = userDao.add(user);
        return user;
    }

    @Override
    public User get(Long userId) {
        User user = userDao.get(userId);
        user.setOrders(orderService.getOrders(userId));
        return user;
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public User delete(Long userId) {
        return userDao.delete(userId);
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        return userDao.login(login, password);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public Optional<User> getByToken(String token) {
        return userDao.getByToken(token);
    }

    private String getToken() {
        return UUID.randomUUID().toString();
    }
}
