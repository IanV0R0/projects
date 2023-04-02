package Tasks;

import java.time.LocalDateTime;

public class Subtask extends Task {

    public final int epicId;

    public Subtask(int id, int epicId, String title, String description, Status status, int duration, LocalDateTime startDate) {
        super(id, title, description, status, duration, startDate);
        this.epicId = epicId;
    }

    @Override
    public String toCsvString() {
        return id + ",SUBTASK," + title + "," + description + "," + status + "," + epicId + "," + duration + "," + startTime;
    }

    @Override
    public String toString() {
        return "Tasks.Subtask{" +
                "epicId=" + epicId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", duration='" + duration + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + getEndTime() + '\'' +
                '}';
    }
}
