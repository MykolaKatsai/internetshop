package mate.academy.internetshop.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.models.Bucket;
import mate.academy.internetshop.models.User;
import mate.academy.internetshop.services.BucketService;
import mate.academy.internetshop.services.UserService;

public class DeleteItemFromBucketController extends HttpServlet {
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
        Bucket bucket = bucketService.get(bucketId);
        bucket.getItems().removeIf(item -> item.getItemId().equals(itemId));
        bucketService.update(bucket);

        resp.sendRedirect("bucket?bucket-id=" + bucketId + "&item-id=" + itemId);
    }
}
