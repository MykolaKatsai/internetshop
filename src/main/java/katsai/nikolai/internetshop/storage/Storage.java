package katsai.nikolai.internetshop.storage;

import java.util.ArrayList;
import java.util.List;

import katsai.nikolai.internetshop.models.Bucket;
import katsai.nikolai.internetshop.models.Item;
import katsai.nikolai.internetshop.models.Order;
import katsai.nikolai.internetshop.models.User;

public class Storage {
    public static final List<Item> itemsStorage = new ArrayList<>();
    public static final List<User> usersStorage = new ArrayList<>();
    public static final List<Bucket> bucketsStorage = new ArrayList<>();
    public static final List<Order> ordersStorage = new ArrayList<>();
}
