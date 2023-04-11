package manager;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpTaskManager extends FileBackedTasksManager {

    private final String apiToken;

    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public HttpTaskManager(String path) {
        super(path);
        apiToken = getRequest("/register");
    }

    private void postRequest(String path, String body) {
        URI url = URI.create(this.path + path + "?API_TOKEN=" + apiToken);
        HttpRequest request = HttpRequest.newBuilder().uri(url).POST(HttpRequest.BodyPublishers.ofString(body)).build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String getRequest(String path) {
        URI url = URI.create(this.path + path + "?API_TOKEN=" + apiToken);
        HttpRequest request = HttpRequest.newBuilder().uri(url).GET().build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Task task) {
        postRequest("/save/" + task.getId(), gson.toJson(task));
    }

    @Override
    public void save(Epic epic) {
        postRequest("/save/" + epic.getId(), gson.toJson(epic));
    }

    @Override
    public void save(Subtask subtask) {
        postRequest("/save/" + subtask.getId(), gson.toJson(subtask));
    }

    @Override
    public void deleteTaskById(int id) {
        postRequest("/delete/" + id, "");
    }

    @Override
    public void deleteEpicById(int id) {
        postRequest("/delete/" + id, "");
    }

    @Override
    public void deleteSubtaskById(int id) {
        postRequest("/delete/" + id, "");
    }

    @Override
    public Epic getEpicById(int id) {
        String task = getRequest("/load/" + id);
        return gson.fromJson(task, Epic.class);
    }

    @Override
    public Task getTaskById(int id) {
        String task = getRequest("/load/" + id);
        return gson.fromJson(task, Task.class);
    }

    @Override
    public Subtask getSubtaskById(int id) {
        String task = getRequest("/load/" + id);
        return gson.fromJson(task, Subtask.class);
    }
}
