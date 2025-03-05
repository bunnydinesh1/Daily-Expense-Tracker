package src;

public class Expense {
    private double amount;
    private String category;
    private String description;
    private String date;

    // Constructor
    public Expense(double amount, String category, String description, String date) {
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    // Getters
    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    // To display expense details nicely
    @Override
    public String toString() {
        return "Expense{" +
                "amount=" + amount +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
