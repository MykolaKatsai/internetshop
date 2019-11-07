package katsai.nikolai.internetshop.dao;

import katsai.nikolai.internetshop.models.Bucket;

public interface BucketDao {
    Bucket add(Bucket bucket);

    Bucket get(Long bucketId);

    Bucket update(Bucket bucket);

    Bucket delete(Long bucketId);
}
