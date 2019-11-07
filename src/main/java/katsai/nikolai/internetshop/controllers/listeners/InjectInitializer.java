package katsai.nikolai.internetshop.controllers.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import katsai.nikolai.internetshop.lib.NewInjector;

public class InjectInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            NewInjector.injectDependency();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
