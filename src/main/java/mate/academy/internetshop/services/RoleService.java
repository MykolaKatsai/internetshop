package mate.academy.internetshop.services;

import mate.academy.internetshop.models.Role;

public interface RoleService {
    Role get(Long roleId);

    Role getByName(String name);
}
