package Tasks;

public class Task {

    public final int id;
    public final String title;
    public final String description;
    protected Status status;

    public Task(int id, String title, String description, Status status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public String toCsvString() {
        return id + ",TASK," + title + "," + description + "," + status;
    }

    @Override
    public String toString() {
        return "Tasks.Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
