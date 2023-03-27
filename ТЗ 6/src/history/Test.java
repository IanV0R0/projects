package history;

import Tasks.Epic;
import Tasks.Status;
import Tasks.Subtask;
import Tasks.Task;
import manager.Managers;
import manager.TaskManager;

public class Test {
    private final static TaskManager taskManager = Managers.getDefault();

    public static void main(String[] args) {
        //setup
        int firstTaskId = taskManager.nextId();
        int secondTaskId = taskManager.nextId();

        int firstEpicId = taskManager.nextId();
        int secondEpicId = taskManager.nextId();

        int firstSubtaskId = taskManager.nextId();
        int secondSubtaskId = taskManager.nextId();
        int thirdSubtaskId = taskManager.nextId();

        Task firstTask = new Task(firstTaskId, "Task 1", "Description for task 1", Status.NEW);
        Task secondTask = new Task(secondTaskId, "Task 2", "Description for task 2", Status.NEW);

        Epic firstEpic = new Epic(firstEpicId, "Epic 1", "Description for epic 1");
        Epic secondEpic = new Epic(secondEpicId, "Epic 2", "Description for epic 2");

        Subtask firstSubtask = new Subtask(firstSubtaskId, firstEpicId, "Subtask 1", "Description for subtask 1", Status.NEW);
        Subtask secondSubtask = new Subtask(secondSubtaskId, firstEpicId, "Subtask 2", "Description for subtask 2", Status.NEW);
        Subtask thirdSubtask = new Subtask(thirdSubtaskId, firstEpicId, "Subtask 3", "Description for subtask 3", Status.NEW);

        taskManager.save(firstEpic);
        taskManager.save(secondEpic);

        taskManager.save(firstSubtask);
        taskManager.save(secondSubtask);
        taskManager.save(thirdSubtask);

        taskManager.save(firstTask);
        taskManager.save(secondTask);

        // check right order and no duplicates in history - right order = {epic 1}, {epic2}, {task2}, {task1}
        taskManager.getEpicById(secondEpicId);
        taskManager.getEpicById(firstEpicId);
        taskManager.getEpicById(secondEpicId);
        taskManager.getTaskById(secondTaskId);
        taskManager.getTaskById(firstTaskId);
        taskManager.getTaskById(secondTaskId);
        taskManager.getTaskById(secondTaskId);
        taskManager.getTaskById(firstTaskId);

        System.out.println(taskManager.getHistory());
    }
}
