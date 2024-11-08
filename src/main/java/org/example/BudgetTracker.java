package org.example;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class BudgetTracker {

    private ExpenseStorage expenseStorage;
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
        budgetTracker.run();  // Starta programmet
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

    }

    private void manageIncome() {

    }

    private void manageBudget() {

    }
}