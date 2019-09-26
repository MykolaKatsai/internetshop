package mate.academy.internetshop.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.models.User;
import mate.academy.internetshop.services.BucketService;
import mate.academy.internetshop.services.UserService;

public class AddItemToBucketController extends HttpServlet {
    @Inject
    private static UserService userService;
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("user-id");
        User user = userService.get(userId);

        Long bucketId = user.getBucketId();
        Long itemId = Long.valueOf(req.getParameter("item-id"));
        bucketService.addItem(bucketId, itemId);

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
