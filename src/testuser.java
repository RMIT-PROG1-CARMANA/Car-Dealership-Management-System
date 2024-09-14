import filehandling.UserDataHandler;
import user.Manager;
import user.Mechanic;
import user.Salesperson;
import user.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class testuser {

    // Method to add a user to the system
    public static void addUser(User newUserManager, UserDataHandler userDAO) {
        List<User> managersList = userDAO.fetchManagerFromDatabase();
        managersList.add(newUserManager);
        userDAO.writeUsersToFile(managersList.toArray(new User[0]));
    }

    public static void main(String[] args) {
        // Create UserDataHandler instance
        final UserDataHandler userDAO = new UserDataHandler();

        // Create date formatter
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Creating Manager
//            User manager = new Manager("u-0001", "Alice Manager", sdf.parse("15/05/1980"), "123 Main St", 9876543210L, "alice.manager@example.com", true, "aliceManager", "password123");
//
//            // Creating Salesperson
//            User salesperson = new Salesperson("u-0002", "Dzi Sales", sdf.parse("12/02/1990"), "12 Thu Duc", 1234567890L, "dzi.sales@example.com", true, "dziPass", "dziSales");
//
//            User salesperson1 = new Salesperson("u-0003", "Bob Sales", sdf.parse("20/06/2000"), "456 Market St", 1234567890L, "bob.sales@example.com", true, "bobPass", "bobSales");
//
//            // Creating Mechanic
//            User mechanic = new Mechanic("u-0004", "Charlie Mechanic", sdf.parse("25/07/1985"), "789 Service Ave", 9876543210L, "charlie.mechanic@example.com", true, "charliePass", "charlieMech");
//
//            User mechanic1 = new Mechanic("u-0005", "Hai Mechanic", sdf.parse("15/11/1992"), "321 Hill St", 9876543210L, "hai.mechanic@example.com", true, "haiPass", "HaiMech");
//
//            // Adding users to the system
//            addUser(manager, userDAO);
//            addUser(salesperson, userDAO);
//            addUser(salesperson1, userDAO);
//            addUser(mechanic, userDAO);
//            addUser(mechanic1, userDAO);



        System.out.println("Test users created successfully and saved to the users file.");
    }
}
