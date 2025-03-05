package src;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseManager expenseManager = new ExpenseManager();
        expenseManager.loadExpenses(); // Load the existing expenses from file at the start

        while (true) {
            System.out.println("\n=== Daily Expense Tracker ===");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Edit Expense");
            System.out.println("4. Delete Expense");
            System.out.println("5. View Total Expenses");
            System.out.println("6. View Expenses by Category");
            System.out.println("7. View Expenses by Date");
            System.out.println("8. Search Expenses by Description");
            System.out.println("9. Export Expenses to CSV");
            System.out.println("10. View Expenses Sorted by Date");
            System.out.println("11. View Total Expenses for a Month");
            System.out.println("12. View Total Expenses for a Week");
            System.out.println("13. Save and Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    // Add an expense
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline

                    System.out.print("Enter category (e.g., Food, Travel, etc.): ");
                    String category = scanner.nextLine();

                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();

                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();

                    Expense expense = new Expense(amount, category, description, date);
                    expenseManager.addExpense(expense);
                    System.out.println("Expense added successfully!");
                    break;

                case 2:
                    // View all expenses
                    expenseManager.showExpenses();
                    break;

                case 3:
                    // Edit an expense
                    System.out.print("Enter the index of the expense to edit: ");
                    int editIndex = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline

                    System.out.print("Enter new amount: ");
                    double newAmount = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Enter new category: ");
                    String newCategory = scanner.nextLine();

                    System.out.print("Enter new description: ");
                    String newDescription = scanner.nextLine();

                    System.out.print("Enter new date: ");
                    String newDate = scanner.nextLine();

                    expenseManager.editExpense(editIndex - 1, newAmount, newCategory, newDescription, newDate);
                    System.out.println("Expense updated successfully!");
                    break;

                case 4:
                    // Delete an expense
                    System.out.print("Enter the index of the expense to delete: ");
                    int deleteIndex = scanner.nextInt();
                    scanner.nextLine();

                    expenseManager.deleteExpense(deleteIndex - 1);
                    break;

                case 5:
                    // View total expenses
                    double totalExpenses = expenseManager.getTotalExpenses();
                    System.out.println("Total expenses: " + totalExpenses);
                    break;

                case 6:
                    // View expenses by category
                    System.out.print("Enter category to view expenses: ");
                    String viewCategory = scanner.nextLine();
                    expenseManager.viewExpensesByCategory(viewCategory);
                    break;

                case 7:
                    // View expenses by date
                    System.out.print("Enter date to view expenses (YYYY-MM-DD): ");
                    String viewDate = scanner.nextLine();
                    expenseManager.viewExpensesByDate(viewDate);
                    break;

                case 8:
                    // Search expenses by description
                    System.out.print("Enter keyword to search in description: ");
                    String keyword = scanner.nextLine();
                    expenseManager.searchExpensesByDescription(keyword);
                    break;

                case 9:
                    // Export expenses to CSV
                    expenseManager.exportExpensesToCSV();
                    break;

                case 10:
                    // View expenses sorted by date
                    expenseManager.viewExpensesSortedByDate();
                    break;

                case 11:
                    // View total expenses for a month
                    System.out.print("Enter month and year (YYYY-MM): ");
                    String monthYear = scanner.nextLine();
                    expenseManager.viewTotalExpensesForMonth(monthYear);
                    break;

                case 12:
                    // View total expenses for a week
                    System.out.print("Enter start date (YYYY-MM-DD): ");
                    String startDate = scanner.nextLine();
                    System.out.print("Enter end date (YYYY-MM-DD): ");
                    String endDate = scanner.nextLine();
                    expenseManager.viewTotalExpensesForWeek(startDate, endDate);
                    break;

                case 13:
                    // Save and exit
                    expenseManager.saveExpenses();
                    System.out.println("Expenses saved. Exiting program...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
