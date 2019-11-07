package katsai.nikolai.internetshop.lib;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import katsai.nikolai.internetshop.dao.BucketDao;
import katsai.nikolai.internetshop.dao.ItemDao;
import katsai.nikolai.internetshop.dao.OrderDao;
import katsai.nikolai.internetshop.dao.UserDao;
import katsai.nikolai.internetshop.factory.Factory;
import katsai.nikolai.internetshop.services.BucketService;
import katsai.nikolai.internetshop.services.ItemService;
import katsai.nikolai.internetshop.services.OrderService;
import katsai.nikolai.internetshop.services.UserService;
import katsai.nikolai.internetshop.services.impl.BucketServiceImpl;
import katsai.nikolai.internetshop.services.impl.ItemServiceImpl;
import katsai.nikolai.internetshop.services.impl.OrderServiceImpl;
import katsai.nikolai.internetshop.services.impl.UserServiceImpl;

public class Injector {
    private static Map<Class, Object> classMap = new HashMap<>();

    static {
        classMap.put(BucketDao.class, Factory.getBucketDaoInstance());
        classMap.put(ItemDao.class, Factory.getItemDaoInstance());
        classMap.put(OrderDao.class, Factory.getOrderDaoInstance());
        classMap.put(UserDao.class, Factory.getUserDaoInstance());
        classMap.put(BucketService.class, Factory.getBucketServiceInstance());
        classMap.put(ItemService.class, Factory.getItemServiceInstance());
        classMap.put(OrderService.class, Factory.getOrderServiceInstance());
        classMap.put(UserService.class, Factory.getUserServiceInstance());
    }

    public static void injectDependency() throws IllegalAccessException {
        List<Field[]> fieldsOfClasses = new ArrayList<>();

        fieldsOfClasses.add(ItemServiceImpl.class.getDeclaredFields());
        fieldsOfClasses.add(OrderServiceImpl.class.getDeclaredFields());
        fieldsOfClasses.add(BucketServiceImpl.class.getDeclaredFields());
        fieldsOfClasses.add(UserServiceImpl.class.getDeclaredFields());

        for (Field[] fields : fieldsOfClasses) {
            for (Field field : fields) {
                if (field.getDeclaredAnnotation(Inject.class) != null) {
                    field.setAccessible(true);
                    field.set(null, classMap.get(field.getType()));
                }
            }
        }
    }
}
