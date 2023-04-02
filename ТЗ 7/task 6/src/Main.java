import Tasks.Epic;
import Tasks.Status;
import Tasks.Subtask;
import Tasks.Task;
import manager.FileBackedTasksManager;
import manager.Managers;
import manager.TaskManager;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        TaskManager tm = Managers.getInMemoryManager();

        Task taskLowPriority = new Task(tm.nextId(), "Task", "Task low priority", Status.NEW, 120, LocalDateTime.of(2025, 12, 1, 12, 0));
        Task taskMiddlePriority = new Task(tm.nextId(), "Task", "Task middle priority", Status.NEW, 500, LocalDateTime.of(2024, 12, 1, 12, 0));
        Task taskHighPriority = new Task(tm.nextId(), "Task", "Task high priority", Status.NEW, 120, LocalDateTime.of(2023, 12, 1, 12, 0));

        tm.save(taskMiddlePriority);
        tm.save(taskHighPriority);
        tm.save(taskLowPriority);

        System.out.println(tm.getPrioritizedTasks());

        System.out.println("------------------------------------------------------------------------------------");

        TaskManager otherTm = Managers.getInMemoryManager();

        Task existing = new Task(tm.nextId(), "Task", "Task high priority", Status.NEW, 120, LocalDateTime.of(2023, 12, 1, 12, 0));
        Task intersect = new Task(tm.nextId(), "Task", "Task high priority", Status.NEW, 120, LocalDateTime.of(2023, 12, 1, 13, 0));

        otherTm.save(existing);
        otherTm.save(intersect);
    }
}