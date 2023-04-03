package test.Tasks;

import Tasks.Epic;
import Tasks.Status;
import Tasks.Subtask;
import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
    // only for id generation
    private static TaskManager taskManager;
    private static LocalDateTime startTime;

    @BeforeAll
    static void beforeAll() {
        taskManager = Managers.getInMemoryManager();
        startTime = LocalDateTime.of(2023, 5, 1, 12, 0);
    }

    @Test
    void addSubtask() {
        Epic epic = new Epic(taskManager.nextId(), "Epic", "Test description.");
        Subtask subtask = new Subtask(taskManager.nextId(), epic.getId(), "Subtask", "Test subtask description", Status.NEW, 150, startTime);

        epic.addSubtask(subtask);

        assertTrue(epic.getSubtasks().contains(subtask));
        // if epic contains only new subtask, epic status == NEW
        assertEquals(epic.getStatus(), Status.NEW);
    }

    @Test
    void removeSubtask() {
        Epic epic = new Epic(taskManager.nextId(), "Epic", "Test epic description.");
        Subtask subtask = new Subtask(taskManager.nextId(), epic.getId(), "Subtask", "Test subtask description", Status.NEW, 240 ,startTime);

        epic.addSubtask(subtask);
        epic.removeSubtask(subtask.getId());

        assertFalse(epic.getSubtasks().contains(subtask));
        // if there is no subtasks, epic status == NEW
        assertEquals(epic.getStatus(), Status.NEW);
    }

    @Test
    void calculateDoneEpicStatus() {
        Epic epic = new Epic(taskManager.nextId(), "Epic", "Test epic description.");
        Subtask subtask = new Subtask(taskManager.nextId(), epic.getId(), "Subtask", "Test subtask description", Status.DONE, 240, startTime);

        epic.addSubtask(subtask);

        assertEquals(epic.getStatus(), Status.DONE);
    }

    @Test
    void calculateNewAndDoneEpicStatus() {
        Epic epic = new Epic(taskManager.nextId(), "Epic", "Test epic description.");
        Subtask newSubtask = new Subtask(taskManager.nextId(), epic.getId(), "Subtask", "Test subtask description", Status.NEW, 240, startTime);
        Subtask doneSubtask = new Subtask(taskManager.nextId(), epic.getId(), "Subtask", "Test subtask description", Status.DONE, 240, startTime);

        epic.addSubtask(newSubtask);
        epic.addSubtask(doneSubtask);

        assertEquals(epic.getStatus(), Status.NEW);
    }

    @Test
    void calculateInProgressEpicStatus() {
        Epic epic = new Epic(taskManager.nextId(), "Epic", "Test epic description.");
        Subtask subtask = new Subtask(taskManager.nextId(), epic.getId(), "Subtask", "Test subtask description", Status.IN_PROGRESS, 700, startTime);

        epic.addSubtask(subtask);

        assertEquals(epic.getStatus(), Status.IN_PROGRESS);
    }

    @Test
    void calculateTimeParametersWorksProperly() {
        Epic epic = new Epic(taskManager.nextId(), "Epic", "Test epic description.");
        Subtask subtask = new Subtask(taskManager.nextId(), epic.getId(), "Subtask", "Test subtask description", Status.NEW, 240, startTime);
        Subtask anotherSubtask = new Subtask(taskManager.nextId(), epic.getId(), "Subtask", "Test subtask description", Status.NEW, 240, startTime.plusYears(2));
        int totalDuration = subtask.getDuration() + anotherSubtask.getDuration();
        LocalDateTime epicStartTime = subtask.getStartTime();
        LocalDateTime epicEndTime = startTime.plusYears(2).plusMinutes(240);


        epic.addSubtask(subtask);
        epic.addSubtask(anotherSubtask);

        assertEquals(epic.getStartTime(), epicStartTime);
        assertEquals(epic.getEndTime(), epicEndTime);
        assertEquals(epic.getStartTime(), totalDuration);
    }
}