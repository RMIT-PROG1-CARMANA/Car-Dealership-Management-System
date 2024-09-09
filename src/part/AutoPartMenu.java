package part;

import java.util.Scanner;

import menu.UserMenu.ManagerMenu;

public class AutoPartMenu {
    private static final String SERIALIZE_FILE_PATH = "src/DataBase/parts.ser"; // Path to the serialized file

    public AutoPartMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        // Load parts from serialized file at startup
        AutoPartFileHandler.deserializeParts();

        // Create an instance of ManagerMenu
        ManagerMenu managerMenu = new ManagerMenu();

        while (!exit) {
            // Call the ManagerMenu's method to display the main menu
            managerMenu.displayManagerMenu();

            // Check if we need to exit
            exit = true; // Assume we want to exit after displaying the menu
        }

        // Save parts to serialized file before exiting
        AutoPartFileHandler.serializeParts();
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
        double price;
        String notes;

        // Loop until a valid, unique part ID is provided
        while (true) {
            System.out.print("Enter Part ID: ");
            partID = scanner.nextLine();
            if (AutoPart.getPartByID(partID) != null) {
                System.out.println("Part ID already exists! Please enter a different Part ID.");
            } else {
                break; // Valid and unique part ID, exit loop
            }
        }

        // Loop until a valid part name is provided
        System.out.print("Enter Part Name: ");
        partName = scanner.nextLine();

        // Loop until a valid manufacturer is provided
        System.out.print("Enter Manufacturer: ");
        manufacturer = scanner.nextLine();

        // Loop until a valid part number is provided
        System.out.print("Enter Part Number: ");
        partNumber = scanner.nextLine();

        // Loop until a valid condition is provided
        System.out.print("Enter Condition (e.g., new, refurbished): ");
        condition = scanner.nextLine();

        // Loop until a valid warranty is provided
        System.out.print("Enter Warranty (e.g., 1 year, 6 months): ");
        warranty = scanner.nextLine();

        // Loop until a valid cost is provided
        while (true) {
            System.out.print("Enter Cost: ");
            if (scanner.hasNextDouble()) {
                price = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                if (price > 0) {
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
        AutoPart part = new AutoPart(partID, partName, manufacturer, partNumber, condition, warranty, price, notes);
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
            double price = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            if (price != 0) {
                part.setPrice(price);
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
