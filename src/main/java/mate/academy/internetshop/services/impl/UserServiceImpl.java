package mate.academy.internetshop.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.models.Bucket;
import mate.academy.internetshop.models.Order;
import mate.academy.internetshop.models.Role;
import mate.academy.internetshop.models.User;
import mate.academy.internetshop.services.BucketService;
import mate.academy.internetshop.services.OrderService;
import mate.academy.internetshop.services.RoleService;
import mate.academy.internetshop.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private static UserDao userDao;
    @Inject
    private static BucketService bucketService;
    @Inject
    private static OrderService orderService;
    @Inject
    private static RoleService roleService;

    @Override
    public User add(User user) {
        Set<Role> validRoles = new HashSet<>();
        for (Role role : user.getRoles()) {
            validRoles.add(roleService.getByName(role.getRoleName()));
        }
        user.setRoles(validRoles);
        user.setToken(getToken());
        user = userDao.add(user);

        if (user.getBucketId() == null) {
            Bucket bucket = bucketService.add(new Bucket(user.getUserId()));
            user.setBucketId(bucket.getBucketId());
        }
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
