package mate.academy.internetshop.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.models.User;
import mate.academy.internetshop.services.UserService;

public class RegistrationController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = new User();
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("psw"));
        user.setName(req.getParameter("user-name"));
        user.setSurname(req.getParameter("user-surname"));
        userService.add(user);

        Cookie cookie = new Cookie("MATE", user.getToken());
        resp.addCookie(cookie);

        HttpSession session = req.getSession(true);
        session.setAttribute("user-id", user.getUserId());

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
