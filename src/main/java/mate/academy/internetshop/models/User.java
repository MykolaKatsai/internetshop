package mate.academy.internetshop.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private static Long newUserId = 0L;
    private Long userId;
    private String token;
    private String name;
    private String surname;
    private String login;
    private String password;
    private Long bucketId;
    private List<Order> orders = new ArrayList<>();

    public User() {
        userId = newUserId++;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getBucketId() {
        return bucketId;
    }

    public void setBucketId(Long bucketId) {
        this.bucketId = bucketId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{"
                + "userId=" + userId
                + ", name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", login='" + login + '\''
                + '}';
    }
}
