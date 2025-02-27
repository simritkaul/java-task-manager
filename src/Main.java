import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
                    System.out.println("\nYou picked add task.");
                    break;
                case 2:
                    System.out.println("\nYou picked list all tasks.");
                    break;
                case 3:
                    System.out.println("\nYou picked list all tasks by priority.");
                    break;
                case 4:
                    System.out.println("\nYou picked list all tasks by deadline.");
                    break;
                case 5:
                    System.out.println("\nYou picked mark task as completed.");
                    break;
                case 6:
                    System.out.println("\nYou picked remove completed tasks.");
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