class Appender {

    public static int add(int a, int b) {
        return a + b;
    }
    public static double add(double a, double b) {
        return a + b;
    }

    public static boolean  add(boolean b, boolean b1) {
        return true || false;
    }

    public static String add(String кофе, String машина) {
        return кофе + машина;
    }
}

public class Practicum {
    public static void main(String[] args) {
        System.out.println("2 + 3 = " + Appender.add(2, 3));
        System.out.println("9.4 + 1.2 = " + Appender.add(9.4, 1.2));
        System.out.println("Кофе + машина = " + Appender.add("Кофе", "машина"));
        System.out.println("true + false = " + Appender.add(true, false));
    }
}