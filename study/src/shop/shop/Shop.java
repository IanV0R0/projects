package shop.shop;

import shop.product.Category;
import shop.product.Product;

import java.util.*;

public class Shop {
    private final int id;
    private final Category specialization;
    private double sum;
    private double profit;
    private final Map<Integer, Product> productIdToProduct = new HashMap<>();


    public Shop(int id, Category specialization) {
        this.id = id;
        this.specialization = specialization;
    }

    public List<Product> getProductList() {
        return new ArrayList<>(productIdToProduct.values());
    }

    public int addProduct(Product product) {
        if (product.getCategory() != specialization) throw new IllegalArgumentException("Product category doesn't coincide with shop specialization");

        productIdToProduct.put(product.getId(), product);
        product.setShopId(id);
        sum += product.getPrice();

        return product.getId();
    }

    public void sellProduct(int productId) {
        Product productToSell = productIdToProduct.remove(productId);
        sum -= productToSell.getPrice();
        profit += productToSell.getPrice();
        productToSell.setShopId(-1);
    }

    public int getId() {
        return id;
    }

    public Category getSpecialization() {
        return specialization;
    }

    public double getSum() {
        return sum;
    }

    public double getProfit() {
        return profit;
    }
}
