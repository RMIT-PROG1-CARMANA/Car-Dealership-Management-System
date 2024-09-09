package utils;

import FileHandling.UserDataHandler;
import part.AutoPart;
import user.User;
import service.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;

import FileHandling.UserDataHandler;
import user.*;
import service.*;
import part.*;
import sales.*;


public class InputValidation {
    private static final UserDataHandler userDAO = new UserDataHandler();

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


    // Validate User ID format (uXXXX)
    public static String validateUserIDFormat(String question) {
        return validateString((userID) -> userID.matches("u\\d{4}"),
                question, "Invalid User ID format. It should be 'uXXXX', where 'XXXX' is a number.");
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
    private static boolean validateUsernameFormat(String username) {
        // Example: Username should be alphanumeric and 3-15 characters long
        return username.matches("[a-zA-Z0-9]{3,15}");
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

    // Validate username format and check if it exists in one method
    public static String validateUsername(String question) {
        String username = InputValidation.validateString(question); // Retrieve user input
        // Check if the username format is correct
        if (!username.matches("[a-zA-Z0-9_]{5,15}")) {  // Example format: alphanumeric, 5 to 15 characters
            return "Invalid Username format. Please ensure it follows the correct format.";
        }
        // Check if the username already exists
//        for (User user : userDAO.readAllUsers()) {
//            if (user.getUsername().equals(username)) {
//                return "Username already exists. Please choose a different one.";
//            }
//        }
        // If all validations pass, return the valid Username
        return username;
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

            // Check for null or empty parts list
            if (existingParts == null || existingParts.isEmpty()) {
                System.out.println("Error: No parts found in the system.");
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




}