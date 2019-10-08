package mate.academy.internetshop.dao;

import mate.academy.internetshop.models.Role;

public interface RoleDao {
    Role get(Long roleId);

    Role getByName(String name);
}
