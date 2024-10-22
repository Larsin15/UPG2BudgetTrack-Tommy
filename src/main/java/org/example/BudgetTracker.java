package org.example;

import java.util.Scanner;

public class BudgetTracker {

    private ExpenseStorage expenseStorage;
    private IncomeStorage incomeStorage;
    private Scanner scanner;

    public BudgetTracker() {
        expenseStorage = new ExpenseStorage();
        incomeStorage = new IncomeStorage();
        scanner = new Scanner(System.in);

        loadData();
    }

    public static void main(String[] args) {
        BudgetTracker budgetTracker = new BudgetTracker();
        budgetTracker.run();
    }
    public void run() {
        boolean running = true;

        while (running) {
            showMenu();
            String choice =scanner.nextLine();

            switch (choice) {

            }
        }
    }

}
