package test.manager;

import Tasks.Epic;
import Tasks.Status;
import Tasks.Subtask;
import Tasks.Task;
import manager.TaskManager;
import manager.exception.TasksTimeIntersectionException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

abstract class TaskManagerTest<T extends TaskManager> {
    private static LocalDateTime startTime;

    T taskManager;

    @BeforeAll
    static void beforeAll() {
        startTime = LocalDateTime.of(2023, 5, 1, 12, 0);
    }

    @Test
    void nextId() {
        int prev = taskManager.nextId();
        int next = taskManager.nextId();

        assertEquals(1, next - prev);
    }

    @Test
    void saveTask() {
        final Task task = new Task(taskManager.nextId(), "Task", "Task Description", Status.NEW, 500, startTime);

        taskManager.save(task);

        final Task savedTask = taskManager.getTaskById(task.getId());
        assertNotNull(savedTask);
        assertEquals(task, savedTask);

        final Collection<Task> savedTasks = taskManager.getAllTasks();
        assertNotNull(savedTasks);
        assertEquals(1, savedTasks.size());
        assertTrue(savedTasks.contains(task));
    }

    @Test
    void saveEpic() {
        final Epic epic = new Epic(taskManager.nextId(), "Epic", "Epic Description");
        final Subtask inProgressSubtask = new Subtask(taskManager.nextId(), epic.getId(), "In progress subtask", "Subtask description", Status.IN_PROGRESS, 500, startTime);
        epic.addSubtask(inProgressSubtask);

        taskManager.save(epic);

        final Epic savedEpic = taskManager.getEpicById(epic.getId());
        assertNotNull(savedEpic);
        assertEquals(epic, savedEpic);
        assertEquals(epic.getStatus(), savedEpic.getStatus());
        assertEquals(1, epic.getSubtasks().size());
        assertTrue(epic.getSubtasks().contains(inProgressSubtask));

        final Collection<Epic> savedEpics = taskManager.getAllEpics();
        assertNotNull(savedEpics);
        assertEquals(1, savedEpics.size());
        assertTrue(savedEpics.contains(epic));
    }

    @Test
    void saveSubtask() {
        final int fakeEpicId = taskManager.nextId();
        final Subtask subtask = new Subtask(taskManager.nextId(), fakeEpicId, "Subtask", "Test Subtask Description", Status.NEW, 500, startTime);

        taskManager.save(subtask);

        final Subtask savedSubtask = taskManager.getSubtaskById(subtask.getId());
        assertNotNull(savedSubtask);
        assertEquals(subtask, savedSubtask);

        final Collection<Subtask> savedSubtasks = taskManager.getAllSubtasks();
        assertNotNull(savedSubtasks);
        assertEquals(1, savedSubtasks.size());
        assertTrue(savedSubtasks.contains(subtask));
    }

    @Test
    void deleteTaskById() {
        final Task task = new Task(taskManager.nextId(), "Task", "Task Description", Status.NEW, 500, startTime);

        taskManager.save(task);
        taskManager.deleteTaskById(task.getId());

        assertNull(taskManager.getTaskById(task.getId()));

        final Collection<Task> savedTasks = taskManager.getAllTasks();
        assertNotNull(savedTasks);
        assertEquals(0, savedTasks.size());
        assertFalse(savedTasks.contains(task));
    }

    @Test
    void deleteEpicById() {
        final Epic epic = new Epic(taskManager.nextId(), "Epic", "Epic Description");
        final Subtask inProgressSubtask = new Subtask(taskManager.nextId(), epic.getId(), "In progress subtask", "Subtask description", Status.IN_PROGRESS, 500, startTime);
        epic.addSubtask(inProgressSubtask);

        taskManager.save(epic);
        taskManager.deleteEpicById(epic.getId());

        assertNull(taskManager.getEpicById(epic.getId()));
        assertNull(taskManager.getSubtaskById(inProgressSubtask.getId()));

        final Collection<Epic> savedEpics = taskManager.getAllEpics();
        assertNotNull(savedEpics);
        assertEquals(0, savedEpics.size());
        assertFalse(savedEpics.contains(epic));
    }

    @Test
    void deleteSubtaskById() {
        final int fakeEpicId = taskManager.nextId();
        final Subtask subtask = new Subtask(taskManager.nextId(), fakeEpicId, "Subtask", "Test Subtask Description", Status.NEW, 500, startTime);

        taskManager.save(subtask);
        taskManager.deleteSubtaskById(subtask.getId());

        assertNull(taskManager.getSubtaskById(subtask.getId()));

        final Collection<Subtask> savedSubtasks = taskManager.getAllSubtasks();
        assertNotNull(savedSubtasks);
        assertEquals(0, savedSubtasks.size());
        assertFalse(savedSubtasks.contains(subtask));
    }

    @Test
    void getAllTasks() {
        final Task task = new Task(taskManager.nextId(), "Task", "Task Description", Status.NEW, 500, startTime);
        final Task anotherTask = new Task(taskManager.nextId(), "Another Task", "Another Task Description", Status.IN_PROGRESS, 500, startTime);

        taskManager.save(task);
        taskManager.save(anotherTask);

        final Collection<Task> savedTasks = taskManager.getAllTasks();
        assertNotNull(savedTasks);
        assertEquals(2, savedTasks.size());
        assertTrue(savedTasks.containsAll(List.of(task, anotherTask)));
    }

    @Test
    void getAllEpics() {
        final Epic epic = new Epic(taskManager.nextId(), "Epic", "Epic Description");
        final Epic anotherEpic = new Epic(taskManager.nextId(), "Another Epic", "Another Epic Description");
        final Epic epicWithoutSubtasks = new Epic(taskManager.nextId(), "Epic Without Subtasks", "Epic Without Subtask Description");
        final Subtask subtask = new Subtask(taskManager.nextId(), epic.getId(), "Subtask", "Test Subtask Description", Status.IN_PROGRESS, 500, startTime);
        final Subtask anotherSubtask = new Subtask(taskManager.nextId(), anotherEpic.getId(), "Another Subtask", "Another Test Subtask Description", Status.DONE, 500, startTime);
        epic.addSubtask(subtask);
        anotherEpic.addSubtask(anotherSubtask);


        taskManager.save(epic);
        taskManager.save(anotherEpic);
        taskManager.save(epicWithoutSubtasks);

        final Collection<Epic> savedEpics = taskManager.getAllEpics();
        assertNotNull(savedEpics);
        assertEquals(3, savedEpics.size());
        assertTrue(savedEpics.containsAll(List.of(epic, anotherEpic, epicWithoutSubtasks)));
    }

    @Test
    void getAllSubtasks() {
        final int fakeEpicId = taskManager.nextId();
        final Subtask subtask = new Subtask(taskManager.nextId(), fakeEpicId, "Subtask", "Test Subtask Description", Status.NEW, 500, startTime);
        final Subtask anotherSubtask = new Subtask(taskManager.nextId(), fakeEpicId, "Subtask", "Test Subtask Description", Status.NEW, 500, startTime);

        taskManager.save(subtask);
        taskManager.save(anotherSubtask);

        final Collection<Subtask> savedSubtasks = taskManager.getAllSubtasks();
        assertNotNull(savedSubtasks);
        assertEquals(2, savedSubtasks.size());
        assertTrue(savedSubtasks.containsAll(List.of(subtask, anotherSubtask)));
    }

    @Test
    void getHistory() {
        final Task task = new Task(taskManager.nextId(), "Task", "Task Description", Status.NEW, 500, startTime);
        final Epic epic = new Epic(taskManager.nextId(), "Epic", "Epic Description");
        final Subtask subtask = new Subtask(taskManager.nextId(), epic.getId(), "Subtask", "Test Subtask Description", Status.NEW, 500, startTime);
        epic.addSubtask(subtask);

        taskManager.save(task);
        taskManager.save(epic);
        taskManager.save(subtask);

        taskManager.getTaskById(task.getId());
        taskManager.getEpicById(epic.getId());
        taskManager.getSubtaskById(subtask.getId());

        final List<Task> history = taskManager.getHistory();
        assertNotNull(history);
        assertEquals(3, history.size());
        assertTrue(history.containsAll(List.of(task, epic, subtask)));
    }

    @Test
    void markAsOpened() {
        final Task task = new Task(taskManager.nextId(), "Task", "Task Description", Status.NEW, 500, startTime);

        taskManager.save(task);
        taskManager.markAsOpened(task);

        final List<Task> history = taskManager.getHistory();
        assertNotNull(history);
        assertEquals(1, history.size());
        assertTrue(history.contains(task));
    }

    @Test
    void checkValidation() {
        Task existing = new Task(taskManager.nextId(), "Task", "Task high priority", Status.NEW, 120, LocalDateTime.of(2023, 12, 1, 12, 0));
        Task intersect = new Task(taskManager.nextId(), "Task", "Task high priority", Status.NEW, 120, LocalDateTime.of(2023, 12, 1, 13, 0));

        taskManager.save(existing);
        assertThrows(TasksTimeIntersectionException.class, () -> taskManager.save(intersect));
    }
}