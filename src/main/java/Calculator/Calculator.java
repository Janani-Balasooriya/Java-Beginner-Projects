package calculator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {

    // --- Core operations ---
    public static double add(double a, double b) { return a + b; }
    public static double subtract(double a, double b) { return a - b; }
    public static double multiply(double a, double b) { return a * b; }

    public static double divide(double a, double b) {
        if (b == 0.0) throw new IllegalArgumentException("Cannot divide by zero");
        return a / b;
    }

    public static double mod(double a, double b) {
        if (b == 0.0) throw new IllegalArgumentException("Cannot mod by zero");
        return a % b;
    }

    public static double power(double a, double b) {
        return Math.pow(a, b);
    }

    // --- CLI App ---
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("=== Java Calculator ===");

            while (true) {
                printMenu();
                int choice = readInt(in, "Choose option (1-7): ");

                if (choice == 7) {
                    System.out.println("Goodbye!");
                    break;
                }

                double a = readDouble(in, "Enter first number: ");
                double b = readDouble(in, "Enter second number: ");

                try {
                    double result = switch (choice) {
                        case 1 -> add(a, b);
                        case 2 -> subtract(a, b);
                        case 3 -> multiply(a, b);
                        case 4 -> divide(a, b);
                        case 5 -> mod(a, b);
                        case 6 -> power(a, b);
                        default -> {
                            System.out.println("Invalid option. Try again.");
                            yield Double.NaN;
                        }
                    };

                    if (!Double.isNaN(result)) {
                        System.out.printf("Result: %.6f%n%n", result);
                    }
                } catch (IllegalArgumentException ex) {
                    System.out.println("Error: " + ex.getMessage() + "\n");
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println("1) +  Add");
        System.out.println("2) -  Subtract");
        System.out.println("3) *  Multiply");
        System.out.println("4) /  Divide");
        System.out.println("5) %  Modulus");
        System.out.println("6) ^  Power");
        System.out.println("7) Exit");
    }

    private static int readInt(Scanner in, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                in.nextLine();
            }
        }
    }

    private static double readDouble(Scanner in, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return in.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number (e.g., 12 or 12.5).");
                in.nextLine();
            }
        }
    }
}
