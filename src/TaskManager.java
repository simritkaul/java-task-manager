import java.time.LocalDate;
import java.util.*;

public class TaskManager {
    private List<Task> taskList = new ArrayList<>();
    private Map<Integer, List<Task>> taskByPriority = new HashMap<>();
    private PriorityQueue<Task> taskQueue = new PriorityQueue<>();

    public void addTask (String title, int priority, LocalDate deadline) {
        Task task = new Task(title, priority, deadline);
        taskList.add(task);
        taskQueue.offer(task);
        taskByPriority.computeIfAbsent(priority, k -> new ArrayList<>()).add(task);
    }

    public void markTaskCompleted(String title) {
        for (Task task : taskList) {
            if (task.toString().contains(title)) {
                task.markCompleted();
                return;
            }
        }
    }

    public void listAllTasks() {
        System.out.println("\nAll tasks");
        System.out.println("\n------------");
        for (Task task : taskList) {
            System.out.println(task.toString());
        }
    }

    public void listByPriority() {
        System.out.println("\nAll tasks by priority");
        System.out.println("\n----------------------");
        for (var entry : taskByPriority.entrySet()) {
            int priority = entry.getKey();
            List<Task> tasks = entry.getValue();

            System.out.println("Priority " + priority + ": " + tasks);
        }
    }

    public void listByDeadline() {
        System.out.println("\nAll tasks by deadline");
        System.out.println("\n----------------------");
        PriorityQueue<Task> sortedQueue = new PriorityQueue<>(taskQueue);
        while (!sortedQueue.isEmpty()) {
            System.out.println(sortedQueue.poll());
        }
    }

    public void removeCompletedTasks() {
        taskList.removeIf(Task::isCompleted);
        System.out.println("Completed Tasks Removed!");
    }
}
