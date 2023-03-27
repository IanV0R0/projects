package Tasks;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Epic extends Task {

    private Map<Integer, Subtask> subtaskMap = new HashMap<>();

    public Epic(int id, String title, String description) {
        super(id, title, description, Status.NEW);
    }

    public void addSubtask(Subtask subtask) {
        this.subtaskMap.put(subtask.id, subtask);
        this.status = calculateStatus();
    }

    public void removeSubtask(int subtaskId) {
        this.subtaskMap.remove(subtaskId);
        this.status = calculateStatus();
    }

    public Collection<Subtask> getSubtasks() {
        return subtaskMap.values();
    }

    private Status calculateStatus() {
        Collection<Subtask> subtasks = subtaskMap.values();
        if (subtasks.isEmpty()) {
            return Status.NEW;
        }
        boolean isNewStatus = false;
        for (Subtask subtask : subtasks) {
            switch (subtask.status) {
                case IN_PROGRESS: {
                    return Status.IN_PROGRESS;
                }
                case NEW: {
                    isNewStatus = true;
                    break;
                }
            }
        }
        if (isNewStatus) return Status.NEW;
        return Status.DONE;
    }

    @Override
    public String toCsvString() {
        return id + ",EPIC," + title + "," + description;
    }

    @Override
    public String toString() {
        return "Tasks.Epic{" +
                "subtaskMap=" + subtaskMap +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
