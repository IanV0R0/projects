package Tasks;

public class Subtask extends Task {

    public int epicId;

    public Subtask(int id, int epicId, String title, String description, Status status) {
        super(id, title, description, status);
        this.epicId = epicId;
    }

    @Override
    public String toCsvString() {
        return id + ",SUBTASK," + title + "," + description + "," + status + "," + epicId;
    }

    @Override
    public String toString() {
        return "Tasks.Subtask{" +
                "epicId=" + epicId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
