public class Task {
    public static TaskPriority priority;
    private static String description;

    public Task(TaskPriority priority, String description ) {
        this.priority = priority;
        this.description = description;
    }

    // добавьте метод get для приоритета
  public TaskPriority getPriority() {
      return priority;
  }
    public String getDescription() {
        return description;
    }
}
/*Перед вами часть программы для хранения списка задач с приоритетом. Приоритет (англ. task priority)
 может быть:
высокий (англ. high) — TaskPriority.HIGH,
средний (англ. medium) — TaskPriority.MEDIUM,
низкий (англ. low) — TaskPriority.LOW.
Вам нужно реализовать поиск задач с наивысшим приоритетом из предложенного списка.
 */

/*
Для работы со списками вам понадобятся пакеты ArrayList и List.
Для хранения значений приоритета создайте перечисление TaskPriority.
Цикл for поможет перебрать все дела в списке.
Для поиска задач с максимальным приоритетом TaskPriority.HIGH воспользуйтесь оператором ==.
 */