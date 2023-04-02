package manager;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;

import java.util.Collection;
import java.util.List;

public interface TaskManager {
    int nextId();

    void save(Task task);

    void save(Epic epic);

    void save(Subtask subtask);

    void deleteTaskById(int id);

    void deleteEpicById(int id);

    void deleteSubtaskById(int id);

    Collection<Epic> getAllEpics();

    Collection<Task> getAllTasks();

    Collection<Subtask> getAllSubtasks();

    Epic getEpicById(int id);

    Task getTaskById(int id);

    Subtask getSubtaskById(int id);

    List<Task> getHistory();

    void markAsOpened(Task task);

    List<Task> getPrioritizedTasks();
}
