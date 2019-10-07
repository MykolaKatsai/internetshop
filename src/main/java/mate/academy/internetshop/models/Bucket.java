package mate.academy.internetshop.models;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private final Long bucketId;
    private Long userId;
    private List<Item> items;

    public Bucket(Long userId) {
        bucketId = 0L;
        this.userId = userId;
        items = new ArrayList<>();
    }

    public Bucket(Long bucketId, Long userId) {
        this.bucketId = bucketId;
        this.userId = userId;
        items = new ArrayList<>();
    }

    public Long getBucketId() {
        return bucketId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
