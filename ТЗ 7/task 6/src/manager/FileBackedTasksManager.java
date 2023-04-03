package manager;

import Tasks.Epic;
import Tasks.Status;
import Tasks.Subtask;
import Tasks.Task;
import manager.exception.ManagerLoadFromFileException;
import manager.exception.ManagerSaveException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FileBackedTasksManager extends InMemoryTaskManager {

    private static final String LINE_SEPARATOR = "\r\n";

    private final String filePath;

    public FileBackedTasksManager(String filePath) {
        this.filePath = filePath;
    }

    public static FileBackedTasksManager loadFromFile(String filePath) {
        try {
            FileBackedTasksManager manager = new FileBackedTasksManager(filePath);
            String content = Files.readString(Path.of(filePath));
            if (content.isEmpty() || content.isBlank()) return null;
            String[] lines = content.split(LINE_SEPARATOR);
            Map<Integer, Task> taskIdToTask = new HashMap<>();
            for (int i = 0; i < lines.length - 2; i++) {
                String[] fields = lines[i].split(",");
                switch (fields[1]) {
                    case "EPIC": {
                        Epic epic = new Epic(Integer.parseInt(fields[0]), fields[2], fields[3]);
                        manager.save(epic);
                        taskIdToTask.put(epic.getId(), epic);
                        break;
                    }
                    case "TASK": {
                        Task task = new Task(Integer.parseInt(fields[0]), fields[2], fields[3], Status.valueOf(fields[4]), Integer.parseInt(fields[5]), LocalDateTime.parse(fields[6]));
                        manager.save(task);
                        taskIdToTask.put(task.getId(), task);
                        break;
                    }
                    case "SUBTASK": {
                        Subtask subtask = new Subtask(Integer.parseInt(fields[0]), Integer.parseInt(fields[5]), fields[2], fields[3], Status.valueOf(fields[4]), Integer.parseInt(fields[5]), LocalDateTime.parse(fields[6]));
                        manager.save(subtask);
                        taskIdToTask.put(subtask.getId(), subtask);
                        break;
                    }
                }
            }
            for (String idInHistory : lines[lines.length-1].split(",")) {
                Integer id = Integer.parseInt(idInHistory);
                manager.markAsOpened(taskIdToTask.get(id));
            }

            return manager;
        } catch (IOException e) {
            throw new ManagerLoadFromFileException(e);
        }
    }

    private void save() {
        try {
            PrintWriter writer = new PrintWriter(filePath);
            for (Epic epic : getAllEpics()) {
                writer.println(epic.toCsvString());
            }
            for (Task task : getAllTasks()) {
                writer.println(task.toCsvString());
            }
            for (Subtask subtask : getAllSubtasks()) {
                writer.println(subtask.toCsvString());
            }
            writer.println();
            String history = getHistory().stream()
                    .map(task -> "" + task.getId())
                    .collect(Collectors.joining(","));
            writer.println(history);
            writer.close();
        } catch (FileNotFoundException e) {
            throw new ManagerSaveException(e);
        }
    }

    @Override
    public void save(Task task) {
        super.save(task);
        save();
    }

    @Override
    public void save(Epic epic) {
        super.save(epic);
        save();
    }

    @Override
    public void save(Subtask subtask) {
        super.save(subtask);
        save();
    }

    @Override
    public void deleteTaskById(int id) {
        super.deleteTaskById(id);
        save();
    }

    @Override
    public void deleteEpicById(int id) {
        super.deleteEpicById(id);
        save();
    }

    @Override
    public void deleteSubtaskById(int id) {
        super.deleteSubtaskById(id);
        save();
    }

    @Override
    public Epic getEpicById(int id) {
        Epic epic = super.getEpicById(id);
        save();
        return epic;
    }

    @Override
    public Task getTaskById(int id) {
        Task task = super.getTaskById(id);
        save();
        return task;
    }

    @Override
    public Subtask getSubtaskById(int id) {
        Subtask subtask = super.getSubtaskById(id);
        save();
        return subtask;
    }
}
