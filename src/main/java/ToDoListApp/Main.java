package ToDoListApp;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("* -- Todo List Application -- *");
            System.out.println("1. Add Task");
            System.out.println("2. List Tasks");
            System.out.println("3. Mark Task as Done");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    Task task = new Task(description);
                    tasks.add(task);
                    System.out.println("Task added.");
                    break;

                case 2:
                    System.out.println("Tasks:");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task t = tasks.get(i);
                        System.out.println((i + 1) + ". " + t.getDescription() + " - " + (t.isDone() ? "Done" : "Not Done"));
                    }
                    break;

                case 3:
                    System.out.print("Enter the task number to mark as done: ");
                    int taskNumber = scanner.nextInt();
                    if (taskNumber >= 1 && taskNumber <= tasks.size()) {
                        Task selectedTask = tasks.get(taskNumber - 1);
                        selectedTask.setDone(true);
                        System.out.println("Task marked as done.");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                    break;

                case 4:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}

