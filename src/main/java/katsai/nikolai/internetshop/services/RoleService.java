package katsai.nikolai.internetshop.services;

import katsai.nikolai.internetshop.models.Role;

public interface RoleService {
    Role get(Long roleId);

    Role getByName(String name);
}
