package katsai.nikolai.internetshop.controllers.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import katsai.nikolai.internetshop.lib.Inject;
import katsai.nikolai.internetshop.services.UserService;
import org.apache.log4j.Logger;

public class DataInitializer implements ServletContextListener {
    private static final Logger logger = Logger.getLogger(DataInitializer.class);
    @Inject
    private static UserService userService;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
//
//        User admin = new User();
//        admin.setRole(Role.of("ADMIN"));
//        admin.setLogin("admin");
//        admin.setName("Admin");
//        admin.setSurname("Admin");
//        admin.setPassword("admin");
//        userService.add(admin);
//
//        User user = new User();
//        user.setRole(Role.of("USER"));
//        user.setLogin("nik");
//        user.setName("N");
//        user.setSurname("K");
//        user.setPassword("1");
//        userService.add(user);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
