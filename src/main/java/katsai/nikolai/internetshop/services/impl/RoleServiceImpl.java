package katsai.nikolai.internetshop.services.impl;

import katsai.nikolai.internetshop.dao.RoleDao;
import katsai.nikolai.internetshop.lib.Inject;
import katsai.nikolai.internetshop.services.RoleService;
import katsai.nikolai.internetshop.models.Role;

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
