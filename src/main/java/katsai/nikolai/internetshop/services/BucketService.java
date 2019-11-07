package katsai.nikolai.internetshop.services;

import java.util.List;

import katsai.nikolai.internetshop.models.Bucket;
import katsai.nikolai.internetshop.models.Item;

public interface BucketService {
    Bucket add(Bucket bucket);

    Bucket get(Long bucketId);

    Bucket update(Bucket bucket);

    Bucket delete(Long bucketId);

    Bucket addItem(Long bucketId, Long itemId);

    Bucket clear(Long bucketId);

    List<Item> getAllItems(Long bucketId);
}
