import java.util.Scanner;

public class Practicum {

    public static double amount;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Пожалуйста, введите сумму перевода в рублях.");
        double amount = scanner.nextDouble();// считайте сумму перевода

        boolean isValid = TransactionValidator.isValidAmount(amount);// добавьте вызов метод isValidAmount
        if (isValid)
            System.out.println("Спасибо! Ваш перевод на сумму " + amount + " р. успешно выполнен.");
    }
}

/* Во всех банковских приложениях есть возможность перевода денег. Как правило, прежде чем выполнить перевод,
 система должна проверить, правильно ли введены все необходимые данные.

Ваша задача — реализовать класс TransactionValidator, в котором будет находиться логика проверки суммы перевода.
        Минимальная сумма перевода — MIN_AMOUNT (1 р.), максимальная сумма перевода — MAX_AMOUNT (5000 р.).

 */