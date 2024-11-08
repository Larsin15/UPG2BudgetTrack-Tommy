package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Income extends Transaction {

    private EIncomeCategory category;
    private String id;

    public Income(double amount, Date date, EIncomeCategory category) {
        super(amount, date);
        this.id = generateIdFromDate(date);
        this.category = category;

    }

    private String generateIdFromDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy - MMMM - dd | HH:mm:ss ");
        return formatter.format(date);
    }

    public EIncomeCategory getCategory() {
        return category;
    }

    public void setCategory(EIncomeCategory category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }
}
