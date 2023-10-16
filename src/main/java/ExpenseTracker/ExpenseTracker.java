package ExpenseTracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ExpenseTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<Double>> expenses = new HashMap<>();

        while (true) {
            System.out.println("Expense Tracker Menu:");
            System.out.println("1. Record an expense");
            System.out.println("2. View expenses by category");
            System.out.println("3. Calculate total expenses by category");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    recordExpense(scanner, expenses);
                    break;
                case 2:
                    viewExpensesByCategory(expenses);
                    break;
                case 3:
                    calculateTotalExpensesByCategory(expenses);
                    break;
                case 4:
                    System.out.println("Exiting Expense Tracker. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static void recordExpense(Scanner scanner, Map<String, List<Double>> expenses) {
        System.out.print("Enter the expense amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter the category: ");
        String category = scanner.nextLine();

        expenses.computeIfAbsent(category, k -> new ArrayList<>()).add(amount);
        System.out.println("Expense recorded.");
    }

    private static void viewExpensesByCategory(Map<String, List<Double>> expenses) {
        System.out.println("Expenses by Category:");
        for (Map.Entry<String, List<Double>> entry : expenses.entrySet()) {
            String category = entry.getKey();
            List<Double> categoryExpenses = entry.getValue();
            System.out.println(category + ": " + categoryExpenses);
        }
    }

    private static void calculateTotalExpensesByCategory(Map<String, List<Double>> expenses) {
        System.out.println("Total Expenses by Category:");
        for (Map.Entry<String, List<Double>> entry : expenses.entrySet()) {
            String category = entry.getKey();
            List<Double> categoryExpenses = entry.getValue();
            double total = categoryExpenses.stream().mapToDouble(Double::doubleValue).sum();
            System.out.println(category + ": " + total);
        }
    } 
}
