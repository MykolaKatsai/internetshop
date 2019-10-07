package mate.academy.internetshop.models;

import java.util.List;

public class Order {
    private final Long orderId;
    private Long userId;
    private List<Item> orders;

    public Order(Long orderId) {
        this.userId = userId;
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public List<Item> getOrders() {
        return orders;
    }

    public void setOrders(List<Item> orders) {
        this.orders = orders;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
