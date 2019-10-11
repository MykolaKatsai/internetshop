package mate.academy.internetshop.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", columnDefinition = "INT")
    private Long itemId;
    @Column(name = "item_name")
    private String name;
    @Column(name = "price", columnDefinition = "DECIMAL")
    private Double price;

    public Item() {
    }

    public Item(Long itemId) {
        this.itemId = itemId;
    }

    public Item(String name, Double prise) {
        this.name = name;
        this.price = prise;
        itemId = 0L;
    }

    public Long getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "{Name: " + name + ", prise: " + price + "}";
    }
}
