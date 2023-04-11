import Tasks.Status;
import Tasks.Task;
import manager.Managers;
import manager.TaskManager;

import java.io.IOException;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        TaskManager tm = Managers.getHttpManager();

        Task taskLowPriority = new Task(tm.nextId(), "Task", "Task low priority", Status.NEW, 120, LocalDateTime.of(2025, 12, 1, 12, 0));
        Task taskMiddlePriority = new Task(tm.nextId(), "Task", "Task middle priority", Status.NEW, 500, LocalDateTime.of(2024, 12, 1, 12, 0));
        Task taskHighPriority = new Task(tm.nextId(), "Task", "Task high priority", Status.NEW, 120, LocalDateTime.of(2023, 12, 1, 12, 0));

        tm.save(taskMiddlePriority);
        tm.save(taskHighPriority);
        tm.save(taskLowPriority);

        System.out.println("------------------------------------------------------------------------------------");

        TaskManager otherTm = Managers.getHttpManager();

        System.out.println(otherTm.getTaskById(taskLowPriority.getId()));
        System.out.println(otherTm.getTaskById(taskMiddlePriority.getId()));
        System.out.println(otherTm.getTaskById(taskHighPriority.getId()));

        System.out.println("------------------------------------------------------------------------------------");

        otherTm.deleteTaskById(taskLowPriority.getId());
        System.out.println(otherTm.getTaskById(taskLowPriority.getId()));
    }
}