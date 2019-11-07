package katsai.nikolai.internetshop.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import katsai.nikolai.internetshop.lib.Inject;
import katsai.nikolai.internetshop.models.User;
import katsai.nikolai.internetshop.services.UserService;
import katsai.nikolai.internetshop.models.Role;

public class InjectDataController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User admin = new User();
        admin.setRole(Role.of("ADMIN"));
        admin.setLogin("admin");
        admin.setName("Admin");
        admin.setSurname("Admin");
        admin.setPassword("admin");
        userService.add(admin);

        User user = new User();
        user.setRole(Role.of("USER"));
        user.setLogin("nik");
        user.setName("N");
        user.setSurname("K");
        user.setPassword("1");
        userService.add(user);
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
