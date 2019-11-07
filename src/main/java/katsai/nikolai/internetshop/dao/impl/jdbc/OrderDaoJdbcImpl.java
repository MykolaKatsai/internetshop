package katsai.nikolai.internetshop.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import katsai.nikolai.internetshop.dao.OrderDao;
import katsai.nikolai.internetshop.models.Item;
import katsai.nikolai.internetshop.models.Order;
import org.apache.log4j.Logger;

public class OrderDaoJdbcImpl extends AbstractDaoClass<Order> implements OrderDao {
    private static final Logger logger = Logger.getLogger(OrderDaoJdbcImpl.class);

    public OrderDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Order add(Order order) {
        String addOrderQuery = "INSERT INTO orders (user_id) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(addOrderQuery,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, order.getUser().getUserId());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                Order orderWithValidId = new Order();
                orderWithValidId.setOrderId(rs.getLong(1));
                orderWithValidId.setUser(order.getUser());
                orderWithValidId.setItems(order.getItems());
                order = orderWithValidId;
            }
        } catch (SQLException e) {
            logger.warn("Can`t add order");
        }
        String addOrderItemsQuery = "INSERT INTO orders_items (order_id, item_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(addOrderItemsQuery)) {
            statement.setLong(1, order.getOrderId());
            for (Item item : order.getItems()) {
                statement.setLong(2, item.getItemId());
                statement.execute();
            }
        } catch (SQLException e) {
            logger.warn("Can`t add Item");
        }
        return order;
    }

    @Override
    public Order get(Long orderId) {
        Order order = null;
        String getOrderQuery = "SELECT * FROM orders AS o INNER JOIN orders_items AS o_i ON"
                + " o.order_id = o_i.order_id INNER JOIN items AS i ON o_i.item_id = i.item_id "
                + "WHERE o.order_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(getOrderQuery)) {
            statement.setLong(1, orderId);
            ResultSet rs = statement.executeQuery();
            Long userId = null;
            List<Item> items = new ArrayList<>();
            while (rs.next()) {
                Item item = new Item(rs.getLong("item_id"));
                item.setName(rs.getString("item_name"));
                item.setPrice(rs.getDouble("price"));
                items.add(item);
            }
            order = new Order();
            order.setOrderId(orderId);
            order.setItems(items);

        } catch (SQLException e) {
            logger.warn("Can`t get order by id = " + orderId);
        }
        return order;
    }

    @Override
    public Order update(Order order) {
        String updateOrderQuery = "INSERT INTO orders_items (order_id, item_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(updateOrderQuery)) {
            deleteItemsFromOrder(order.getOrderId());
            statement.setLong(1, order.getOrderId());
            for (Item item : order.getItems()) {
                statement.setLong(2, item.getItemId());
                statement.execute();
            }
        } catch (SQLException e) {
            logger.warn("Can`t update order");
        }
        return order;
    }

    @Override
    public Order delete(Long orderId) {
        Order order = get(orderId);
        String deleteOrderQuery = "DELETE FROM orders WHERE order_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteOrderQuery)) {
            deleteItemsFromOrder(orderId);
            statement.setLong(1, orderId);
            statement.execute();
        } catch (SQLException e) {
            logger.warn("Can`t delete order");
        }
        return order;
    }

    public List<Order> getUserOrders(Long userId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE orders.user_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(get(resultSet.getLong("order_id")));
            }
        } catch (SQLException e) {
            logger.error("Can`t get user orders");
        }
        return orders;
    }

    private void deleteItemsFromOrder(Long orderId) throws SQLException {
        String query = "DELETE FROM orders_items WHERE order_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, orderId);
            statement.execute();
        }
    }
}
