package org.example;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class BudgetTracker {

    private static ExpenseStorage expenseStorage;
    private IncomeStorage incomeStorage;
    private static UserStorage userStorage = new UserStorage();
    private static User currentUser = null;
    private Scanner scanner;

    // Konstruktorn för BudgetTracker
    public BudgetTracker() {
        scanner = new Scanner(System.in);
        expenseStorage = new ExpenseStorage();
        incomeStorage = new IncomeStorage();

    }

    // Main-metoden där programmet startar
    public static void main(String[] args) {
        BudgetTracker budgetTracker = new BudgetTracker();
        userStorage.readUsers("users.txt");
        expenseStorage.readExpenses("expenses.txt");
        //incomeStorage.readIncomes("incomes.txt");
        budgetTracker.run();  // Starta programmet
        Expense expense = new Expense(300.75, new Date(), EExpenseCategory.MAT);
    }
    public void run() {
        boolean menuActive = true;

        while (menuActive) {
            System.out.println("\nVälj alternativ 1-3:"
                    + "\n1: Logga in"
                    + "\n2: Skapa konto"
                    + "\n3: Avsluta");

            try {
                int menuChoice = scanner.nextInt();
                scanner.nextLine();

                switch (menuChoice) {
                    case 1:
                        pickUser();
                        if (currentUser != null) {
                            userMenu();
                        }
                        break;
                    case 2:
                        createUser();
                        break;
                    case 3:
                        menuActive = false;
                        expenseStorage.saveExpenses("expenses.txt");
                        incomeStorage.saveIncomes("incomes.txt");
                        userStorage.saveUsers("users.txt");
                        System.out.println("Välkommen åter.");
                        break;
                    default:
                        System.out.println("Ogiltigt val, försök igen.");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Använd enbart siffror som input.");
                scanner.nextLine();
            }
        }
    }

    private void pickUser() {
        Map<Integer, User> usersList = userStorage.getUsers();

        if (usersList.isEmpty()) {
            System.out.println("Inga användare tillgängliga.");
            return;
        }

        System.out.println("Välj användare:");
        for (Map.Entry<Integer, User> entry : usersList.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getFirstName() + " " + entry.getValue().getLastName());
        }

        System.out.print("Ange användarens ID: ");
        int userId = scanner.nextInt();

        if (usersList.containsKey(userId)) {
            currentUser = usersList.get(userId);
            System.out.println("Inloggad som: " + currentUser.getFirstName() + " " + currentUser.getLastName());
        } else {
            System.out.println("Ogiltigt val, försök igen.");
        }
    }

    // Låter användaren skapa ett nytt konto
    private void createUser() {
        System.out.print("Förnamn: ");
        String firstName = scanner.nextLine();
        System.out.print("Efternamn: ");
        String lastName = scanner.nextLine();

        int newUserId = userStorage.getUsers().size() + 1;
        User newUser = new User(firstName, lastName, newUserId);
        userStorage.addUser(newUserId, newUser);

        System.out.println("Användare skapad: " + firstName + " " + lastName + " med ID: " + newUserId);
    }

    private void userMenu() {
        boolean userMenuActive = true;

        while (userMenuActive) {
            System.out.println("\n1: Utgifter"
                    + "\n2: Ikomster"
                    + "\n3: Budget"
                    + "\n4: Logga ut");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manageExpense();
                    break;
                case 2:
                    manageIncome();
                    break;
                case 3:
                    manageBudget();
                    break;
                case 4:
                    userMenuActive = false;
                    break;
                default:
                    System.out.println("Ogiltigt val");
                    break;
            }
        }
    }

    private void manageExpense() {
        boolean expenseMenuActive = true;

        while (expenseMenuActive) {
            System.out.println("\n1: Lägg till utgift"
                    + "\n2: Visa alla utgifter"
                    + "\n3: Gå tillbaka");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    showExpenses();
                    break;
                case 3:
                    expenseMenuActive = false;
                    break;
                default:
                    System.out.println("Error: Enbart 1-3 som input");

            }
        }

    }

    private void addExpense() {
        System.out.println("Ange belopp för ny utgift: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Välj kategori: ");
        for (EExpenseCategory category : EExpenseCategory.values()) {
            System.out.println((category.ordinal() + 1) + ": " + category.name());
        }
        int categoryChoice = scanner.nextInt();
        scanner.nextLine();

        EExpenseCategory category = EExpenseCategory.values()[categoryChoice - 1];
        Expense newExpense = new Expense(amount, new Date(), category);
        expenseStorage.addExpense(newExpense);
        System.out.println("Utgiften är tillagd.");
    }

    private void showExpenses() {
        System.out.println("Dina utgifter:");
        for (Expense expense : expenseStorage.getExpenses()) {
            System.out.println("Datum: " + expense.getId() + ", Belopp " + expense.getAmount() + ", " + expense.getCategory());
        }
    }

    private void manageIncome() {
        boolean incomeMenuActive = true;

        while (incomeMenuActive) {
            System.out.println("\n1: Lägg till inkomst"
                    + "\n2: Visa alla inkomster"
                    + "\n3: Gå tillbaka");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addIncome();
                    break;
                case 2:
                    showIncome();
                    break;
                case 3:
                    incomeMenuActive = false;
                    break;
                default:
                    System.out.println("Error: Enbart 1-3 som input");

            }
        }
    }

    private void addIncome() {
        System.out.println("Ange belopp för ny inkomst: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Välj kategori: ");
        for (EIncomeCategory category : EIncomeCategory.values()) {
            System.out.println((category.ordinal() + 1) + ": " + category.name());
        }
        int categoryChoice = scanner.nextInt();
        scanner.nextLine();

        EIncomeCategory category = EIncomeCategory.values()[categoryChoice - 1];
        Income newIncome = new Income(amount, new Date(), category);
        incomeStorage.addIncome(newIncome);
        System.out.println("Inkomst är tillagd.");
    }

    private void showIncome() {
        boolean showIncomeMenuActive = true;

        while (showIncomeMenuActive) {
            System.out.println("Dina ikomster:");
            for (Income income : incomeStorage.getIncomes()) {
                System.out.println("Datum: " + income.getId() + ", Belopp " + income.getAmount() + ", " + income.getCategory());
            }
            System.out.println("\n1: Ändra en inkomst"
                    + "\n2: Ta bort en ikomst"
                    + "\n3: Gå tillbaka");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    updateIncome();
                    break;
                case 2:
                    //deleteIncome();
                    break;
                case 3:
                    showIncomeMenuActive = false;
                    break;
                default:
                    System.out.println("Error: Enbart 1-3 som input");
            }
        }

    }

    private void updateIncome() {

        Map<Integer, Income> incomeList = incomeStorage.getIncomes();

        if (incomeList.isEmpty()) {
            System.out.println("Inga inkomster tillgängliga.");
            return;
        }

        System.out.println("Välj inkomst att uppdatera:");
        for (Map.Entry<Integer, Income> entry : incomeList.entrySet()) {
            System.out.println(entry.getKey() + ": Belopp: " + entry.getValue().getId() + ", " + entry.getValue().getCategory());
        }

        int incomeId = scanner.nextInt();
        scanner.nextLine();

        if (!incomeList.containsKey(incomeId)) {
            System.out.println("Ogiltigt val, försök igen.");
            return;
        }

        Income selectedIncome = incomeList.get(incomeId);

        System.out.println("Ange det nya beloppet");
        double updatedIncomeAmount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Välj kategori: ");
        for (EExpenseCategory category : EExpenseCategory.values()) {
            System.out.println((category.ordinal() + 1) + ": " + category.name());
        }

        int categoryChoice = scanner.nextInt();
        scanner.nextLine();

        Date oldDate = selectedIncome.getDate();

        EIncomeCategory newCategory = EIncomeCategory.values()[categoryChoice - 1];
        Income newIncome = new Income(updatedIncomeAmount, oldDate, newCategory);

        if (incomeStorage.updateIncome(incomeId, newIncome)) {
            System.out.println("Inkomst uppdaterad.");
        } else {
            System.out.println("Försök igen, något gick fel");
        }

    }

    private void manageBudget() {

    }
}