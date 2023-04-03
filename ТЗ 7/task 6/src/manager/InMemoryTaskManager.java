package manager;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;
import history.HistoryManager;
import manager.exception.TasksTimeIntersectionException;

import java.util.*;

public class InMemoryTaskManager implements TaskManager {
    private int idCounter = 0;

    private final Map<Integer, Task> taskMap = new HashMap<>();
    private final Map<Integer, Epic> epicMap = new HashMap<>();
    private final Map<Integer, Subtask> subtaskMap = new HashMap<>();

    private final HistoryManager historyManager = Managers.getDefaultHistory();

    private final Set<Task> prioritizedTasks = new TreeSet<>(Comparator.comparing(o -> o.getStartTime()));

    @Override
    public int nextId() {
        return ++idCounter;
    }

    @Override
    public void save(Task task) {
        validateTasksTimeIntersection(task);
        taskMap.put(task.getId(), task);
        prioritizedTasks.add(task);
    }

    @Override
    public void save(Epic epic) {
        validateTasksTimeIntersection(epic);
        epicMap.put(epic.getId(), epic);
        prioritizedTasks.add(epic);
    }

    @Override
    public void save(Subtask subtask) {
        validateTasksTimeIntersection(subtask);
        Subtask subtaskBeforeUpdate = subtaskMap.get(subtask.getId());
        if (subtaskBeforeUpdate != null) {
            Epic oldEpic = epicMap.get(subtaskBeforeUpdate.epicId);
            if (oldEpic != null) {
                oldEpic.removeSubtask(subtask.getId());
            }
        }
        Epic epic = epicMap.get(subtask.epicId);
        if (epic != null) {
            epic.addSubtask(subtask);
        }
        subtaskMap.put(subtask.getId(), subtask);
        prioritizedTasks.add(subtask);
    }

    @Override
    public void deleteTaskById(int id) {
        prioritizedTasks.remove(taskMap.get(id));
        taskMap.remove(id);
        historyManager.remove(id);
    }

    @Override
    public void deleteEpicById(int id) {
        Epic epic = epicMap.get(id);
        if (epic != null) {
            for (Subtask subtask : epic.getSubtasks()) {
                subtaskMap.remove(subtask.getId());
                historyManager.remove(subtask.getId());
            }
        }
        prioritizedTasks.remove(epicMap.get(id));
        epicMap.remove(id);
        historyManager.remove(id);
    }

    @Override
    public void deleteSubtaskById(int id) {
        Subtask subtask = subtaskMap.get(id);
        if (subtask != null) {
            Epic epic = epicMap.get(subtask.epicId);
            if (epic != null) {
                epic.removeSubtask(subtask.getId());
            }
        }
        prioritizedTasks.remove(subtaskMap.get(id));
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

    @Override
    public List<Task> getPrioritizedTasks() {
        return new ArrayList<>(prioritizedTasks);
    }

    private void validateTasksTimeIntersection(Task addedTask) {
        for (Task existing : prioritizedTasks) {
            if(addedTask.getStartTime().isAfter(existing.getStartTime()) &&
                    addedTask.getEndTime().isBefore(existing.getEndTime()) &&
                    addedTask.getEndTime().isAfter(existing.getStartTime()) &&
                    addedTask.getEndTime().isBefore(existing.getEndTime()))
                throw new TasksTimeIntersectionException();
        }
    }
}
