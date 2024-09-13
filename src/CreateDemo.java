import interfaces.*;
import operations.*;
import java.util.Scanner;

public class CreateDemo {
    // Service references
    static CarInterfaces carService = new CarService();
    static AutoPartInterfaces autoPartService = new AutoPartService();
    static ServiceInterfaces serviceService = new ServiceService();
    static TransactionInterfaces transactionService = new TransactionService();
    static UserInterfaces userService = new UserService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueRunning = true;

        while (continueRunning) {
            System.out.println("Select an option:");
            System.out.println("1. Create User");
            System.out.println("2. Create Car");
            System.out.println("3. Add Auto Part");
            System.out.println("4. Add Service");
            System.out.println("5. Add Transaction");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    userService.createUser();
                    break;
                case 2:
                    carService.createCar();
                    break;
                case 3:
                    autoPartService.addPart();
                    break;
                case 4:
                    serviceService.addService();
                    break;
                case 5:
                    transactionService.addTransaction();
                    break;
                case 6:
                    continueRunning = false;
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
        scanner.close();
    }
}
