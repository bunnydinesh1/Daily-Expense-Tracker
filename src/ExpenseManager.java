package src;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpenseManager {
    private List<Expense> expenses = new ArrayList<>();
    private static final String FILE_NAME = "expenses.txt";

    // Add an expense to the list
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    // Display all expenses
    public void showExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to display.");
            return;
        }
        for (int i = 0; i < expenses.size(); i++) {
            System.out.println((i + 1) + ". " + expenses.get(i));
        }
    }

    // Edit an expense by its index
    public void editExpense(int index, double amount, String category, String description, String date) {
        if (index >= 0 && index < expenses.size()) {
            Expense expense = expenses.get(index);
            expense = new Expense(amount, category, description, date);
            expenses.set(index, expense);  // Update the expense at the index
        } else {
            System.out.println("Invalid expense index.");
        }
    }

    // Save expenses to a text file
    public void saveExpenses() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Expense expense : expenses) {
                writer.write(expense.getAmount() + "," + expense.getCategory() + "," + expense.getDescription() + "," + expense.getDate());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load expenses from a text file
    public void loadExpenses() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Expense expense = new Expense(
                    Double.parseDouble(data[0]),
                    data[1],
                    data[2],
                    data[3]
                );
                expenses.add(expense);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Calculate the total expenses
    public double getTotalExpenses() {
        double total = 0.0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        return total;
    }

    // View expenses by category
    public void viewExpensesByCategory(String category) {
        boolean found = false;
        for (Expense expense : expenses) {
            if (expense.getCategory().equalsIgnoreCase(category)) {
                System.out.println(expense);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No expenses found for the category: " + category);
        }
    }

    // View expenses by specific date
    public void viewExpensesByDate(String date) {
        boolean found = false;
        for (Expense expense : expenses) {
            if (expense.getDate().equals(date)) {
                System.out.println(expense);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No expenses found for the date: " + date);
        }
    }

    // Delete an expense by index
    public void deleteExpense(int index) {
        if (index >= 0 && index < expenses.size()) {
            expenses.remove(index);
            System.out.println("Expense removed successfully.");
        } else {
            System.out.println("Invalid expense index.");
        }
    }

    // View total expenses for a specific month (e.g., "2025-03")
    public void viewTotalExpensesForMonth(String monthYear) {
        double total = 0.0;
        for (Expense expense : expenses) {
            if (expense.getDate().startsWith(monthYear)) {
                total += expense.getAmount();
            }
        }
        System.out.println("Total expenses for " + monthYear + ": " + total);
    }

    // View total expenses for a specific week
    public void viewTotalExpensesForWeek(String startDate, String endDate) {
        double total = 0.0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);

            for (Expense expense : expenses) {
                Date expenseDate = sdf.parse(expense.getDate());
                if ((expenseDate.equals(start) || expenseDate.after(start)) && (expenseDate.equals(end) || expenseDate.before(end))) {
                    total += expense.getAmount();
                }
            }

            System.out.println("Total expenses from " + startDate + " to " + endDate + ": " + total);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Search expenses by description
    public void searchExpensesByDescription(String keyword) {
        boolean found = false;
        for (Expense expense : expenses) {
            if (expense.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(expense);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No expenses found with the description containing: " + keyword);
        }
    }

    // Export expenses to CSV
    public void exportExpensesToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("expenses_export.csv"))) {
            writer.write("Amount,Category,Description,Date\n");
            for (Expense expense : expenses) {
                writer.write(expense.getAmount() + "," + expense.getCategory() + "," + expense.getDescription() + "," + expense.getDate() + "\n");
            }
            System.out.println("Expenses exported to expenses_export.csv successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // View expenses sorted by date
    public void viewExpensesSortedByDate() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to display.");
            return;
        }

        expenses.sort((e1, e2) -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date1 = sdf.parse(e1.getDate());
                Date date2 = sdf.parse(e2.getDate());
                return date1.compareTo(date2);
            } catch (Exception ex) {
                ex.printStackTrace();
                return 0;
            }
        });

        // Display sorted expenses
        showExpenses();  // This will print the expenses sorted by date
    }
}
