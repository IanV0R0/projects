package manager;

import history.HistoryManager;
import history.InMemoryHistoryManager;

import java.io.IOException;

public class Managers {

    public static TaskManager getFileBackedManager() {
        return new FileBackedTasksManager("file.csv");
    }

    public static TaskManager getInMemoryManager() {
        return new InMemoryTaskManager();
    }

    public static TaskManager getHttpManager() {
        return new HttpTaskManager("http://localhost:8078");
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
