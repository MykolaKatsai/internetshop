package mate.academy.internetshop.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.models.Bucket;
import mate.academy.internetshop.services.BucketService;
import mate.academy.internetshop.services.OrderService;

public class AddNewOrderController extends HttpServlet {
    @Inject
    private static OrderService orderService;
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Bucket bucket = bucketService.get(Long.valueOf(req.getParameter("bucket-id")));
        orderService.completeOrder(bucket.getItems(), bucket.getUserId());
        bucketService.clear(bucket.getBucketId());
        resp.sendRedirect("getAllUsers");
    }
}
