package org.example;

import java.util.Scanner;

public class BudgetTracker {

    private ExpenseStorage expenseStorage;
    private IncomeStorage incomeStorage;
    private Scanner scanner;
    boolean exit = false;

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
        boolean MenuActive = true;

        while (MenuActive) {

            System.out.println("Välj alternativ 1-4"
                    + "\n1: Hantera Löner"
                    + "\n2: Skapa ny faktura"
                    + "\n3: Betala fakturor"
                    + "\n4: Avsluta");
            try {

                int menuChoice = scanner.nextInt();

                switch (menuChoice) {
                    case 1:

                }
            }
            showMenu();
            String choice =scanner.nextLine();

            switch (choice) {

            }
        }
    }

}
