package manager;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;
import client.KVTaskClient;
import com.google.gson.Gson;

public class HttpTaskManager extends FileBackedTasksManager {

    private final Gson gson = new Gson();
    private final KVTaskClient client;

    public HttpTaskManager(String path) {
        super(path);
        this.client = new KVTaskClient(path);
    }

    @Override
    public void save(Task task) {
        client.put("/save/" + task.getId(), gson.toJson(task));
    }

    @Override
    public void save(Epic epic) {
        client.put("/save/" + epic.getId(), gson.toJson(epic));
    }

    @Override
    public void save(Subtask subtask) {
        client.put("/save/" + subtask.getId(), gson.toJson(subtask));
    }

    @Override
    public void deleteTaskById(int id) {
        client.put("/delete/" + id, "");
    }

    @Override
    public void deleteEpicById(int id) {
        client.put("/delete/" + id, "");
    }

    @Override
    public void deleteSubtaskById(int id) {
        client.put("/delete/" + id, "");
    }

    @Override
    public Epic getEpicById(int id) {
        String task = client.load("/load/" + id);
        return gson.fromJson(task, Epic.class);
    }

    @Override
    public Task getTaskById(int id) {
        String task = client.load("/load/" + id);
        return gson.fromJson(task, Task.class);
    }

    @Override
    public Subtask getSubtaskById(int id) {
        String task = client.load("/load/" + id);
        return gson.fromJson(task, Subtask.class);
    }
}
