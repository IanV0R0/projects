package manager;

import history.HistoryManager;
import history.InMemoryHistoryManager;

public class Managers {

    public static TaskManager getFileBackedManager() {
        return new FileBackedTasksManager("file.csv");
    }

    public static TaskManager getInMemoryManager() {
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
