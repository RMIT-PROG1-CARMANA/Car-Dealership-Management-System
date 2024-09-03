//import FileHandling.UserDataHandler;
//import user.*;
//
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class TestUserCreation {
//    public static void main(String[] args) {
//        // Create UserDAO instance
//          final UserDataHandler userDAO = new UserDataHandler();
//
//        // Create some test users
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        try {
//            // Creating Manager
//            User manager = new Manager("u001", "Alice Manager", sdf.parse("1980-05-15"), "123 Main St", 9876543210L, "alice.manager@example.com", true, "password123", "aliceManager");
//            userDAO.addManager(manager);
//
//            // Creating Salesperson
//            User salesperson = new Salesperson("u002", "Bob Sales", sdf.parse("1990-06-20"), "456 Market St", 1234567890L, "bob.sales@example.com", true, "salesPass", "bobSales");
//            userDAO.addManager(salesperson);
//
//            // Creating Mechanic
//            User mechanic = new Mechanic("u003", "Charlie Mechanic", sdf.parse("1985-07-25"), "789 Service Ave", 9876543210L, "charlie.mechanic@example.com", true, "mechPass", "charlieMech");
//            userDAO.addManager(mechanic);
//
//            // Creating Client
//
//
//            System.out.println("Test users created successfully and saved to users.txt.");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
