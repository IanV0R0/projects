import java.util.HashMap;
import java.util.Map;

public class Practicum {
    private Map<String, Integer> orders = new HashMap<>();

    public static void main(String[] args) {
        Practicum pizzeria = new Practicum();
        pizzeria.openPizzeria();
        pizzeria.printStatistics();
    }

    private void openPizzeria() {
        newOrder("Леонардо");
        newOrder("Донателло");
        newOrder("Рафаэль");
        newOrder("Леонардо");
        newOrder("Микеланджело");
        newOrder("Шреддер");
        newOrder("Донателло");
    }

    private void newOrder(String clientName) {
        Integer previousClientOrders = orders.getOrDefault(clientName, 0);
        orders.put(clientName, previousClientOrders + 1);
    }

    private void printStatistics() {
        for (Map.Entry<String, Integer> entry : orders.entrySet()) {
            System.out.println("Заказов от " + entry.getKey() + ": " + entry.getValue());
//            System.out.printf("Заказов от %s: %d%n", entry.getKey(), entry.getValue());
        }
//        Integer totalOrders = orders.values().stream().reduce(Integer::sum).get();
        int totalOrders = 0;
        for (Integer clientOrders : orders.values()) {
            totalOrders += clientOrders;
        }
        System.out.printf("Всего заказов: %d%n", totalOrders);
    }
}