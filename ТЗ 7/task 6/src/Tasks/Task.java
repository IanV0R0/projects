package Tasks;

import java.time.LocalDateTime;

public class Task {

    public final int id;
    public final String title;
    public final String description;
    protected Status status;

    public int duration;
    public LocalDateTime startTime;

    public Task(int id, String title, String description, Status status, int duration, LocalDateTime startTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.duration = duration;
        this.startTime = startTime;
    }

    public String toCsvString() {
        return id + ",TASK," + title + "," + description + "," + status + "," + duration + "," + startTime;
    }

    @Override
    public String toString() {
        return "Tasks.Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", duration='" + duration + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + getEndTime() + '\'' +
                '}';
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getEndTime() {
        return startTime.plusMinutes(duration);
    }
}
