package mate.academy.internetshop.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Cookie cookie = new Cookie("MATE", "");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
        req.getSession().removeAttribute("user-id");
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
