package katsai.nikolai.internetshop.dao.impl;

import java.util.NoSuchElementException;

import katsai.nikolai.internetshop.dao.BucketDao;
import katsai.nikolai.internetshop.lib.Dao;
import katsai.nikolai.internetshop.models.Bucket;
import katsai.nikolai.internetshop.storage.Storage;

@Dao
public class BucketDaoImpl implements BucketDao {
    @Override
    public Bucket add(Bucket bucket) {
        Storage.bucketsStorage.add(bucket);
        return bucket;
    }

    @Override
    public Bucket get(Long bucketId) {
        return Storage.bucketsStorage.stream()
                .filter(bucket -> bucket.getBucketId() == bucketId)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Bucket update(Bucket bucket) {
        Bucket oldBucket = get(bucket.getBucketId());
        if (oldBucket == null) {
            throw new NoSuchElementException();
        }
        oldBucket.setItems(bucket.getItems());
        return bucket;
    }

    @Override
    public Bucket delete(Long bucketId) {
        Bucket bucket = get(bucketId);
        Storage.bucketsStorage.removeIf(b -> b.getBucketId().equals(bucketId));
        return bucket;
    }
}
