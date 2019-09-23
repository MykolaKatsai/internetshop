package mate.academy.internetshop.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.services.BucketService;

public class AddItemToBucketController extends HttpServlet {
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getParameterMap().isEmpty()) {
            req.getRequestDispatcher("/WEB-INF/views/addItemToBucket.jsp").forward(req, resp);
        }
        Long bucketId = Long.valueOf(req.getParameter("bucket-id"));
        Long itemId = Long.valueOf(req.getParameter("item-id"));
        bucketService.addItem(bucketId, itemId);
        req.getRequestDispatcher("/WEB-INF/views/addItemToBucket.jsp").forward(req, resp);
    }
}
