import shop.product.Category;
import shop.product.Product;
import shop.product.ProductFactory;
import shop.shop.Shop;

public class Main {
    public static void main(String[] args) {
        Product boxOfApples = ProductFactory.getFoodProduct(100.0, "Box of apples");
        Product boxOfMilk = ProductFactory.getFoodProduct(250.0, "Box of milk");
        Product anotherBoxOfApples = ProductFactory.getFoodProduct(100.0, "Box of apples");
        Product bike = ProductFactory.getSportProduct(5000.0, "Sport bike");

        Shop foodShop = new Shop(1, Category.FOOD);

        //3 products, sum = 450, profit = 0, boxOfApples.shopId = 1, boxOfMilk.shopId = 1, anotherBoxOfApples.shopId = 1 - CHECK
        foodShop.addProduct(anotherBoxOfApples);
        foodShop.addProduct(boxOfApples);
        foodShop.addProduct(boxOfMilk);
        System.out.println(foodShop.getProductList());
        System.out.println("Sum=" + foodShop.getSum());
        System.out.println("Profit=" + foodShop.getProfit());

        System.out.println("----------------------------------------------");

        //2 products, sum = 350, profit = 100, boxOfApples.shopId = -1, boxOfMilk.shopId = 1, anotherBoxOfApples.shopId = 1
        foodShop.sellProduct(boxOfApples.getId());
        System.out.println(foodShop.getProductList());
        System.out.println("Sum=" + foodShop.getSum());
        System.out.println("Profit=" + foodShop.getProfit());
        System.out.println("Box of apples shopId=" + boxOfApples.getShopId());

        System.out.println("----------------------------------------------");

        //exception thrown TODO explain difference
        try {
            foodShop.addProduct(bike);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
