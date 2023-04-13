import Tasks.Status;
import Tasks.Task;
import com.google.gson.Gson;
import manager.Managers;
import manager.TaskManager;
import server.HttpTaskServer;
import server.KVServer;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

public class Main {

    private static final Gson gson = new Gson();
    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) throws IOException, InterruptedException {
        KVServer kvServer = new KVServer();
        kvServer.start();
        HttpTaskServer server = new HttpTaskServer();
        server.start();
        TaskManager tm = Managers.getHttpManager();

        Task taskLowPriority = new Task(tm.nextId(), "Task", "Task low priority", Status.NEW, 120, LocalDateTime.of(2025, 12, 1, 12, 0));
        Task taskMiddlePriority = new Task(tm.nextId(), "Task", "Task middle priority", Status.NEW, 500, LocalDateTime.of(2024, 12, 1, 12, 0));
        Task taskHighPriority = new Task(tm.nextId(), "Task", "Task high priority", Status.NEW, 120, LocalDateTime.of(2023, 12, 1, 12, 0));

        postRequest("http://localhost:8080/tasks/task", taskLowPriority);
        postRequest("http://localhost:8080/tasks/task", taskMiddlePriority);
        postRequest("http://localhost:8080/tasks/task", taskHighPriority);

        System.out.println("------------------------------------------------------------------------------------");

        System.out.println(getRequest("http://localhost:8080/tasks/task?id=" + taskLowPriority.getId()));
        System.out.println(getRequest("http://localhost:8080/tasks/task?id=" + taskMiddlePriority.getId()));
        System.out.println(getRequest("http://localhost:8080/tasks/task?id=" + taskHighPriority.getId()));

        System.out.println("------------------------------------------------------------------------------------");

        System.out.println(getRequest("http://localhost:8080/tasks"));

        System.out.println("------------------------------------------------------------------------------------");

        deleteRequest("http://localhost:8080/tasks/task?id=" + taskLowPriority.getId());
        System.out.println(getRequest("http://localhost:8080/tasks/task?id=" + taskLowPriority.getId()));

        System.out.println("------------------------------------------------------------------------------------");

        tm.save(taskLowPriority);
        tm.save(taskMiddlePriority);
        tm.save(taskHighPriority);

        System.out.println(tm.getTaskById(taskLowPriority.getId()));
        System.out.println(tm.getTaskById(taskMiddlePriority.getId()));
        System.out.println(tm.getTaskById(taskHighPriority.getId()));

        kvServer.stop();
        server.stop();
    }

    private static String getRequest(String path) throws IOException, InterruptedException {
        URI url = URI.create(path);
        HttpRequest request = HttpRequest.newBuilder().uri(url).GET().build();
        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    private static <T extends Task> void postRequest(String path, T body) throws IOException, InterruptedException {
        URI url = URI.create(path);
        HttpRequest request = HttpRequest.newBuilder().uri(url).POST(HttpRequest.BodyPublishers.ofString(gson.toJson(body))).build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private static void deleteRequest(String path) throws IOException, InterruptedException {
        URI url = URI.create(path);
        HttpRequest request = HttpRequest.newBuilder().uri(url).DELETE().build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}