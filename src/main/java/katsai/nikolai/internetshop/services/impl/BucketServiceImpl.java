package katsai.nikolai.internetshop.services.impl;

import java.util.ArrayList;
import java.util.List;

import katsai.nikolai.internetshop.dao.BucketDao;
import katsai.nikolai.internetshop.dao.ItemDao;
import katsai.nikolai.internetshop.lib.Inject;
import katsai.nikolai.internetshop.lib.Service;
import katsai.nikolai.internetshop.models.Bucket;
import katsai.nikolai.internetshop.models.Item;
import katsai.nikolai.internetshop.services.BucketService;

@Service
public class BucketServiceImpl implements BucketService {
    @Inject
    private static BucketDao bucketDao;

    @Inject
    private static ItemDao itemDao;

    @Override
    public Bucket add(Bucket bucket) {
        return bucketDao.add(bucket);
    }

    @Override
    public Bucket get(Long bucketId) {
        return bucketDao.get(bucketId);
    }

    @Override
    public Bucket update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public Bucket delete(Long bucketId) {
        return bucketDao.delete(bucketId);
    }

    @Override
    public Bucket addItem(Long bucketId, Long itemId) {
        Bucket bucket = bucketDao.get(bucketId);
        Item item = itemDao.get(itemId);
        bucket.getItems().add(item);
        return bucketDao.update(bucket);
    }

    @Override
    public Bucket clear(Long bucketId) {
        Bucket bucket = bucketDao.get(bucketId);
        bucket.setItems(new ArrayList<>());
        bucketDao.update(bucket);
        return bucket;
    }

    @Override
    public List<Item> getAllItems(Long bucketId) {
        return bucketDao.get(bucketId).getItems();
    }
}
