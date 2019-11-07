package katsai.nikolai.internetshop.dao.impl.hibernate;

import java.util.List;

import katsai.nikolai.internetshop.dao.OrderDao;
import katsai.nikolai.internetshop.models.Order;
import katsai.nikolai.internetshop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class OrderDaoHibernateImpl implements OrderDao {
    @Override
    public Order add(Order order) {
        Transaction transaction = null;
        Long orderId = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            orderId = (Long) session.save(order);
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
        order.setOrderId(orderId);
        return order;
    }

    @Override
    public Order get(Long orderId) {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            return session.get(Order.class, orderId);
        }
    }

    @Override
    public Order update(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(order);
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
        return order;
    }

    @Override
    public Order delete(Long orderId) {
        Transaction transaction = null;
        Order order = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            order = get(orderId);
            session.delete(order);
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
        return order;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            Query<Order> query = session.createQuery("from Order where user.userId=:userId", Order.class);
            query.setParameter("userId", userId);
            return query.getResultList();
        }
    }
}
