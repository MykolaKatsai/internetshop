package mate.academy.internetshop.lib;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import java.net.URL;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.RoleDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.factory.Factory;
import mate.academy.internetshop.services.BucketService;
import mate.academy.internetshop.services.ItemService;
import mate.academy.internetshop.services.OrderService;
import mate.academy.internetshop.services.RoleService;
import mate.academy.internetshop.services.UserService;

public class NewInjector {
    private static Map<Class, Object> classMap = new HashMap<>();
    private static List<Class> classes = new ArrayList<>();

    static {
        try {
            classes.addAll(getClasses("mate.academy.internetshop"));
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        classMap.put(BucketDao.class, Factory.getBucketDaoInstance());
        classMap.put(ItemDao.class, Factory.getItemDaoInstance());
        classMap.put(OrderDao.class, Factory.getOrderDaoInstance());
        classMap.put(UserDao.class, Factory.getUserDaoInstance());
        classMap.put(RoleDao.class, Factory.getRoleDaoInstance());

        classMap.put(BucketService.class, Factory.getBucketServiceInstance());
        classMap.put(ItemService.class, Factory.getItemServiceInstance());
        classMap.put(OrderService.class, Factory.getOrderServiceInstance());
        classMap.put(UserService.class, Factory.getUserServiceInstance());
        classMap.put(RoleService.class, Factory.getRoleServiceInstance());
    }

    public static void injectDependency() throws IllegalAccessException {
        List<Field[]> fieldsOfClasses = new ArrayList<>();

        for (Class c : classes) {
            fieldsOfClasses.add(c.getDeclaredFields());
        }

        for (Field[] fields : fieldsOfClasses) {
            for (Field field : fields) {
                if (field.getDeclaredAnnotation(Inject.class) != null) {
                    field.setAccessible(true);
                    field.set(null, classMap.get(field.getType()));
                }
            }
        }
    }

    private static List<Class> getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException -
     */
    private static List<Class> findClasses(File directory, String packageName)
            throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName()
                        .substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
