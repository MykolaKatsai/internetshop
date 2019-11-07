package katsai.nikolai.internetshop.controllers;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import katsai.nikolai.internetshop.lib.Inject;
import katsai.nikolai.internetshop.models.User;
import katsai.nikolai.internetshop.services.UserService;

public class GetAllUsersController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<User> users = userService.getAllUsers();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/views/allUsers.jsp").forward(req, resp);
    }
}
