package org.example;

public class Income extends Transaction {

    private EIncomeCategory category;

    public Income(double amount, String date, category) {
        super(amount, date);
        this.category = category;

    }

    public EIncomeCategory getCategory() {
        return category;
    }

    public void setCategory(EIncomeCategory category) {
        this.category = category;
    }
}
