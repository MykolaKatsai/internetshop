package katsai.nikolai.internetshop.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import katsai.nikolai.internetshop.dao.BucketDao;
import katsai.nikolai.internetshop.dao.ItemDao;
import katsai.nikolai.internetshop.dao.OrderDao;
import katsai.nikolai.internetshop.dao.UserDao;
import katsai.nikolai.internetshop.dao.impl.hibernate.BucketDaoHibernateImpl;
import katsai.nikolai.internetshop.dao.impl.hibernate.ItemDaoHibernateImpl;
import katsai.nikolai.internetshop.dao.impl.hibernate.UserDaoHibernateImpl;
import katsai.nikolai.internetshop.dao.RoleDao;
import katsai.nikolai.internetshop.dao.impl.hibernate.OrderDaoHibernateImpl;
import katsai.nikolai.internetshop.dao.impl.hibernate.RoleDaoHibernateImpl;
import katsai.nikolai.internetshop.services.BucketService;
import katsai.nikolai.internetshop.services.ItemService;
import katsai.nikolai.internetshop.services.OrderService;
import katsai.nikolai.internetshop.services.RoleService;
import katsai.nikolai.internetshop.services.UserService;
import katsai.nikolai.internetshop.services.impl.BucketServiceImpl;
import katsai.nikolai.internetshop.services.impl.ItemServiceImpl;
import katsai.nikolai.internetshop.services.impl.OrderServiceImpl;
import katsai.nikolai.internetshop.services.impl.RoleServiceImpl;
import katsai.nikolai.internetshop.services.impl.UserServiceImpl;
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
            bucketDaoInstance = new BucketDaoHibernateImpl();
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
            orderDaoInstance = new OrderDaoHibernateImpl();
        }
        return orderDaoInstance;
    }

    public static UserDao getUserDaoInstance() {
        if (userDaoInstance == null) {
            userDaoInstance = new UserDaoHibernateImpl();
        }
        return userDaoInstance;
    }

    public static RoleDao getRoleDaoInstance() {
        if (roleDaoInstance == null) {
            roleDaoInstance = new RoleDaoHibernateImpl();
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
