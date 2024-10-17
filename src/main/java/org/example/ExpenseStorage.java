package org.example;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public class ExpenseStorage {

    private ArrayList<Expense> expenses;
    private String filename; = "src/main/expenses.json";
    Gson gson = new Gson();
    String json = gson.toJson(expenses);


    public void addExpense(Expense expense) {
        expenses.add(expense);

        System.out.println(": " + expense + ".");
    }

    public void removeExpense(Expense expense) {
        expenses.remove(expense);
        System.out.println(": " + expense + ".");
    }

    public void updateExpense(Expense expense) {
        expenses.set(expenses.indexOf(expense), expense);
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    public void readExpenses() throws IOException {
        Type gson = new GsonToken<Map<String, Expense>>() {}.getT

    }

}
