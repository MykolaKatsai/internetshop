package mate.academy.internetshop.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.models.Bucket;
import mate.academy.internetshop.models.Order;
import mate.academy.internetshop.models.User;
import mate.academy.internetshop.services.BucketService;
import mate.academy.internetshop.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private static UserDao userDao;
    @Inject
    private static BucketService bucketService;

    @Override
    public User add(User user) {
        if (user.getBucketId() == null) {
            Bucket bucket = new Bucket(user.getUserId());
            bucketService.add(bucket);
            user.setBucketId(bucket.getBucketId());
        }
        user.setToken(getToken());
        return userDao.add(user);
    }

    @Override
    public User get(Long userId) {
        return userDao.get(userId);
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
    public List<Order> getOrders(Long userId) {
        return userDao.get(userId).getOrders();
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
