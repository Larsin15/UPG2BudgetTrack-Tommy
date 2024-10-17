package org.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExpenseStorage {

    private Map<String, Expense> expensesList;
    private ArrayList<Expense> expenses;
    Expense expense = new Expense(300.75, "2024-10-15", EExpenseCategory.MAT);
    Gson gson = new Gson();


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
        Reader reader = new FileReader("expenses.json");
        Expense expense = gson.fromJson(reader, Expense.class);
        expensesList = new HashMap<>();



    }

    public void saveExpenses() throws IOException {
        FileWriter writer = new FileWriter("expenses.json");
        gson.toJson(expensesList, writer);
        writer.close();
    }

}
