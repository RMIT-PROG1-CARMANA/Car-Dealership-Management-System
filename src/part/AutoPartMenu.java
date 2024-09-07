package part;

import util.InputValidation;

import java.util.Scanner;
import java.util.InputMismatchException;

public class AutoPartMenu {

    public static void displayMenu() {
        System.out.println("\n--- AutoPart Management System ---");
        System.out.println("1. Add a New Part");
        System.out.println("2. View Part Details");
        System.out.println("3. Update an Existing Part");
        System.out.println("4. Delete a Part");
        System.out.println("5. List All Parts");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        // Load parts from file at startup
        AutoPartFileHandler.loadPartsFromFile();

        while (!exit) {
            displayMenu();
            int choice = 0;

            // Loop until valid integer input is provided
            while (true) {
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (choice >= 1 && choice <= 6) {
                        break; // Valid choice, exit loop
                    } else {
                        System.out.println("Invalid choice! Please enter a number between 1 and 6.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a number between 1 and 6.");
                    scanner.nextLine(); // Clear the invalid input
                }
            }

            switch (choice) {
                case 1:
                    addPart(scanner);
                    break;
                case 2:
                    viewPartDetails(scanner);
                    break;
                case 3:
                    updatePart(scanner);
                    break;
                case 4:
                    deletePart(scanner);
                    break;
                case 5:
                    listAllParts();
                    break;
                case 6:
                    // Save parts to file before exiting
                    AutoPartFileHandler.savePartsToFile();
                    exit = true;
                    System.out.println("Exiting the AutoPart Management System. Goodbye!");
                    break;
            }
        }

        scanner.close();
    }


    private static void addPart(Scanner scanner) {
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
            if (!InputValidation.isValidPartID(partID)) {
                System.out.println("Invalid Part ID! It must start with 'p-' followed by digits.");
            } else if (AutoPart.getPartByID(partID) != null) {
                System.out.println("Part ID already exists! Please enter a different Part ID.");
            } else {
                break; // Valid and unique part ID, exit loop
            }
        }

        // Loop until a valid part name is provided
        while (true) {
            System.out.print("Enter Part Name: ");
            partName = scanner.nextLine();
            if (!InputValidation.isNonEmptyString(partName)) {
                System.out.println("Part Name cannot be empty!");
            } else {
                break; // Valid part name, exit loop
            }
        }

        // Loop until a valid manufacturer is provided
        while (true) {
            System.out.print("Enter Manufacturer: ");
            manufacturer = scanner.nextLine();
            if (!InputValidation.isNonEmptyString(manufacturer)) {
                System.out.println("Manufacturer cannot be empty!");
            } else {
                break; // Valid manufacturer, exit loop
            }
        }

        // Loop until a valid part number is provided
        while (true) {
            System.out.print("Enter Part Number: ");
            partNumber = scanner.nextLine();
            if (!InputValidation.isNonEmptyString(partNumber)) {
                System.out.println("Part Number cannot be empty!");
            } else {
                break; // Valid part number, exit loop
            }
        }

        // Loop until a valid condition is provided
        while (true) {
            System.out.print("Enter Condition (e.g., new, used, refurbished): ");
            condition = scanner.nextLine();
            if (!InputValidation.isValidCondition(condition)) {
                System.out.println("Invalid Condition! It must be one of the following: 'new', 'used', 'refurbished'.");
            } else {
                break; // Valid condition, exit loop
            }
        }

        // Loop until a valid warranty is provided
        while (true) {
            System.out.print("Enter Warranty (e.g., 1 year, 6 months): ");
            warranty = scanner.nextLine();
            if (!InputValidation.isValidWarranty(warranty)) {
                System.out.println("Invalid Warranty format! Use format like '1 year' or '6 months'.");
            } else {
                break; // Valid warranty, exit loop
            }
        }

        // Loop until a valid cost is provided
        while (true) {
            System.out.print("Enter Cost: ");
            if (scanner.hasNextDouble()) {
                cost = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                if (InputValidation.isPositiveNumber(cost)) {
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


    private static void viewPartDetails(Scanner scanner) {
        System.out.print("Enter Part ID to View Details: ");
        String partID = scanner.nextLine();
        AutoPart part = AutoPart.getPartByID(partID);
        if (part != null) {
            System.out.println(part.toString());
        }
    }

    private static void updatePart(Scanner scanner) {
        System.out.print("Enter Part ID to Update: ");
        String partID = scanner.nextLine();

        AutoPart part = AutoPart.getPartByID(partID);
        if (part != null) {
            // Update Part Name
            System.out.print("Enter New Part Name (or press Enter to skip): ");
            String partName = scanner.nextLine();
            if (!partName.isEmpty() && !InputValidation.isNonEmptyString(partName)) {
                System.out.println("Invalid Part Name! It cannot be empty.");
            } else if (!partName.isEmpty()) {
                part.setPartName(partName);
            }

            // Update Manufacturer
            System.out.print("Enter New Manufacturer (or press Enter to skip): ");
            String manufacturer = scanner.nextLine();
            if (!manufacturer.isEmpty() && !InputValidation.isNonEmptyString(manufacturer)) {
                System.out.println("Invalid Manufacturer! It cannot be empty.");
            } else if (!manufacturer.isEmpty()) {
                part.setManufacturer(manufacturer);
            }

            // Update Part Number
            System.out.print("Enter New Part Number (or press Enter to skip): ");
            String partNumber = scanner.nextLine();
            if (!partNumber.isEmpty() && !InputValidation.isNonEmptyString(partNumber)) {
                System.out.println("Invalid Part Number! It cannot be empty.");
            } else if (!partNumber.isEmpty()) {
                part.setPartNumber(partNumber);
            }

            // Update Condition
            System.out.print("Enter New Condition (or press Enter to skip, e.g., new, used, refurbished): ");
            String condition = scanner.nextLine();
            if (!condition.isEmpty() && !InputValidation.isValidCondition(condition)) {
                System.out.println("Invalid Condition! It must be one of the following: 'new', 'used', 'refurbished'.");
            } else if (!condition.isEmpty()) {
                part.setCondition(condition);
            }

            // Update Warranty
            System.out.print("Enter New Warranty (or press Enter to skip): ");
            String warranty = scanner.nextLine();
            if (!warranty.isEmpty() && !InputValidation.isValidWarranty(warranty)) {
                System.out.println("Invalid Warranty format! Use format like '1 year' or '6 months'.");
            } else if (!warranty.isEmpty()) {
                part.setWarranty(warranty);
            }

            // Update Cost
            System.out.print("Enter New Cost (or enter 0 to skip): ");
            double cost = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            if (cost != 0 && !InputValidation.isPositiveNumber(cost)) {
                System.out.println("Invalid Cost! It must be a positive number.");
            } else if (cost != 0) {
                part.setCost(cost);
            }

            // Update Notes
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


    private static void deletePart(Scanner scanner) {
        System.out.print("Enter Part ID to Delete: ");
        String partID = scanner.nextLine();
        AutoPart.deletePart(partID);
    }

    private static void listAllParts() {
        AutoPart.listAllParts();
    }
}
