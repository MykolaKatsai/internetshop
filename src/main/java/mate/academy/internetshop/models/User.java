package mate.academy.internetshop.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private final Long userId;
    private String token;
    private String name;
    private String surname;
    private String login;
    private String password;
    private Long bucketId;
    private Set<Role> roles = new HashSet<>();
    private List<Order> orders = new ArrayList<>();

    public User() {
        userId = 0L;
    }

    public User(Long userId) {
        this.userId = userId;
    }

    public static User copyUser(Long newUserId, User oldUser) {
        User newUser = new User(newUserId);
        newUser.setName(oldUser.getName());
        newUser.setSurname(oldUser.getSurname());
        newUser.setLogin(oldUser.getLogin());
        newUser.setPassword(oldUser.getPassword());
        newUser.setBucketId(oldUser.getBucketId());
        newUser.setToken(oldUser.getToken());
        newUser.roles = oldUser.roles;
        newUser.orders = oldUser.orders;
        return newUser;
    }

    public Long getUserId() {
        return userId;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRole(Role role) {
        this.roles.add(role);
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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
