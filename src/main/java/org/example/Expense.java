package org.example;

public class Expense extends Transaction {

    private EExpenseCategory category;

    public Expense(double amount, String date, EExpenseCategory category) {
        super(amount, date);
        this.category = category;

    }


    public EExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(EExpenseCategory category) {
        this.category = category;
    }
}
