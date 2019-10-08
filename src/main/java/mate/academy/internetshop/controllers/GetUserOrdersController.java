package mate.academy.internetshop.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.services.OrderService;
import mate.academy.internetshop.services.UserService;

public class GetUserOrdersController extends HttpServlet {
    @Inject
    private static OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("user-id");

        req.setAttribute("orders", orderService.getOrders(userId));
        req.getRequestDispatcher("/WEB-INF/views/displayOrders.jsp").forward(req, resp);
    }
}
