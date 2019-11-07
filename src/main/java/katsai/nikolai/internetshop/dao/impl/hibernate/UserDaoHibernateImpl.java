package katsai.nikolai.internetshop.dao.impl.hibernate;

import java.util.List;
import java.util.Optional;

import katsai.nikolai.internetshop.exceptions.AuthenticationException;
import katsai.nikolai.internetshop.dao.UserDao;
import katsai.nikolai.internetshop.lib.Dao;
import katsai.nikolai.internetshop.models.User;
import katsai.nikolai.internetshop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class UserDaoHibernateImpl implements UserDao {
    @Override
    public User add(User user) {
        Transaction transaction = null;
        Long itemId = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            itemId = (Long) session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        user.setUserId(itemId);
        return user;
    }

    @Override
    public User get(Long userId) {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            return session.get(User.class, userId);
        }
    }

    @Override
    public User update(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    @Override
    public User delete(Long userId) {
        Transaction transaction = null;
        User user = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            user = get(userId);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    @Override
    public User login(String login, String password) throws AuthenticationException {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            Query query = session.createQuery("FROM User WHERE login=:login");
            query.setParameter("login", login);
            User user = (User) query.uniqueResult();
            if (user == null || !user.getPassword().equals(password)) {
                throw new AuthenticationException("Incorrect login or password");
            }
            return user;
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            Query<User> query = session.createQuery("from User", User.class);
            return query.getResultList();
        }
    }

    @Override
    public Optional<User> getByToken(String token) {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE token=:token"
                    , User.class);
            query.setParameter("token", token);
            return query.list().stream().findFirst();
        }
    }
}
