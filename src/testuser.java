import filehandling.UserDataHandler;
import user.Client;
import user.Manager;
import user.Salesperson;
import user.Mechanic;
import user.User;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class testuser {

    // Method to add a user to the system
    public static void addUser(User newUser, UserDataHandler userDAO) {
        List<User> usersList = userDAO.fetchManagerFromDatabase(); // Assuming this method gets all users including managers
        usersList.add(newUser);
        userDAO.writeUsersToFile(usersList.toArray(new User[0]));
    }

    public static void main(String[] args) {
        // Create UserDataHandler instance
        UserDataHandler userDAO = new UserDataHandler();

        // Create date formatter
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            // Creating Managers
            User manager = new Manager("u-0001", "Alice Manager", sdf.parse("15/05/1980"), "123 Main St", 9876543210L, "alice.manager@example.com", true, "aliceManager", "password123");

            // Creating Salespersons
            User salesperson1 = new Salesperson("u-0002", "Dzi Sales", sdf.parse("12/02/1990"), "12 Thu Duc", 1234567890L, "dzi.sales@example.com", true, "dziPass", "dziSales");
            User salesperson2 = new Salesperson("u-0003", "Bob Sales", sdf.parse("20/06/2000"), "456 Market St", 1234567890L, "bob.sales@example.com", true, "bobPass", "bobSales");

            // Creating Mechanics
            User mechanic1 = new Mechanic("u-0004", "Charlie Mechanic", sdf.parse("25/07/1985"), "789 Service Ave", 9876543210L, "charlie.mechanic@example.com", true, "charliePass", "charlieMech");
            User mechanic2 = new Mechanic("u-0005", "Hai Mechanic", sdf.parse("15/11/1992"), "321 Hill St", 9876543210L, "hai.mechanic@example.com", true, "haiPass", "HaiMech");

            // Creating Clients
            User client1 = new Client("u-0006", "Alice Johnson", sdf.parse("05/03/1985"), "123 Maple Street, Springfield", 5551234L, "alice.johnson@example.com", true, "alice123", "alice85", 32000000.00);
            User client2 = new Client("u-0007", "Bob Smith", sdf.parse("20/07/1990"), "456 Oak Avenue, Springfield", 5555678L, "bob.smith@example.com", true, "bob456", "bob90", 160000000.00);
            User client3 = new Client("u-0008", "Carol White", sdf.parse("12/11/1978"), "789 Pine Road, Springfield", 5559876L, "carol.white@example.com", false, "carol789", "carol78", 22000.00);

            // Adding users to the system
            addUser(manager, userDAO);
            addUser(salesperson1, userDAO);
            addUser(salesperson2, userDAO);
            addUser(mechanic1, userDAO);
            addUser(mechanic2, userDAO);
            addUser(client1, userDAO);
            addUser(client2, userDAO);
            addUser(client3, userDAO);

            System.out.println("Test users created successfully and saved to the users file.");
        } catch (ParseException e) {
            System.err.println("Date parsing error: " + e.getMessage());
        }
    }
}
