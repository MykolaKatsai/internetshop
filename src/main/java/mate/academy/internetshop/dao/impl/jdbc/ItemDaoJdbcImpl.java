package mate.academy.internetshop.dao.impl.jdbc;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.models.Item;

public class ItemDaoJdbcImpl extends AbstractClass<Item> implements ItemDao {
    private static String DB_NAME = "internet_shop";

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item add(Item item) {
        String query = "INSERT INTO " + DB_NAME + ".items (name, price)"
                + " values('" + item.getName() + "', " + item.getPrice() + ");";
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public Item get(Long itemId) {
        String query = "SELECT * FROM " + DB_NAME + ".items WHERE item_id=" + itemId + ";";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                Item item = new Item(itemId);
                item.setName(name);
                item.setPrice(price);
                return item;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Item update(Item item) {
        String query = "UPDATE " + DB_NAME + ".items SET name='"
                + item.getName() + "', price='" + item.getPrice() + "' WHERE item_id=" + item.getItemId() + ";";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public Item delete(Long itemId) {
        Item item = get(itemId);
        String query = "DELETE FROM " + DB_NAME + ".items WHERE item_id=" + itemId + ";";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> result = new ArrayList<>();
        String query = "SELECT * FROM " + DB_NAME + ".items;";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                Item item = new Item(rs.getLong("item_id"));
                item.setName(name);
                item.setPrice(price);
                result.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
