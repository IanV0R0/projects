package Tasks;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Epic extends Task {

    private final Map<Integer, Subtask> subtaskMap = new HashMap<>();
    private LocalDateTime endTime;

    public Epic(int id, String title, String description) {
        super(id, title, description, Status.NEW, 0, LocalDateTime.MAX);
    }

    public void addSubtask(Subtask subtask) {
        this.subtaskMap.put(subtask.getId(), subtask);
        this.status = calculateStatus();
        this.duration = calculateDuration();
        this.startTime = calculateStartTime();
        this.endTime = calculateEndTime();
    }

    public void removeSubtask(int subtaskId) {
        this.subtaskMap.remove(subtaskId);
        this.status = calculateStatus();
        this.duration = calculateDuration();
        this.startTime = calculateStartTime();
        this.endTime = calculateEndTime();
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

    private int calculateDuration() {
        Collection<Subtask> subtasks = subtaskMap.values();
        int totalDuration = 0;
        for (Subtask subtask : subtasks) {
            totalDuration += subtask.getDuration();
        }
        return totalDuration;
    }

    private LocalDateTime calculateStartTime() {
        Collection<Subtask> subtasks = subtaskMap.values();
        if (subtasks.isEmpty()) return LocalDateTime.MAX;
        LocalDateTime result = LocalDateTime.MAX;
        for (Subtask subtask : subtasks) {
            if (result.isAfter(subtask.getStartTime())) result = subtask.getStartTime();
        }
        return result;
    }

    private LocalDateTime calculateEndTime() {
        Collection<Subtask> subtasks = subtaskMap.values();
        if (subtasks.isEmpty()) return LocalDateTime.MIN;
        LocalDateTime result = LocalDateTime.MIN;
        for (Subtask subtask : subtasks) {
            if (result.isBefore(subtask.getEndTime())) result = subtask.getEndTime();
        }
        return result;
    }

    @Override
    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public String toCsvString() {
        return getId() + ",EPIC," + getTitle() + "," + getDescription();
    }

    @Override
    public String toString() {
        return "Tasks.Epic{" +
                "subtaskMap=" + subtaskMap +
                ", id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                ", duration='" + getDuration() + '\'' +
                ", startTime='" + getStartTime() + '\'' +
                ", endTime='" + getEndTime() + '\'' +
                '}';
    }
}
