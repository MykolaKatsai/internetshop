package katsai.nikolai.internetshop.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import katsai.nikolai.internetshop.dao.RoleDao;
import katsai.nikolai.internetshop.dao.UserDao;
import katsai.nikolai.internetshop.exceptions.AuthenticationException;
import katsai.nikolai.internetshop.lib.Inject;
import katsai.nikolai.internetshop.lib.Service;
import katsai.nikolai.internetshop.models.Bucket;
import katsai.nikolai.internetshop.models.Role;
import katsai.nikolai.internetshop.models.User;
import katsai.nikolai.internetshop.services.OrderService;
import katsai.nikolai.internetshop.services.UserService;
import katsai.nikolai.internetshop.util.HashUtil;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private static UserDao userDao;
    @Inject
    private static RoleDao roleDao;
    @Inject
    private static OrderService orderService;

    @Override
    public User add(User user) {
        Role role = roleDao.getByName("USER");
        byte[] salt = HashUtil.getSalt();
        String hashedPassword = HashUtil.hashPassword(user.getPassword(), salt);

        user.setRole(role);
        user.setSalt(salt);
        user.setPassword(hashedPassword);
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
        Optional<User> user = userDao.getByLogin(login);
        if (user.isEmpty() || !user.get().getPassword().equals(HashUtil
                .hashPassword(password, user.get().getSalt()))) {
            throw new AuthenticationException("Incorrect login or password");
        }
        return user.get();
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
