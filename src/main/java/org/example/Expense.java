package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Expense extends Transaction {

    private EExpenseCategory category;
    private String id;


    public Expense(Double amount, Date date, EExpenseCategory category) {
        super(amount, date);
        this.id = generateIdFromDate(date);
        this.category = category;

    }

    private String generateIdFromDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy - MMMM - dd | HH:mm:ss ");
        return formatter.format(date);
    }


    public EExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(EExpenseCategory category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }
}
