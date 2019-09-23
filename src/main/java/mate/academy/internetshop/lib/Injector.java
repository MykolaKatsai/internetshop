package mate.academy.internetshop.lib;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.factory.Factory;
import mate.academy.internetshop.services.BucketService;
import mate.academy.internetshop.services.ItemService;
import mate.academy.internetshop.services.OrderService;
import mate.academy.internetshop.services.UserService;
import mate.academy.internetshop.services.impl.BucketServiceImpl;
import mate.academy.internetshop.services.impl.ItemServiceImpl;
import mate.academy.internetshop.services.impl.OrderServiceImpl;
import mate.academy.internetshop.services.impl.UserServiceImpl;

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
