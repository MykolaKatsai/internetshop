package mate.academy.internetshop.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.RoleDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.dao.impl.hibernate.ItemDaoHibernateImpl;
import mate.academy.internetshop.dao.impl.jdbc.BucketDaoJdbcImpl;
import mate.academy.internetshop.dao.impl.jdbc.OrderDaoJdbcImpl;
import mate.academy.internetshop.dao.impl.jdbc.RoleDaoJdbcImpl;
import mate.academy.internetshop.dao.impl.jdbc.UserDaoJdbcImpl;
import mate.academy.internetshop.services.BucketService;
import mate.academy.internetshop.services.ItemService;
import mate.academy.internetshop.services.OrderService;
import mate.academy.internetshop.services.RoleService;
import mate.academy.internetshop.services.UserService;
import mate.academy.internetshop.services.impl.BucketServiceImpl;
import mate.academy.internetshop.services.impl.ItemServiceImpl;
import mate.academy.internetshop.services.impl.OrderServiceImpl;
import mate.academy.internetshop.services.impl.RoleServiceImpl;
import mate.academy.internetshop.services.impl.UserServiceImpl;
import org.apache.log4j.Logger;

public class Factory {
    private static final Logger logger = Logger.getLogger(Factory.class);
    private static BucketDao bucketDaoInstance;
    private static ItemDao itemDaoInstance;
    private static OrderDao orderDaoInstance;
    private static UserDao userDaoInstance;
    private static RoleDao roleDaoInstance;
    private static BucketService bucketServiceInstance;
    private static ItemService itemServiceInstance;
    private static OrderService orderServiceInstance;
    private static UserService userServiceInstance;
    private static RoleService roleServiceInstance;
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection =
                    DriverManager.getConnection("jdbc:mysql://localhost/internet_shop?"
                            + "user=root&password=Nikolay_11082000&serverTimezone=UTC");
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Error in work with driver, or connection to DB");
        }
    }

    public static BucketDao getBucketDaoInstance() {
        if (bucketDaoInstance == null) {
            bucketDaoInstance = new BucketDaoJdbcImpl(connection);
        }
        return bucketDaoInstance;
    }

    public static ItemDao getItemDaoInstance() {
        if (itemDaoInstance == null) {
            itemDaoInstance = new ItemDaoHibernateImpl();
        }
        return itemDaoInstance;
    }

    public static OrderDao getOrderDaoInstance() {
        if (orderDaoInstance == null) {
            orderDaoInstance = new OrderDaoJdbcImpl(connection);
        }
        return orderDaoInstance;
    }

    public static UserDao getUserDaoInstance() {
        if (userDaoInstance == null) {
            userDaoInstance = new UserDaoJdbcImpl(connection);
        }
        return userDaoInstance;
    }

    public static RoleDao getRoleDaoInstance() {
        if (roleDaoInstance == null) {
            roleDaoInstance = new RoleDaoJdbcImpl(connection);
        }
        return roleDaoInstance;
    }

    public static BucketService getBucketServiceInstance() {
        if (bucketServiceInstance == null) {
            bucketServiceInstance = new BucketServiceImpl();
        }
        return bucketServiceInstance;
    }

    public static ItemService getItemServiceInstance() {
        if (itemServiceInstance == null) {
            itemServiceInstance = new ItemServiceImpl();
        }
        return itemServiceInstance;
    }

    public static OrderService getOrderServiceInstance() {
        if (orderServiceInstance == null) {
            orderServiceInstance = new OrderServiceImpl();
        }
        return orderServiceInstance;
    }

    public static UserService getUserServiceInstance() {
        if (userServiceInstance == null) {
            userServiceInstance = new UserServiceImpl();
        }
        return userServiceInstance;
    }

    public static RoleService getRoleServiceInstance() {
        if (roleServiceInstance == null) {
            roleServiceInstance = new RoleServiceImpl();
        }
        return roleServiceInstance;
    }
}
