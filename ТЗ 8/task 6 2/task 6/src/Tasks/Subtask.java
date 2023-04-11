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
        return getId() + ",SUBTASK," + getTitle() + "," + getDescription() + "," + status + "," + epicId + "," + getDuration() + "," + getStartTime();
    }

    @Override
    public String toString() {
        return "Tasks.Subtask{" +
                "epicId=" + epicId +
                ", id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + status +
                ", duration='" + getDuration() + '\'' +
                ", startTime='" + getStartTime() + '\'' +
                ", endTime='" + getEndTime() + '\'' +
                '}';
    }
}
