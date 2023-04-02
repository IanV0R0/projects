package history;

import Tasks.Status;
import Tasks.Task;
import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {
    private static LocalDateTime startTime;

    private HistoryManager historyManager;
    private TaskManager taskManager;

    @BeforeAll
    static void beforeAll() {
        startTime = LocalDateTime.of(2023, 5, 1, 12, 0);
    }

    @BeforeEach
    void beforeEach() {
        historyManager = Managers.getDefaultHistory();
        taskManager = Managers.getInMemoryManager();
    }

    @Test
    void getEmptyHistory() {
        final List<Task> historyList = historyManager.getHistory();
        assertNotNull(historyList);
        assertEquals(0, historyList.size());
        assertTrue(historyList.isEmpty());
    }

    @Test
    void addAndGetHistory() {
        final Task task = new Task(taskManager.nextId(), "Task", "Test task", Status.NEW, 60, startTime);

        historyManager.add(task);

        final List<Task> historyList = historyManager.getHistory();
        assertNotNull(historyList);
        assertEquals(1, historyList.size());
        assertEquals(task, historyList.get(0));
    }

    @Test
    void addDuplicates() {
        final Task task = new Task(taskManager.nextId(), "Task", "Test task", Status.NEW, 60, startTime);

        historyManager.add(task);
        historyManager.add(task);
        historyManager.add(task);

        final List<Task> historyList = historyManager.getHistory();
        assertNotNull(historyList);
        assertEquals(1, historyList.size());
        assertEquals(task, historyList.get(0));
    }

    @Test
    void remove() {
        final Task task = new Task(taskManager.nextId(), "Task", "Test task", Status.NEW, 60, startTime);

        historyManager.add(task);
        historyManager.remove(task.id);

        final List<Task> historyList = historyManager.getHistory();
        assertNotNull(historyList);
        assertEquals(0, historyList.size());
        assertFalse(historyList.contains(task));
    }

    @Test
    void removeFirst() {
        final Task firstTask = new Task(taskManager.nextId(), "First Task", "Test task", Status.NEW, 60, startTime);
        final Task middleTask = new Task(taskManager.nextId(), "Middle Task", "Test task", Status.IN_PROGRESS, 60, startTime);
        final Task endTask = new Task(taskManager.nextId(), "End Task", "Test task", Status.NEW, 60, startTime);

        historyManager.add(firstTask);
        historyManager.add(middleTask);
        historyManager.add(endTask);
        historyManager.remove(firstTask.id);

        final List<Task> historyList = historyManager.getHistory();
        assertNotNull(historyList);
        assertEquals(2, historyList.size());
        assertTrue(historyList.containsAll(List.of(middleTask, endTask)));
    }

    @Test
    void removeMiddle() {
        final Task firstTask = new Task(taskManager.nextId(), "First Task", "Test task", Status.NEW, 60, startTime);
        final Task middleTask = new Task(taskManager.nextId(), "Middle Task", "Test task", Status.IN_PROGRESS, 60, startTime);
        final Task endTask = new Task(taskManager.nextId(), "End Task", "Test task", Status.NEW, 60, startTime);

        historyManager.add(firstTask);
        historyManager.add(middleTask);
        historyManager.add(endTask);
        historyManager.remove(middleTask.id);

        final List<Task> historyList = historyManager.getHistory();
        assertNotNull(historyList);
        assertEquals(2, historyList.size());
        assertTrue(historyList.containsAll(List.of(firstTask, endTask)));
    }

    @Test
    void removeEnd() {
        final Task firstTask = new Task(taskManager.nextId(), "First Task", "Test task", Status.NEW, 60, startTime);
        final Task middleTask = new Task(taskManager.nextId(), "Middle Task", "Test task", Status.IN_PROGRESS, 60, startTime);
        final Task endTask = new Task(taskManager.nextId(), "End Task", "Test task", Status.NEW, 60, startTime);

        historyManager.add(firstTask);
        historyManager.add(middleTask);
        historyManager.add(endTask);
        historyManager.remove(endTask.id);

        final List<Task> historyList = historyManager.getHistory();
        assertNotNull(historyList);
        assertEquals(2, historyList.size());
        assertTrue(historyList.containsAll(List.of(middleTask, firstTask)));
    }
}