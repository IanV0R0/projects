package shop.product;

public class ProductFactory {
    private static int idCounter = 1;

    public static Product getFoodProduct(double price, String name) {
        Product foodProduct = new Product(idCounter, Category.FOOD, price, name);
        idCounter++;
        return foodProduct;
    }

    public static Product getTechProduct(double price, String name) {
        Product techProduct = new Product(idCounter, Category.TECH, price, name);
        idCounter++;
        return techProduct;
    }

    public static Product getSportProduct(double price, String name) {
        Product sportProduct = new Product(idCounter, Category.SPORT, price, name);
        idCounter++;
        return sportProduct;
    }

    public static Product getClothesProduct(double price, String name) {
        Product clothesProduct = new Product(idCounter, Category.CLOTHES, price, name);
        idCounter++;
        return clothesProduct;
    }

}
