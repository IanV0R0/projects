import Tasks.Epic;
import Tasks.Status;
import Tasks.Subtask;
import Tasks.Task;
import manager.FileBackedTasksManager;
import manager.Managers;
import manager.TaskManager;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
        TaskManager taskManager = Managers.getDefault();

        int epic1Id = taskManager.nextId();
        int epic2Id = taskManager.nextId();

        int subtask1Id = taskManager.nextId();
        int subtask2Id = taskManager.nextId();
        int subtask3Id = taskManager.nextId();

        int task1Id = taskManager.nextId();
        int task2Id = taskManager.nextId();
        int task3Id = taskManager.nextId();

        taskManager.save(new Epic(epic1Id, "Переезд", "Надо бы переехать."));
        taskManager.save(new Epic(epic2Id, "Важный эпик", "Просто эпик."));

        taskManager.save(new Subtask(subtask1Id, epic1Id, "Собрать коробки", "-", Status.NEW));
        taskManager.save(new Subtask(subtask2Id, epic1Id, "Упаковать кошку", "-", Status.NEW));
        taskManager.save(new Subtask(subtask3Id, epic2Id, "Задача 1", "-", Status.NEW));

        taskManager.save(new Task(task1Id, "Таска 1", "Сделать то.", Status.NEW));
        taskManager.save(new Task(task2Id, "Таска 2", "Сделать сё.", Status.NEW));
        taskManager.save(new Task(task3Id, "Лишняя для истории", "...", Status.NEW));

        System.out.println(taskManager.getAllEpics());
        System.out.println(taskManager.getAllSubtasks());
        System.out.println(taskManager.getAllTasks());
        System.out.println("------------------------------------------");

        taskManager.save(new Subtask(subtask2Id, epic1Id, "Упаковать кошку", "-", Status.IN_PROGRESS));
        taskManager.save(new Subtask(subtask3Id, epic2Id, "Задача 1", "-", Status.DONE));

        System.out.println(taskManager.getAllEpics());
        System.out.println("------------------------------------------");

        taskManager.deleteEpicById(epic2Id);
        taskManager.deleteSubtaskById(subtask2Id);
        System.out.println(taskManager.getAllEpics());
        System.out.println(taskManager.getAllSubtasks());

        System.out.println("------------------------------------------");
        taskManager.getTaskById(task3Id);
        for (int i = 0; i < 5; i++) {
            taskManager.getTaskById(task1Id);
            taskManager.getTaskById(task2Id);
        }
        System.out.println(taskManager.getHistory());
        System.out.println("------------------------------------------");
        System.out.println("Загрузить данные из файла и распечатать результат");
        System.out.println("------------------------------------------");
        TaskManager newTaskManager = FileBackedTasksManager.loadFromFile("file.csv");
        System.out.println(newTaskManager.getAllEpics());
        System.out.println(newTaskManager.getAllSubtasks());
        System.out.println(newTaskManager.getAllTasks());
        System.out.println("------------------------------------------");
        System.out.println(newTaskManager.getHistory());
    }
}
