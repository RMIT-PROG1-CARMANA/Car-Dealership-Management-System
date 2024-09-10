package utils;

import FileHandling.*;
import operations.ActivityLogService;
import part.AutoPart;
import sales.SalesTransaction;
import user.User;
import service.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;

import FileHandling.UserDataHandler;
import vehicle.Car;


public class InputValidation {
    private static final UserDataHandler userDAO = new UserDataHandler();
    private static final CarDataHandler carDAO = new CarDataHandler();
    private static final ActivityLogService logService = new ActivityLogService();
    private static final SalesTransactionDataHandler transactionDAO = new SalesTransactionDataHandler();

    public static long validateLong(String question) {
        return validateLong((v) -> true, question, "Invalid input, please try again.");
    }

    public static long validateLong(Function<Long, Boolean> function) {
        return validateLong(function, "", "Invalid input, please try again.");
    }

    public static long validateLong(Function<Long, Boolean> function, String question) {
        return validateLong(function, question, "Invalid input, please try again.");
    }

    public static long validateLong(Function<Long, Boolean> function, String question, String errorMessage) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(question);
                long userInput = scanner.nextLong();

                if (userInput == -1) return -1;
                if (function.apply(userInput)) return userInput;
                System.out.println(errorMessage);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid long integer.");
                scanner.nextLine();  // Clear the invalid input
            } catch (Exception e) {
                System.out.println("Error occurred, please try again.");
            }
        }
    }
    public static int validateInt(String question) {
        return validateInt((v) -> true, question, "Invalid input, please try again.");
    }
    public static int validateInt(Function<Integer, Boolean> function) {
        return validateInt(function, "", "Invalid input, please try again.");
    }

    public static int validateInt(Function<Integer, Boolean> function, String question) {
        return validateInt(function, question, "Invalid input, please try again.");
    }


    public static int validateInt(Function<Integer, Boolean> function, String question, String errorMessage) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(question);
                int userInput = scanner.nextInt();

                if (userInput == -1) return -1;
                if (function.apply(userInput)) return userInput;
                System.out.println(errorMessage);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Error occurred, please try again.");
            }
        }
    }
    public static Date validateDate(String question) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(question);
                String userInput = scanner.nextLine();

                if (userInput.equals("-1")) return null;
                Date date = (new SimpleDateFormat("dd/MM/yyyy")).parse(userInput);
                if (date != null) return date;
                System.out.println("Invalid input. Please enter a valid date.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid date.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Error occurred, please try again.");
            }
        }
    }

    public static double validateDouble(String question) {
        return validateDouble((v) -> true, question, "Invalid input, please try again.");
    }

    public static double validateDouble(Function<Double, Boolean> function) {
        return validateDouble(function, "", "Invalid input, please try again.");
    }

    public static double validateDouble(Function<Double, Boolean> function, String question) {
        return validateDouble(function, question, "Invalid input, please try again.");
    }

    public static double validateDouble(Function<Double, Boolean> function, String question, String errorMessage) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(question);
                double userInput = scanner.nextDouble();

                if (userInput == -1) return -1;
                if (function.apply(userInput)) return userInput;
                System.out.println(errorMessage);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid double.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Error occurred, please try again.");
            }
        }
    }

    public static String validateString(String question) {
        return validateString((v) -> true, question, "Invalid input, please try again.");
    }

    public static String validateString(Function<String, Boolean> function) {
        return validateString(function, "", "Invalid input, please try again.");
    }

    public static String validateString(Function<String, Boolean> function, String question) {
        return validateString(function, question, "Invalid input, please try again.");
    }

    public static String validateString(Function<String, Boolean> function, String question, String errorMessage) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(question);
                String userInput = scanner.nextLine();

                if (userInput.equals("-1")) return "-1";
                if (function.apply(userInput)) return userInput;
                System.out.println(errorMessage);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid string.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Error occurred, please try again.");
            }
        }
    }

    public static boolean validateBoolean(String question) {
        String answer = validateString((v) -> Arrays.asList("true", "false", "t", "f", "yes", "no", "y", "n").contains(v.toLowerCase()),
                question,
                "Invalid input. Please enter a valid boolean.");
        return !answer.equals("-1") && Arrays.asList("true", "t", "yes", "y").contains(answer.toLowerCase());
    }


    public static String validateUserID(String promptMessage) {
        Scanner input = new Scanner(System.in);
        String userID;

        while (true) {
            System.out.print(promptMessage);
            userID = input.nextLine().trim();

            // Validate the format of the userID (should be in the form u-xxxx, where xxxx is a number)
            if (!userID.matches("u-\\d{4}")) {
                System.out.println("Invalid User ID format. It should be 'u-XXXX', where 'XXXX' is a number.");
                continue;  // Re-prompt for user input
            }

            // Check if the userID already exists in the database using the existing userDAO
            User[] existingUsers = userDAO.readAllUsers();

            // Check for null or empty user list
            if (existingUsers == null) {
                System.out.println("Error: No users found in the system.");
                break; // Exit loop if no users found (or handle this case appropriately)
            }

            boolean userExists = false;

            // Loop through the existing users and check if any have the same userID
            for (User user : existingUsers) {
                if (user != null && user.getUserID().equals(userID)) {  // Null check before accessing userID
                    System.out.println("User ID already exists. Please enter a different User ID.");
                    userExists = true;
                    break;  // Exit the loop if we find a match
                }
            }

            // If the userID is unique and valid, return it
            if (!userExists) {
                return userID;
            }
        }

        return null; // Default return in case of errors
    }

    public static String validateExistingUserID(String promptMessage) {
        Scanner input = new Scanner(System.in);
        String userID;

        while (true) {
            System.out.print(promptMessage);
            userID = input.nextLine().trim();

            // Validate the format of the userID (should be in the form u-xxxx, where xxxx is a number)
            if (!userID.matches("u-\\d{4}")) {
                System.out.println("Invalid User ID format. It should be 'u-XXXX', where 'XXXX' is a number.");
                continue;  // Re-prompt for user input
            }

            // Check if the userID exists in the database using the existing userDAO
            User[] existingUsers = userDAO.readAllUsers();

            // Check for null or empty user list
            if (existingUsers == null || existingUsers.length == 0) {
                System.out.println("Error: No users found in the system.");
                return null;  // Exit method if no users found (or handle this case appropriately)
            }

            boolean userExists = false;

            // Loop through the existing users and check if any have the same userID
            for (User user : existingUsers) {
                if (user != null && user.getUserID().equals(userID)) {  // Null check before accessing userID
                    userExists = true;
                    break;  // Exit the loop if we find a match
                }
            }

            // If the userID exists, return it
            if (userExists) {
                return userID;
            } else {
                System.out.println("User ID does not exist. Please enter a valid User ID.");
            }
        }
    }


    // Validate username format and check if it exists in one method
    public static String validateUsername(String question) {
        Scanner input = new Scanner(System.in);
        String username;

        while (true) {
            username = InputValidation.validateString(question); // Retrieve user input

            // Check if the username format is correct
            if (!username.matches("[a-zA-Z0-9_]{5,15}")) {  // Example format: alphanumeric, 5 to 15 characters
                System.out.println("Invalid Username format. Please ensure it follows the correct format.");
                continue;  // Re-prompt the user
            }

            // Check if the username already exists in the database
            User[] existingUsers = userDAO.readAllUsers();
            boolean usernameExists = false;

            for (User user : existingUsers) {
                if (user != null && user.getUsername() != null && user.getUsername().equals(username)) {
                    System.out.println("Username already exists. Please choose a different one.");
                    usernameExists = true;
                    break;
                }
            }

            // If the username is valid and doesn't exist, return it
            if (!usernameExists) {
                return username;
            }
        }
    }



    // Validate User ID format (uXXXX)
    public static String validateUserIDFormat(String question) {
        Scanner input = new Scanner(System.in);
        String userID;

        while (true) {
            System.out.print(question);
            userID = input.nextLine().trim();
            // Validate the format of the userID (should be in the form u-xxxx, where xxxx is a number)
            if (!userID.matches("u-\\d{4}")) {
                System.out.println("Invalid User ID format. It should be 'u-XXXX', where 'XXXX' is a number.");
                continue;  // Re-prompt for user input
            }
        }
    }

    // Check if the user ID already exists in the database
    private static boolean isUserIDExist(String userID) {
        for (User user : userDAO.readAllUsers()) {
            if (user.getUserID().equals(userID)) {
                System.out.println("User ID already exists. Please choose a different one.");
                return true;
            }
        }
        return false;
    }

    // Method to check the username format (you can define your own format or use a regex)
    public static String validateUsernameFormat(String question) {
        Scanner input = new Scanner(System.in);
        String username;

        while (true) {
            System.out.print(question);  // Prompt user for input
            username = input.nextLine(); // Retrieve user input

            // Check if the username format is correct
            if (username.matches("[a-zA-Z0-9_]{5,15}")) {
                return username;  // Return valid username
            } else {
                System.out.println("Invalid Username format. Please ensure it follows the correct format.");
            }
        }
    }


    // Check if the username already exists in the database
    private static boolean isUsernameExist(String username) {
        for (User user : userDAO.readAllUsers()) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists. Please choose a different one.");
                return true;
            }
        }
        return false;
    }

    public static String validateCarID(String question) {
        Scanner input = new Scanner(System.in);
        String carID;

        // Load the car database before checking for existing car IDs
        carDAO.loadCarDatabase("src/DataBase/CarDatabase.txt");

        while (true) {
            carID = InputValidation.validateString(question); // Retrieve user input

            // Check if the Car ID format is correct
            if (!carID.matches("C-\\d{4}")) {  // Example format: C-XXXX
                System.out.println("Invalid Car ID format. It should be 'C-XXXX', where 'XXXX' is a number.");
                continue;  // Re-prompt the user
            }

            // Check if the Car ID already exists in the database
            if (isCarIDExists(carID)) {
                System.out.println("Car ID already exists. Please enter a different Car ID.");
                continue;  // Re-prompt the user
            }

            // If the Car ID is valid and doesn't exist, return it
            return carID;
        }
    }

    // Method to check if the Car ID already exists
    public static boolean isCarIDExists(String carID) {
        ArrayList<Car> cars = carDAO.getCars();
        for (Car car : cars) {
            if (car.getCarID().equals(carID)) {
                return true;
            }
        }
        return false;
    }

    public static String validateCarIDFormat(String question) {
        Scanner input = new Scanner(System.in);
        String carID;

        while (true) {
            carID = InputValidation.validateString(question); // Retrieve user input

            // Check if the Car ID format is correct
            if (!carID.matches("C-\\d{4}")) {  // Example format: C-XXXX
                System.out.println("Invalid Car ID format. It should be 'C-XXXX', where 'XXXX' is a number.");
                continue;  // Re-prompt the user
            }
        }
    }

    public static String validatePartID(String promptMessage) {
        Scanner input = new Scanner(System.in);
        String partID;

        while (true) {
            System.out.print(promptMessage);
            partID = input.nextLine().trim();

            // Validate the format of the partID (should be in the form p-xxxx, where xxxx is a number)
            if (!partID.matches("p-\\d{4}")) {
                System.out.println("Invalid Part ID format. It should be 'p-XXXX', where 'XXXX' is a number.");
                continue;  // Re-prompt for user input
            }

            // Check if the partID already exists in the parts list
            List<AutoPart> existingParts = AutoPart.getAllParts();  // Retrieve all parts

            // Allow if the list is empty
            if (existingParts.isEmpty()) {
                return partID;  // If no parts exist, return the entered partID
            }

            String finalPartID = partID;
            boolean partExists = existingParts.stream().anyMatch(part -> part.getPartID().equals(finalPartID));

            // If the partID is unique and valid, return it
            if (!partExists) {
                return partID;
            } else {
                System.out.println("Part ID already exists. Please enter a different Part ID.");
            }
        }
    }


    public static boolean validatePartIDFormat(String question) {
        Scanner input = new Scanner(System.in);
        String partID;

        while (true) {
            System.out.print(question);
            partID = input.nextLine().trim();

            // Validate the format of the partID (should be in the form p-xxxx, where xxxx is a number)
            if (!partID.matches("p-\\d{4}")) {
                System.out.println("Invalid Part ID format. It should be 'p-XXXX', where 'XXXX' is a number.");
                continue;  // Re-prompt for user input
            }

        }
    }

    public static boolean doesPartIDExist(String partID, List<AutoPart> existingParts) {
        return existingParts.stream()
                .anyMatch(part -> part.getPartID().equals(partID));
    }

    public static String validateServiceID(String promptMessage, List<Service> existingServices) {
        Scanner scanner = new Scanner(System.in);
        String serviceID;
        while (true) {
            System.out.print(promptMessage);
            serviceID = scanner.nextLine().trim();

            // Check if input matches a valid format (e.g., S-XXXX where XXXX is a number)
            if (!serviceID.matches("^S-\\d{4}$")) {
                System.out.println("Invalid Service ID format. It should be in the format 'S-XXXX' where 'XXXX' are digits.");
                continue;
            }

            // Check if ServiceID is unique
            String finalServoceID = serviceID;
            boolean isDuplicate = existingServices.stream()
                    .anyMatch(service -> service.getServiceID().equals(finalServoceID));

            if (isDuplicate) {
                System.out.println("Service ID already exists. Please enter a unique Service ID.");
            } else {
                break; // Service ID is valid and unique
            }
        }
        return serviceID;
    }


    public static String validateServiceIDFormat(String promptMessage, List<Service> existingServices) {
        Scanner scanner = new Scanner(System.in);
        String serviceID;
        while (true) {
            System.out.print(promptMessage);
            serviceID = scanner.nextLine().trim();
            // Check if input matches a valid format (e.g., S-XXXX where XXXX is a number)
            if (!serviceID.matches("^S-\\d{4}$")) {
                System.out.println("Invalid Service ID format. It should be in the format 'S-XXXX' where 'XXXX' are digits.");
                continue;
            }
        }
    }

    public static boolean doesServiceIDExist(String serviceID, List<Service> existingServices) {
        return existingServices.stream()
                .anyMatch(service -> service.getServiceID().equals(serviceID));
    }

    public static String validateLogID(String promptMessage) {
        Scanner input = new Scanner(System.in);
        String logID;

        while (true) {
            System.out.print(promptMessage);
            logID = input.nextLine().trim();

            // Validate the format of the logID (should be in the form log-xxxx, where xxxx is a number)
            if (!logID.matches("log-\\d{4}")) {
                System.out.println("Invalid Log ID format. It should be 'log-XXXX', where 'XXXX' is a number.");
                continue;  // Re-prompt the user
            }

            // Check if the Log ID exists
            if (logService.viewLogById(logID).isEmpty()) {
                System.out.println("Log ID does not exist. Please enter a valid Log ID.");
                continue;  // Re-prompt the user
            }

            // If the Log ID is valid and exists, return it
            return logID;
        }
    }

    public static String validateTransactionID(String promptMessage) {
        Scanner input = new Scanner(System.in);
        String transactionID;

        while (true) {
            System.out.print(promptMessage);
            transactionID = input.nextLine().trim();

            // Validate the format of the transactionID (should be in the form t-XXXX, where XXXX is a number)
            if (!transactionID.matches("t-\\d{4}")) {
                System.out.println("Invalid Transaction ID format. It should be 't-XXXX', where 'XXXX' is a number.");
                continue;  // Re-prompt for user input
            }

//             Fetch the existing transactions

            transactionDAO.loadTransactionDatabase(); // Ensure transactions are loaded
            List<SalesTransaction> existingTransactions = transactionDAO.transactions;

            // Check if the transactionID already exists in the list

            String finalTransactionID = transactionID;
            boolean transactionExists = existingTransactions.stream()
                    .anyMatch(transaction -> transaction.getTransactionID().equals(finalTransactionID));

            if (transactionExists) {
                System.out.println("Transaction ID already exists. Please enter a different Transaction ID.");
            } else {
                // If the ID is valid and unique, return it
                return transactionID;
            }

        }
    }



}