package history;


import Tasks.Task;
import history.InMemoryHistoryManager.CustomLinkedList.TaskNode;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

    private final CustomLinkedList historyList = new CustomLinkedList();
    private final Map<Integer, TaskNode> taskIdsToNode = new HashMap<>();

    @Override
    public void add(Task task) {
        if (task == null) return;
        TaskNode presentElem = taskIdsToNode.get(task.id);
        if (presentElem != null) {
            historyList.removeNode(presentElem);
        }

        TaskNode createdNode = historyList.linkLast(task);
        taskIdsToNode.put(task.id, createdNode);
    }

    @Override
    public List<Task> getHistory() {
        return historyList.getTasks();
    }

    @Override
    public void remove(int id) {
        TaskNode node = taskIdsToNode.remove(id);
        if (node != null) historyList.removeNode(node);
    }

    static class CustomLinkedList {
        TaskNode first;
        TaskNode last;
        private int size = 0;

        TaskNode linkLast(Task task) {
            TaskNode last = this.last;
            TaskNode newNode = new TaskNode(task, last, null);
            this.last = newNode;

            if (last == null) {
                this.first = newNode;
            } else {
                last.next = newNode;
            }

            size++;
            return newNode;
        }

        void removeNode(TaskNode node) {
            TaskNode prev = node.prev;
            TaskNode next = node.next;

            if (prev == null) {
                first = next;
            } else {
                prev.next = next;
                node.prev = null;
            }

            if (next == null) {
                last = prev;
            } else {
                next.prev = prev;
                node.next = null;
            }

            node.item = null;
            size--;
        }

        List<Task> getTasks() {
            List<Task> result = new ArrayList<>();
            for (TaskNode elem = first; elem != null; elem = elem.next) {
                result.add(elem.item);
            }
            return result;
        }

        public int size() {
            return size;
        }

        static class TaskNode {
            private Task item;
            private TaskNode prev;
            private TaskNode next;

            public TaskNode(Task item, TaskNode prev, TaskNode next) {
                this.item = item;
                this.prev = prev;
                this.next = next;
            }
        }
    }
}
