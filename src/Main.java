//import menu.Menu;
import menu.Menu;
import user.*;
import utils.Divider;

        import java.util.Scanner;

public class Main {
    private static Menu appMenu;
    private static User loggedUser = null;

    public static void main(String[] args) {
        Menu appMenu = null;

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
        Divider.printDivider();
        System.out.println("      COSC2081 GROUP ASSIGNMENT      ");
        System.out.println("  AUTO168 CAR DEALERSHIP MANAGEMENT SYSTEM  ");
        Divider.printDivider();
        System.out.println("Instructor: Mr. Minh Vu & Mr. Dung Nguyen");
        System.out.println("Group: Group Name");
        System.out.println("s3979239, Nguyen Pham Tien Hai");
        System.out.println("sXXXXXXX, Student Name");
        System.out.println("sXXXXXXX, Student Name");
        System.out.println("sXXXXXXX, Student Name");
    }

    private static User displayHomePage() {
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
                System.out.println("Exiting the application... Goodbye!");
                input.close();
                return null;  // Exit and return null if user enters -1
            }

            if (Authenticator.authenticate(username, password)) {
                System.out.println("Login successful!");
                Divider.printDivider();
                return Authenticator.loggedUser;  // Return the authenticated user
            } else {
                System.out.println("Login failed! Please try again.");
            }
        }
    }
}
