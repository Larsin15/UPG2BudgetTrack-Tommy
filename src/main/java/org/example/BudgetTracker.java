package org.example;

import java.util.HashMap;
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
    }

    // Main-metoden där programmet startar
    public static void main(String[] args) {
        BudgetTracker budgetTracker = new BudgetTracker();
        budgetTracker.run();  // Starta programmet med run()
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
                        break;
                    case 2:
                        createUser();
                        break;
                    case 3:
                        menuActive = false;
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
}