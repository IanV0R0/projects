package server;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import manager.Managers;
import manager.TaskManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

public class HttpTaskServer {

    private final TaskManager manager;
    private final Gson gson;
    private final HttpServer server;

    public HttpTaskServer()  {
        manager = Managers.getFileBackedManager();
        gson = new Gson();
        try {
            server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/tasks/task", this::processTasks);
            server.createContext("/tasks/subtask", this::processSubtasks);
            server.createContext("/tasks/epic", this::processEpics);
            server.createContext("/tasks/history", this::getHistory);
            server.createContext("/tasks", this::getPrioritizedTasks);
        } catch (IOException e) {
            throw new RuntimeException("Unable to build HttpTaskServer", e);
        }
    }

    private void getPrioritizedTasks(HttpExchange httpExchange) throws IOException {
        try {
            List<Task> tasks = manager.getPrioritizedTasks();
            sendText(httpExchange, gson.toJson(tasks));
            httpExchange.sendResponseHeaders(200, 0);
        } finally {
            httpExchange.close();
        }
    }

    private void getHistory(HttpExchange httpExchange) throws IOException {
        try {
            List<Task> tasks = manager.getHistory();
            sendText(httpExchange, gson.toJson(tasks));
            httpExchange.sendResponseHeaders(200, 0);
        } finally {
            httpExchange.close();
        }
    }

    private void processEpics(HttpExchange httpExchange) throws IOException {
        try {
            if ("GET".equals(httpExchange.getRequestMethod())) {
                String id = queryToMap(httpExchange.getRequestURI().getQuery()).get("id");
                if (id != null) {
                    Epic epic = manager.getEpicById(Integer.parseInt(id));
                    sendText(httpExchange, gson.toJson(epic));
                } else {
                    Collection<Epic> epics = manager.getAllEpics();
                    sendText(httpExchange, gson.toJson(epics));
                }
            } else if ("POST".equals(httpExchange.getRequestMethod())) {
                Epic epic = gson.fromJson(readText(httpExchange), Epic.class);
                manager.save(epic);
                httpExchange.sendResponseHeaders(200, 0);
            } else if ("DELETE".equals(httpExchange.getRequestMethod())) {
                String id = queryToMap(httpExchange.getRequestURI().getQuery()).get("id");
                if (id != null) {
                    manager.deleteEpicById(Integer.parseInt(id));
                }
                httpExchange.sendResponseHeaders(200, 0);
            }
        } finally {
            httpExchange.close();
        }
    }

    private void processSubtasks(HttpExchange httpExchange) throws IOException {
        try {
            if ("GET".equals(httpExchange.getRequestMethod())) {
                String id = queryToMap(httpExchange.getRequestURI().getQuery()).get("id");
                if (id != null) {
                    Subtask subtask = manager.getSubtaskById(Integer.parseInt(id));
                    sendText(httpExchange, gson.toJson(subtask));
                } else {
                    Collection<Subtask> subtasks = manager.getAllSubtasks();
                    sendText(httpExchange, gson.toJson(subtasks));
                }
            } else if ("POST".equals(httpExchange.getRequestMethod())) {
                Subtask subtask = gson.fromJson(readText(httpExchange), Subtask.class);
                manager.save(subtask);
                httpExchange.sendResponseHeaders(200, 0);
            } else if ("DELETE".equals(httpExchange.getRequestMethod())) {
                String id = queryToMap(httpExchange.getRequestURI().getQuery()).get("id");
                if (id != null) {
                    manager.deleteSubtaskById(Integer.parseInt(id));
                }
                httpExchange.sendResponseHeaders(200, 0);
            }
        } finally {
            httpExchange.close();
        }
    }

    private void processTasks(HttpExchange httpExchange) throws IOException {
        try {
            if ("GET".equals(httpExchange.getRequestMethod())) {
                String id = queryToMap(httpExchange.getRequestURI().getQuery()).get("id");
                if (id != null) {
                    Task task = manager.getTaskById(Integer.parseInt(id));
                    sendText(httpExchange, gson.toJson(task));
                } else {
                    Collection<Task> tasks = manager.getAllTasks();
                    sendText(httpExchange, gson.toJson(tasks));
                }
            } else if ("POST".equals(httpExchange.getRequestMethod())) {
                Task task = gson.fromJson(readText(httpExchange), Task.class);
                manager.save(task);
                httpExchange.sendResponseHeaders(200, 0);
            } else if ("DELETE".equals(httpExchange.getRequestMethod())) {
                String id = queryToMap(httpExchange.getRequestURI().getQuery()).get("id");
                if (id != null) {
                    manager.deleteTaskById(Integer.parseInt(id));
                }
                httpExchange.sendResponseHeaders(200, 0);
            }
        } finally {
            httpExchange.close();
        }
    }

    protected String readText(HttpExchange h) throws IOException {
        return new String(h.getRequestBody().readAllBytes(), UTF_8);
    }

    protected void sendText(HttpExchange h, String text) throws IOException {
        byte[] resp = text.getBytes(UTF_8);
        h.getResponseHeaders().add("Content-Type", "application/json");
        h.sendResponseHeaders(200, resp.length);
        h.getResponseBody().write(resp);
    }

    public Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();
        if(query == null) {
            return result;
        }
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            }else{
                result.put(entry[0], "");
            }
        }
        return result;
    }

    public void start() {
        server.start();
    }

    public void stop() {
        server.stop(0);
    }
}
