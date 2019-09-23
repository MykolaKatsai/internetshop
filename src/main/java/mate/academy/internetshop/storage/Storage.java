package mate.academy.internetshop.storage;

import java.util.ArrayList;
import java.util.List;

import mate.academy.internetshop.models.Bucket;
import mate.academy.internetshop.models.Item;
import mate.academy.internetshop.models.Order;
import mate.academy.internetshop.models.User;

public class Storage {
    public static final List<Item> itemsStorage = new ArrayList<>();
    public static final List<User> usersStorage = new ArrayList<>();
    public static final List<Bucket> bucketsStorage = new ArrayList<>();
    public static final List<Order> ordersStorage = new ArrayList<>();
}
