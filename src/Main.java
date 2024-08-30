import menu.Menu;
import user.Authenticator;
import user.User;
import utils.Divider;

import java.util.Scanner;

public class Main {
    private static Menu appMenu;
    private static User loggedUser = null;

    public static void main(String[] args) {
        // Run the program
        // While loop is used here because when user logout it returns false, which is inverted and becomes true, so the loop continues
        do {
            loggedUser = displayHomePage();
            if (loggedUser != null) {
                appMenu = new Menu();
                Menu.setLoggedUser(loggedUser);  // Set the loggedUser for Menu
            }
        } while (loggedUser != null && appMenu.run());
    }

    //Login logic
    private static User displayHomePage() {
        // Login
        Divider.printDivider();
        System.out.println("Please login to an account");
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Username: ");
            String username = input.nextLine().trim();

            System.out.print("Password: ");
            String password = input.nextLine().trim();

            if (username.equals("-1") || password.equals("-1")) {
                return null;  // Exit and return null if user enters -1
            }

            if (Authenticator.authenticate(username, password)) {
                System.out.println("Login successful!");
                Divider.printDivider();
                System.out.println("Welcome back, " + username + "!");
                return Authenticator.loggedUser;  // Return the authenticated user
            } else {
                System.out.println("Login failed! Please try again.");
            }
        }

    }

}
