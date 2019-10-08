package mate.academy.internetshop.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mate.academy.internetshop.dao.RoleDao;
import mate.academy.internetshop.models.Role;
import org.apache.log4j.Logger;

public class RoleDaoJdbcImpl extends AbstractDaoClass<Role> implements RoleDao {
    private static Logger logger = Logger.getLogger(RoleDaoJdbcImpl.class);

    public RoleDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Role get(Long roleId) {
        String query = "SELECT * FROM roles WHERE role_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, roleId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Role role = new Role(rs.getLong("role_id"));
                role.setRoleName(rs.getString("role_name"));
                return role;
            }
        } catch (SQLException e) {
            logger.warn("Can`t get role by id - " + roleId);
        }
        return null;
    }

    @Override
    public Role getByName(String name) {
        String query = "SELECT * FROM roles WHERE role_name = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Role role = new Role(rs.getLong("role_id"));
                role.setRoleName(rs.getString("role_name"));
                return role;
            }
        } catch (SQLException e) {
            logger.warn("Can`t get role by name - " + name);
        }
        return null;
    }
}
