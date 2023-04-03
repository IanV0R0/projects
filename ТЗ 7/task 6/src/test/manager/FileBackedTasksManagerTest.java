package test.manager;

import Tasks.Epic;
import manager.FileBackedTasksManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FileBackedTasksManagerTest extends TaskManagerTest<FileBackedTasksManager> {
    @BeforeEach
    void beforeEach() {
        taskManager = new FileBackedTasksManager("files.csv");
    }

    @Test
    void saveAndLoadEmptyEpic() {
        final Epic epic = new Epic(taskManager.nextId(), "Epic", "Test epic description.");

        taskManager.save(epic);

        final Epic savedEpic = taskManager.getEpicById(epic.getId());
        assertNotNull(savedEpic);
    }
}