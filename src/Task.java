import java.time.LocalDate;

class Task implements Comparable<Task> {
    private String title;
    private int priority;
    private LocalDate deadline;
    private boolean completed;

    Task (String title, int priority, LocalDate deadline) {
        this.title = title;
        this.priority = priority;
        this.deadline = deadline;
        this.completed = false;
    }

    public void markCompleted() {
        completed = true;
    }

    public boolean isCompleted() {
        return completed;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Task other) {
        return deadline.compareTo(other.deadline);
    }

    @Override
    public String toString() {
        return "[" + (completed ? "✔" : "✗") + "] " + title + " (Priority: " + priority + ", Deadline: " + deadline + ")";
    }
}
