package katsai.nikolai.internetshop.dao.impl.hibernate;

import katsai.nikolai.internetshop.dao.BucketDao;
import katsai.nikolai.internetshop.lib.Dao;
import katsai.nikolai.internetshop.models.Bucket;
import katsai.nikolai.internetshop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class BucketDaoHibernateImpl implements BucketDao {
    @Override
    public Bucket add(Bucket bucket) {
        Transaction transaction = null;
        Session session = null;
        Long bucketId = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            bucketId = (Long) session.save(bucket);
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
        bucket.setBucketId(bucketId);
        return bucket;
    }

    @Override
    public Bucket get(Long bucketId) {
        try (Session session = HibernateUtil.sessionFactory().openSession()) {
            return session.get(Bucket.class, bucketId);
        }
    }

    @Override
    public Bucket update(Bucket bucket) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(bucket);
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
        return bucket;
    }

    @Override
    public Bucket delete(Long bucketId) {
        Transaction transaction = null;
        Bucket bucket = null;
        Session session = null;
        try {
            session = HibernateUtil.sessionFactory().openSession();
            transaction = session.beginTransaction();
            bucket = get(bucketId);
            session.delete(bucket);
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
        return bucket;
    }
}
