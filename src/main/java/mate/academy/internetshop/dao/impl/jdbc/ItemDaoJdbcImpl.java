package mate.academy.internetshop.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.models.Item;
import org.apache.log4j.Logger;

public class ItemDaoJdbcImpl extends AbstractDaoClass<Item> implements ItemDao {
    private static final Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item add(Item item) {
        String query = String.format("INSERT INTO %s.items (item_name, price) values('%s', %s);",
                DB_NAME, item.getName(), item.getPrice());
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            logger.warn("Can`t add item");
        }
        return item;
    }

    @Override
    public Item get(Long itemId) {
        String query = String.format("SELECT * FROM %s.items WHERE item_id=%b;", DB_NAME, itemId);
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
            logger.warn("Can`t get item");
        }
        return null;
    }

    @Override
    public Item update(Item item) {
        String query = String.format("UPDATE %s.items SET item_name='%s', price='%s' WHERE item_id=%d;",
                DB_NAME, item.getName(), item.getPrice(), item.getItemId());
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.warn("Can`t update item");
        }
        return item;
    }

    @Override
    public Item delete(Long itemId) {
        Item item = get(itemId);
        String query = String.format("DELETE FROM %s.items WHERE item_id=%d;", DB_NAME, itemId);
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            logger.warn("Can`t delete item");
        }
        return item;
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> result = new ArrayList<>();
        String query = String.format("SELECT * FROM %s.items;", DB_NAME);
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("item_name");
                Double price = rs.getDouble("price");
                Item item = new Item(rs.getLong("item_id"));
                item.setName(name);
                item.setPrice(price);
                result.add(item);
            }
        } catch (SQLException e) {
            logger.warn("Can`t get all item");
        }
        return result;
    }
}
