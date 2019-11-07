package katsai.nikolai.internetshop.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import katsai.nikolai.internetshop.lib.Inject;
import katsai.nikolai.internetshop.models.User;
import katsai.nikolai.internetshop.services.UserService;

public class GetUserInfoController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("user-id");
        User user = userService.get(userId);

        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/views/userInfo.jsp").forward(req, resp);
    }
}
