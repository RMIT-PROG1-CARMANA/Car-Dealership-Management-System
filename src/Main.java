//import menu.Menu;
import menu.Menu;
import user.*;
import utils.Divider;
import java.io.Console;
import java.util.Scanner;

public class Main {
    private static Menu appMenu;
    private static User loggedUser = null;

    public static void main(String[] args) {

        do {
            displayWelcomeScreen();  // Display welcome screen again after logout
            loggedUser = displayHomePage();  // Prompt user to log in

            if (loggedUser != null) {
                appMenu = new Menu();
                Menu.setLoggedUser(loggedUser);  // Set the loggedUser for Menu

                // Run the menu and check if the user should continue
                boolean shouldContinue = appMenu.run();
                if (!shouldContinue) {
                    loggedUser = null;  // Reset loggedUser to trigger the login screen
                }
            }
        }  while (true);
    }

    // Function to display the welcome screen
    private static void displayWelcomeScreen() {
        Console console = System.console();
        if (console == null) {
            System.err.println("No console available");
            return;
        }

        // Colors
        final String RESET = "\033[0m";  // Reset
        final String GREEN_BOLD = "\033[1;32m";  // Green
        final String CYAN_BOLD = "\033[1;36m";   // Cyan
        final String YELLOW_BOLD = "\033[1;33m"; // Yellow

        // Print divider
        Divider.printDivider();

        // Welcome message
        System.out.println(GREEN_BOLD + "      COSC2081 GROUP ASSIGNMENT      " + RESET);
        System.out.println(CYAN_BOLD + "  AUTO168 CAR DEALERSHIP MANAGEMENT SYSTEM  " + RESET);

        // Print divider
        Divider.printDivider();

        // Instructor and group info
        System.out.println(YELLOW_BOLD + "Instructor: Mr. Minh Vu & Mr. Dung Nguyen" + RESET);
        System.out.println(YELLOW_BOLD + "Group: Group Name" + RESET);
        System.out.println(YELLOW_BOLD + "s3979239, Nguyen Pham Tien Hai" + RESET);
        System.out.println(YELLOW_BOLD + "sXXXXXXX, Student Name" + RESET);
        System.out.println(YELLOW_BOLD + "sXXXXXXX, Student Name" + RESET);
        System.out.println(YELLOW_BOLD + "sXXXXXXX, Student Name" + RESET);
    }

    private static User displayHomePage() {
        // Colors
        final String RESET = "\033[0m";  // Reset
        final String GREEN = "\033[0;32m";  // Green
        final String RED = "\033[0;31m";
        final String YELLOW = "\033[0;33m";
        final String BLUE = "\033[0;34m";
        final String CYAN = "\033[0;36m";
        final String YELLOW2 = "\033[0;36m";

        Divider.printDivider();
        System.out.println("          Please login to an account          ");
        System.out.println("         (Enter -1 to exit at any time)        ");
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Username: ");
            String username = input.nextLine().trim();

            System.out.print("Password: ");
            String password = input.nextLine().trim();

            if (username.equals("-1") || password.equals("-1")) {
                System.out.println(GREEN + "Exiting the application... Goodbye!" + RESET);
                input.close();
                System.exit(0);
                return null;  // Exit and return null if user enters -1
            }

            if (Authenticator.authenticate(username, password)) {
                System.out.println(GREEN + "Login successful!" + RESET);
                Divider.printDivider();
                return Authenticator.loggedUser;  // Return the authenticated user
            } else {
                System.out.println(RED + "Login failed! Please try again." + RESET);
            }
        }

    }
}
