package mate.academy.internetshop.services.impl;

import mate.academy.internetshop.dao.RoleDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.models.Role;
import mate.academy.internetshop.services.RoleService;

public class RoleServiceImpl implements RoleService {
    @Inject
    private static RoleDao roleDao;

    @Override
    public Role get(Long roleId) {
        return roleDao.get(roleId);
    }

    @Override
    public Role getByName(String name) {
        return roleDao.getByName(name);
    }
}
