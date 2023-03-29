import java.util.Random;
import java.util.Scanner;

public class Practicum {
    public static void main(String[] args) {
        Random random = new Random();
        int target = random.nextInt(1000) + 1;
        System.out.println("Я загадал число. Попробуйте угадать!");

        Scanner s = new Scanner(System.in);
        int userGuess = -1;
        userGuess = s.nextInt();
        // Считывайте числа от пользователя пока не найдёте число, равное target
        while (userGuess != target) {
            userGuess = s.nextInt();
            if (userGuess  > target) {
                System.out.println("Ваше число больше");
            } else if (userGuess < target) {
                System.out.println("Ваше число меньше");
            } else if (userGuess == target) {
                System.out.println("Правильный ответ");

            }
        }
        }
    }