package manager;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;
import history.HistoryManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {
    private int idCounter = 0;

    private final Map<Integer, Task> taskMap = new HashMap<>();
    private final Map<Integer, Epic> epicMap = new HashMap<>();
    private final Map<Integer, Subtask> subtaskMap = new HashMap<>();

    private final HistoryManager historyManager = Managers.getDefaultHistory();

    @Override
    public int nextId() {
        return ++idCounter;
    }

    @Override
    public void save(Task task) {
        taskMap.put(task.id, task);
    }

    @Override
    public void save(Epic epic) {
        epicMap.put(epic.id, epic);
    }

    @Override
    public void save(Subtask subtask) {
        Subtask subtaskBeforeUpdate = subtaskMap.get(subtask.id);
        if (subtaskBeforeUpdate != null) {
            Epic oldEpic = epicMap.get(subtaskBeforeUpdate.epicId);
            if (oldEpic != null) {
                oldEpic.removeSubtask(subtask.id);
            }
        }
        Epic epic = epicMap.get(subtask.epicId);
        if (epic != null) {
            epic.addSubtask(subtask);
        }
        subtaskMap.put(subtask.id, subtask);
    }

    @Override
    public void deleteTaskById(int id) {
        taskMap.remove(id);
        historyManager.remove(id);
    }

    @Override
    public void deleteEpicById(int id) {
        Epic epic = epicMap.get(id);
        if (epic != null) {
            for (Subtask subtask : epic.getSubtasks()) {
                subtaskMap.remove(subtask.id);
                historyManager.remove(subtask.id);
            }
        }
        epicMap.remove(id);
        historyManager.remove(id);
    }

    @Override
    public void deleteSubtaskById(int id) {
        Subtask subtask = subtaskMap.get(id);
        if (subtask != null) {
            Epic epic = epicMap.get(subtask.epicId);
            if (epic != null) {
                epic.removeSubtask(subtask.id);
            }
        }
        subtaskMap.remove(id);
        historyManager.remove(id);
    }

    @Override
    public Collection<Epic> getAllEpics() {
        return epicMap.values();
    }

    @Override
    public Collection<Task> getAllTasks() {
        return taskMap.values();
    }

    @Override
    public Collection<Subtask> getAllSubtasks() {
        return subtaskMap.values();
    }

    @Override
    public Epic getEpicById(int id) {
        Epic epic = epicMap.get(id);
        historyManager.add(epic);
        return epic;
    }

    @Override
    public Task getTaskById(int id) {
        Task task = taskMap.get(id);
        historyManager.add(task);
        return task;
    }

    @Override
    public Subtask getSubtaskById(int id) {
        Subtask subtask = subtaskMap.get(id);
        historyManager.add(subtask);
        return subtask;
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    @Override
    public void markAsOpened(Task task) {
        historyManager.add(task);
    }
}
