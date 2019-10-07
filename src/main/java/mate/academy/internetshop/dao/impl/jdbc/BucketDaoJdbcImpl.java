package mate.academy.internetshop.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.models.Bucket;
import mate.academy.internetshop.models.Item;
import org.apache.log4j.Logger;

public class BucketDaoJdbcImpl extends AbstractDaoClass<Bucket> implements BucketDao {
    private static final Logger logger = Logger.getLogger(OrderDaoJdbcImpl.class);

    public BucketDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Bucket add(Bucket bucket) {
        String addBucketQuery = "INSERT INTO buckets (user_id) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(addBucketQuery,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, bucket.getUserId());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                Bucket bucketWithValidId = new Bucket(rs.getLong(1),
                        bucket.getUserId());
                bucketWithValidId.setItems(bucket.getItems());
                bucket = bucketWithValidId;
            }
        } catch (SQLException e) {
            logger.warn("Can`t add bucket");
        }
        String addBucketItemsQuery = "INSERT INTO buckets_items (bucket_id, item_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(addBucketItemsQuery)) {
            statement.setLong(1, bucket.getBucketId());
            for (Item item : bucket.getItems()) {
                statement.setLong(2, item.getItemId());
                statement.execute();
            }
        } catch (SQLException e) {
            logger.warn("Can`t add Item");
        }
        return bucket;
    }

    @Override
    public Bucket get(Long bucketId) {
        Bucket bucket = null;
        String getBucketQuery = "SELECT * FROM buckets AS b INNER JOIN buckets_items AS b_i ON"
                + " b.bucket_id = b_i.bucket_id INNER JOIN items AS i ON b_i.item_id = i.item_id "
                + "WHERE b.bucket_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(getBucketQuery)) {
            statement.setLong(1, bucketId);
            ResultSet rs = statement.executeQuery();
            Long userId = null;
            List<Item> items = new ArrayList<>();
            while (rs.next()) {
                userId = rs.getLong("user_id");
                Item item = new Item(rs.getLong("item_id"));
                item.setName(rs.getString("item_name"));
                item.setPrice(rs.getDouble("price"));
                items.add(item);
            }
            bucket = new Bucket(bucketId, userId);
            bucket.setItems(items);

        } catch (SQLException e) {
            logger.warn("Can`t get bucket by id = " + bucketId);
        }
        return bucket;
    }

    @Override
    public Bucket update(Bucket bucket) {
        String updateBucketQuery = "INSERT INTO orders_items (bucket_id, item_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(updateBucketQuery)) {
            deleteItemsFromBucket(bucket.getBucketId());
            statement.setLong(1, bucket.getBucketId());
            for (Item item : bucket.getItems()) {
                statement.setLong(2, item.getItemId());
                statement.execute();
            }
        } catch (SQLException e) {
            logger.warn("Can`t update order");
        }
        return bucket;
    }

    @Override
    public Bucket delete(Long bucketId) {
        Bucket bucket = get(bucketId);
        String deleteOrderQuery = "DELETE FROM buckets WHERE bucket_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteOrderQuery)) {
            deleteItemsFromBucket(bucketId);
            statement.setLong(1, bucketId);
            statement.execute();
        } catch (SQLException e) {
            logger.warn("Can`t delete bucket");
        }
        return bucket;
    }

    private void deleteItemsFromBucket(Long bucketId) throws SQLException {
        String query = "DELETE FROM buckets_items WHERE bucket_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bucketId);
            statement.execute();
        }
    }
}
