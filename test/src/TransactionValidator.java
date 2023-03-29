public class TransactionValidator {
    public static final int MIN_AMOUNT = 1;
    public static final int MAX_AMOUNT = 5000;
    // объявите константы

    public static boolean isValidAmount(double amount) {// объявите метод isValidAmount()
        if (amount < MIN_AMOUNT) {
            System.out.println("Минимальная сумма перевода: " + MIN_AMOUNT + " р. Попробуйте ещё раз!");
            return false;
        } else if (amount > MAX_AMOUNT) {
            System.out.println("Максимальная сумма перевода: " + MAX_AMOUNT + " р. Попробуйте ещё раз!");
            return false;
        }
        return true;
    }
}

/*
Метод isValidAmount нужно сделать статическим.
Метод isValidAmount  на вход будет принимать сумму перевода, а на выходе возвращать true, если все проверки успешно
пройдены, или false, если обнаружены ошибки.
Для объявления констант используйте модификаторы public static final.
Сумма перевода может содержать цифры после запятой.
Константы должны быть не только в выражении if, но и в сообщениях об ошибке.

 */