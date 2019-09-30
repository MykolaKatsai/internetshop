package mate.academy.internetshop.models;

public class Item {
    private static Long newItemId = 0L;
    private final Long itemId;
    private String name;
    private Double price;

    public Item(String name, Double prise) {
        this.name = name;
        this.price = prise;
        itemId = newItemId++;
    }

    public Item(Long itemId) {
        this.itemId = itemId;
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

    @Override
    public String toString() {
        return "{Name: " + name + ", prise: " + price + "}";
    }
}
