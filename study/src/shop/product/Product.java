package shop.product;

import java.util.Objects;

public class Product {
    private final int id;
    private int shopId = -1;
    private final Category category;
    private final double price;
    private final String name;

    Product(int id, Category category, double price, String name) {
        this.id = id;
        this.category = category;
        this.price = price;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getShopId() {
        return shopId;
    }

    public Category getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", shopId=" + shopId +
                ", category=" + category +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (shopId != product.shopId) return false;
        if (Double.compare(product.price, price) != 0) return false;
        if (category != product.category) return false;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + shopId;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
