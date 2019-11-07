package katsai.nikolai.internetshop.dao;

import katsai.nikolai.internetshop.models.Role;

public interface RoleDao {
    Role get(Long roleId);

    Role getByName(String name);
}
