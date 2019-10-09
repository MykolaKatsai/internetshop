package mate.academy.internetshop.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.models.Order;
import mate.academy.internetshop.models.Role;
import mate.academy.internetshop.models.User;
import org.apache.log4j.Logger;

public class UserDaoJdbcImpl extends AbstractDaoClass<User> implements UserDao {
    private static final Logger logger = Logger.getLogger(UserDaoJdbcImpl.class);

    public UserDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User add(User user) {
        String addUserToDbQuery = "INSERT INTO users (name, surname, login, password, token)"
                + " VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(addUserToDbQuery,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getToken());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                user = User.copyUser(rs.getLong(1), user);
            }
        } catch (SQLException e) {
            logger.error("Can`t add user to db");
        }

        String addRolesToUserQuery = "INSERT INTO users_roles (user_id, role_id) "
                + "VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(addRolesToUserQuery)) {
            statement.setLong(1, user.getUserId());
            for (Role role : user.getRoles()) {
                statement.setLong(2, role.getRoleId());
                statement.execute();
            }
        } catch (SQLException e) {
            logger.error("Can`t add role to user");
        }

        String addOrdersToUserQuery = "INSERT INTO orders (order_id, user_id) "
                + "VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(addOrdersToUserQuery)) {
            statement.setLong(2, user.getUserId());
            for (Order order : user.getOrders()) {
                statement.setLong(1, order.getOrderId());
                statement.execute();
            }
        } catch (SQLException e) {
            logger.error("Can`t add order to user");
        }
        return user;
    }

    @Override
    public User get(Long userId) {
        String getUserQuery = "SELECT * FROM users INNER JOIN buckets ON"
                + " users.user_id = buckets.user_id WHERE users.user_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(getUserQuery)) {
            statement.setLong(1, userId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User user = new User(userId);
                user = getRoles(user);
                user.setBucketId(rs.getLong("bucket_id"));
                return createUserFromResultSet(rs, user);
            }
        } catch (SQLException e) {
            logger.error("Can`t get user from db");
        }
        return null;
    }

    @Override
    public User update(User user) {
        String query = "UPDATE users SET name = ?, surname = ?,"
                + " login = ?, password = ?, token = ? WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getToken());
            statement.setLong(6, user.getUserId());
            statement.execute();
        } catch (SQLException e) {
            logger.error("Can`t update user");
        }
        return user;
    }

    @Override
    public User delete(Long userId) {
        User user = get(userId);
        String query = "DELETE FROM users WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        String query = "SELECT * FROM users WHERE login = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                if (!password.equals(rs.getString("password"))) {
                    throw new AuthenticationException("Incorrect login or password!");
                }
                User user = new User(rs.getLong("user_id"));
                user = getRoles(user);
                return createUserFromResultSet(rs, user);
            }
            throw new AuthenticationException("Incorrect login or password!");
        } catch (SQLException e) {
            logger.error("Can`t login user");
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                User user = new User(rs.getLong("user_id"));
                users.add(createUserFromResultSet(rs, user));
            }
        } catch (SQLException e) {
            logger.error("Can`t get users from db");
        }
        return users;
    }

    @Override
    public Optional<User> getByToken(String token) {
        String query = "SELECT * FROM users WHERE token = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, token);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getLong("user_id"));
                user = getRoles(user);
                return Optional.of(createUserFromResultSet(rs, user));
            }
        } catch (SQLException e) {
            logger.error("Can`t get user by token = " + token);
        }
        return Optional.empty();
    }

    private User getRoles(User user) throws SQLException {
        String query = "SELECT * FROM users_roles INNER JOIN roles ON "
                + "users_roles.role_id = roles.role_id WHERE users_roles.user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, user.getUserId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Role role = new Role(rs.getLong("role_id"));
                role.setRoleName(rs.getString("role_name"));
                user.setRole(role);
            }
        }
        return user;
    }

    private User getOrders(User user) {

        return user;
    }

    private static User createUserFromResultSet(ResultSet rs, User user) throws SQLException {
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setToken(rs.getString("token"));
        return user;
    }

}
