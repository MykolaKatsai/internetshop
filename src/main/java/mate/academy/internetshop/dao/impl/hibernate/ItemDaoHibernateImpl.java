package mate.academy.internetshop.dao.impl.hibernate;

import java.sql.SQLException;

import java.util.List;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.models.Item;
import mate.academy.internetshop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class ItemDaoHibernateImpl implements ItemDao {
    @Override
    public Item add(Item item) {
        Transaction transaction = null;
        Long itemId = null;
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            transaction = session.beginTransaction();
            itemId = (Long) session.save(item);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        item.setItemId(itemId);
        return item;
    }

    @Override
    public Item get(Long itemId) {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            return session.get(Item.class, itemId);
        }
    }

    @Override
    public Item update(Item item) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(item);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return item;
    }

    @Override
    public Item delete(Long itemId) {
        Transaction transaction = null;
        Item item = null;
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            transaction = session.beginTransaction();
            item = get(itemId);
            item.setItemId(itemId);
            session.delete(item);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return item;
    }

    @Override
    public List<Item> getAllItems() throws SQLException {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            return session.createCriteria(Item.class).list();
        }
    }
}
