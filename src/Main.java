import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Task Manager ===");
            System.out.println("1. Add Task");
            System.out.println("2. List All Tasks");
            System.out.println("3. List Tasks by Priority");
            System.out.println("4. List Tasks by Deadline");
            System.out.println("5. Mark Task as Completed");
            System.out.println("6. Remove Completed Tasks");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter priority (1-10) (lower = more important): ");
                    int priority = scanner.nextInt();
                    System.out.println("Enter the deadline (yyyy-mm-dd): ");
                    LocalDate deadline = LocalDate.parse(scanner.next());
                    taskManager.addTask(title, priority, deadline);
                    break;
                case 2:
                    taskManager.listAllTasks();
                    break;
                case 3:
                    taskManager.listByPriority();
                    break;
                case 4:
                    taskManager.listByDeadline();
                    break;
                case 5:
                    System.out.print("Enter title of the task you completed: ");
                    String deleteTitle = scanner.nextLine();
                    taskManager.markTaskCompleted(deleteTitle);
                    break;
                case 6:
                    taskManager.removeCompletedTasks();
                    break;
                case 0:
                    System.out.println("\nExiting...");
                    return;
                default:
                    System.out.println("\nInvalid");
                    return;
            }
        }
    }
}