import java.util.Objects;

public class Potato implements Comparable<Potato> {

    public final int id;

    /**
     * Масса
     */
    public final int weight;

    /**
     * Длина
     */
    public final int length;

    /**
     * Ширина
     */
    public final int girth;

    public Potato(int id, int weight, int length, int girth) {
        this.id = id;
        this.weight = weight;
        this.length = length;
        this.girth = girth;
    }

    public int calculateAlpha(Potato o) {
        return (int) (o.weight * 0.5 + o.length * 0.65 + o.girth * 0.80);
    }

    @Override
    public int compareTo(Potato o) {
    return Integer.compare(this.calculateAlpha(this), o.calculateAlpha(o));

        // Сравните картофелины по альфа характеристике
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Potato potato = (Potato) o;
        return id == potato.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Potato{" +
                "id=" + id +
                ", weight=" + weight +
                ", length=" + length +
                ", girth=" + girth +
                '}';
    }
}








/*
Чтобы вычислить альфа-характеристику картофеля, удобно использовать вспомогательный метод
public int calculateAlpha(Potato o). Его тело будет таким
 —{return (int) (o.weight * 0.5 + o.length * 0.65 + o.girth * 0.80);}.
Для нахождения минимальной и максимальной картошки можно использовать такой способ: взять сначала
минимальный и максимальный элемент при помощи методов Collections.max() и Collections.min(),
 удалить их из списка, а затем взять ещё раз. Помните, что для начала надо сделать изменяемую копию
 списка при помощи new ArrayList<>(potatoes).
Есть и второй способ по получению двух максимальных и минимальных элементов. Сначала отсортируйте
весь картофель при помощи Collections.sort(..), а затем возьмите элементы с правильными индексами.
 */

/*
В лаборатории высокотехнологичного агрохолдинга разработали уникальную методику селекции
картошки. Каждой картофелине из нового урожая присваивается её уникальный идентификатор,
 а также измеряются основные характеристики — масса, длина и ширина. Затем для каждой картофелины
  вычисляется альфа-характеристика, равная 50% от массы, 65% от длины и 80% от ширины.
После этого, выбираются две картошки с самым большим показателям альфа-характеристик и две с
самым маленьким. Именно они отправляются на главное испытание, по которому производится вывод,
 поступит ли этот сорт картошки в продажу или нет.
Вам необходимо реализовать для класса картошки Potato интерфейс Comparable<Potato>, который сравнит
две картофелины по их альфа-характеристикам.
После этого допишите метод **findPotatoesForExperiment класса PotatoLaboratory, чтобы он находил две
 самые большие и две самые маленькие по альфа-характеристикам картошки. Результат выведите в порядке
 от меньшего к большему.
 */