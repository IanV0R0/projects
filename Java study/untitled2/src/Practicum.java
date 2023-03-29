import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Practicum {
    private static Set<Letter> letters = new LinkedHashSet<>();

    public static void main(String[] args) {
        // информация о письмах (в порядке занесения в систему)
        letters.add(new Letter("Джон Смит", LocalDate.of(2021, 7, 7), "текст письма №1 ..."));
        letters.add(new Letter("Аманда Линс", LocalDate.of(2021, 6, 17), "текст письма №2 ..."));
        letters.add(new Letter("Джо Кью", LocalDate.of(2021, 7, 5), "текст письма №3 ..."));
        letters.add(new Letter("Мишель Фернандес", LocalDate.of(2021, 8, 23), "текст письма №4 ..."));

        printOrderedById(letters);
        printOrderedByDateReceived(letters);
    }

    private static void printOrderedById(Set<Letter> letters) {
        System.out.println("Все письма с сортировкой по ID: ");

        for (Letter letter : letters) {
            System.out.println("    * Письмо от " + letter.authorName + " поступило " + letter.getDateReceived());
        }
    }

    private static void printOrderedByDateReceived(Set<Letter> letters) {
        System.out.println("Все письма с сортировкой по дате получения: ");

            Set<Letter> sortByDate = new TreeSet<>(Comparator.comparing(l -> l.dateReceived));
        sortByDate.addAll(letters);
        for (Letter sortByDates : sortByDate) {
            System.out.println("    * Письмо от " + sortByDates.authorName + " поступило " + sortByDates.getDateReceived());
        }
    }

    static class Letter {
        public String authorName;      // имя отправителя
        private LocalDate dateReceived; // дата получения письма
        public String text;            // текст письма

        public Letter(String senderName, LocalDate dateReceived, String text) {
            this.authorName = senderName;
            this.setDateReceived(dateReceived);
            this.text = text;
        }

        public LocalDate getDateReceived() {
            return dateReceived;
        }

        public void setDateReceived(LocalDate dateReceived) {
            this.dateReceived = dateReceived;
        }
    }
}