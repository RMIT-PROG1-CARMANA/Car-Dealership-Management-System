package part;

import java.util.Scanner;
import java.util.InputMismatchException;

import menu.UserMenu.ManagerMenu;

public class AutoPartMenu {





    {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        // Load parts from file at startup
        AutoPartFileHandler.loadPartsFromFile();

        // Create an instance of ManagerMenu
        ManagerMenu managerMenu = new ManagerMenu();

        while (!exit) {
            // Call the ManagerMenu's method to display the main menu
            managerMenu.displayManagerMenu();

            // Check if we need to exit
            exit = true; // Assume we want to exit after displaying the menu
        }

        // Save parts to file before exiting
        AutoPartFileHandler.savePartsToFile();
        scanner.close();
        System.out.println("Exiting the AutoPart Management System. Goodbye!");
    }




    public static void addPart(Scanner scanner) {
        String partID;
        String partName;
        String manufacturer;
        String partNumber;
        String condition;
        String warranty;
        double cost;
        String notes;

        // Loop until a valid, unique part ID is provided
        while (true) {
            System.out.print("Enter Part ID: ");
            partID = scanner.nextLine();
            // Commented out InputValidation
            // if (!InputValidation.isValidPartID(partID)) {
            //     System.out.println("Invalid Part ID! It must start with 'p-' followed by digits.");
            // } else if (AutoPart.getPartByID(partID) != null) {
            if (AutoPart.getPartByID(partID) != null) {
                System.out.println("Part ID already exists! Please enter a different Part ID.");
            } else {
                break; // Valid and unique part ID, exit loop
            }
        }

        // Loop until a valid part name is provided
        while (true) {
            System.out.print("Enter Part Name: ");
            partName = scanner.nextLine();
            // Commented out InputValidation
            // if (!InputValidation.isNonEmptyString(partName)) {
            //     System.out.println("Part Name cannot be empty!");
            // } else {
            break; // Valid part name, exit loop
            // }
        }

        // Loop until a valid manufacturer is provided
        while (true) {
            System.out.print("Enter Manufacturer: ");
            manufacturer = scanner.nextLine();
            // Commented out InputValidation
            // if (!InputValidation.isNonEmptyString(manufacturer)) {
            //     System.out.println("Manufacturer cannot be empty!");
            // } else {
            break; // Valid manufacturer, exit loop
            // }
        }

        // Loop until a valid part number is provided
        while (true) {
            System.out.print("Enter Part Number: ");
            partNumber = scanner.nextLine();
            // Commented out InputValidation
            // if (!InputValidation.isNonEmptyString(partNumber)) {
            //     System.out.println("Part Number cannot be empty!");
            // } else {
            break; // Valid part number, exit loop
            // }
        }

        // Loop until a valid condition is provided
        while (true) {
            System.out.print("Enter Condition (e.g., new, refurbished): ");
            condition = scanner.nextLine();
            // Commented out InputValidation
            // if (!InputValidation.isNonEmptyString(condition)) {
            //     System.out.println("Condition cannot be empty!");
            // } else {
            break; // Valid condition, exit loop
            // }
        }

        // Loop until a valid warranty is provided
        while (true) {
            System.out.print("Enter Warranty (e.g., 1 year, 6 months): ");
            warranty = scanner.nextLine();
            // Commented out InputValidation
            // if (!InputValidation.isValidWarranty(warranty)) {
            //     System.out.println("Invalid Warranty format! Use format like '1 year' or '6 months'.");
            // } else {
            break; // Valid warranty, exit loop
            // }
        }

        // Loop until a valid cost is provided
        while (true) {
            System.out.print("Enter Cost: ");
            if (scanner.hasNextDouble()) {
                cost = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                // Commented out InputValidation
                // if (InputValidation.isPositiveNumber(cost)) {
                if (cost > 0) {
                    break; // Valid cost, exit loop
                } else {
                    System.out.println("Cost must be a positive number!");
                }
            } else {
                System.out.println("Invalid input! Please enter a numeric value for cost.");
                scanner.next(); // Clear the invalid input
            }
        }

        // Notes can be empty, so no need to validate
        System.out.print("Enter Notes: ");
        notes = scanner.nextLine();

        // Create and add the part
        AutoPart part = new AutoPart(partID, partName, manufacturer, partNumber, condition, warranty, cost, notes);
        AutoPart.addPart(part);
    }

    public static void viewPartDetails(Scanner scanner) {
        System.out.print("Enter Part ID to View Details: ");
        String partID = scanner.nextLine();
        AutoPart part = AutoPart.getPartByID(partID);
        if (part != null) {
            System.out.println(part.toString());
        }
    }

    public static void updatePart(Scanner scanner) {
        System.out.print("Enter Part ID to Update: ");
        String partID = scanner.nextLine();

        AutoPart part = AutoPart.getPartByID(partID);
        if (part != null) {
            System.out.print("Enter New Part Name (or press Enter to skip): ");
            String partName = scanner.nextLine();
            if (!partName.isEmpty()) {
                part.setPartName(partName);
            }

            System.out.print("Enter New Manufacturer (or press Enter to skip): ");
            String manufacturer = scanner.nextLine();
            if (!manufacturer.isEmpty()) {
                part.setManufacturer(manufacturer);
            }

            System.out.print("Enter New Part Number (or press Enter to skip): ");
            String partNumber = scanner.nextLine();
            if (!partNumber.isEmpty()) {
                part.setPartNumber(partNumber);
            }

            System.out.print("Enter New Condition (or press Enter to skip): ");
            String condition = scanner.nextLine();
            if (!condition.isEmpty()) {
                part.setCondition(condition);
            }

            System.out.print("Enter New Warranty (or press Enter to skip): ");
            String warranty = scanner.nextLine();
            if (!warranty.isEmpty()) {
                part.setWarranty(warranty);
            }

            System.out.print("Enter New Cost (or enter 0 to skip): ");
            double cost = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            if (cost != 0) {
                part.setCost(cost);
            }

            System.out.print("Enter New Notes (or press Enter to skip): ");
            String notes = scanner.nextLine();
            if (!notes.isEmpty()) {
                part.setNotes(notes);
            }

            System.out.println("Auto part updated successfully: " + part.toString());
        } else {
            System.out.println("No part found with ID: " + partID);
        }
    }

    public static void deletePart(Scanner scanner) {
        System.out.print("Enter Part ID to Delete: ");
        String partID = scanner.nextLine();
        AutoPart.deletePart(partID);
    }

    public static void listAllParts() {
        AutoPart.listAllParts();
    }
}
