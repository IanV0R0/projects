package manager;

import history.HistoryManager;
import history.InMemoryHistoryManager;
import manager.InMemoryTaskManager;
import manager.TaskManager;

public class Managers {

    public static TaskManager getDefault() {
        return new FileBackedTasksManager("file.csv");
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
