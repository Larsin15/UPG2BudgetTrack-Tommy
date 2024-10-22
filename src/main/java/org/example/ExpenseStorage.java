package org.example;
import com.google.gson.Gson;


import java.io.*;
import java.util.*;

public class ExpenseStorage {

    private Map<String, Expense> expensesList = new HashMap<>();
    private String fileName = "expenses.json";
    Expense expense = new Expense(300.75, new Date(), EExpenseCategory.MAT);


    public void addExpense(Expense expense) {
        expensesList.put(expense.getId(), expense);
    }

    public boolean removeExpense(String expenseId) {
        return expensesList.remove(expenseId) != null;
    }

    public boolean updateExpense(String expenseId, Expense newExpense) {
        if (expensesList.containsKey(expenseId)) {
            expensesList.put(expenseId, newExpense);
            return true;
        } else {
            System.out.println("Ingen utgift med ID " + expenseId + " finns inte");
            return false;
        }
    }

    public Collection<Expense> getExpenses() {
        return expensesList.values();
    }

    public void readExpenses(String fileName) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(fileName)) {
            Expense[] readExpenses = gson.fromJson(reader, Expense[].class);
            expensesList.clear();
            for (Expense expense : readExpenses) {
                expensesList.put(expense.getId(), expense);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveExpenses(String fileName) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(expensesList.values(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
