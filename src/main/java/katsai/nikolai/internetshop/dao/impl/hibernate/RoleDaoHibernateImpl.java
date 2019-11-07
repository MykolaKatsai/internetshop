package katsai.nikolai.internetshop.dao.impl.hibernate;

import katsai.nikolai.internetshop.util.HibernateUtil;
import katsai.nikolai.internetshop.dao.RoleDao;
import katsai.nikolai.internetshop.models.Role;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class RoleDaoHibernateImpl implements RoleDao {
    @Override
    public Role get(Long roleId) {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            Query<Role> query = session.createQuery("from Role where roleId=:roleId", Role.class);
            query.setParameter("roleId", roleId);
            return query.getSingleResult();
        }
    }

    @Override
    public Role getByName(String name) {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            Query<Role> query = session.createQuery("from Role where roleName=:roleName", Role.class);
            query.setParameter("roleName", name);
            return query.getSingleResult();
        }
    }
}
