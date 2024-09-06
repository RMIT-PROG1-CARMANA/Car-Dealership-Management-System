package service;

import part.AutoPart;
// import utils.InputValidation; // Commented out because we are not using it now

import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;

public class ServiceMenu {
    private static Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Service Management System ---");
            System.out.println("1. Add Service");
            System.out.println("2. Get Service by ID");
            System.out.println("3. Update Service");
            System.out.println("4. Delete Service");
            System.out.println("5. Add Part to Service");
            System.out.println("6. Remove Part from Service");
            System.out.println("7. List All Services");
            System.out.println("8. Exit");
            System.out.print("Select an option: ");

            int choice = getValidInteger("Choose a valid option (1-8): ");
            switch (choice) {
                case 1:
                    addService();
                    break;
                case 2:
                    getServiceByID();
                    break;
                case 3:
                    updateService();
                    break;
                case 4:
                    deleteService();
                    break;
                case 5:
//                    addPartToService();
                    break;
                case 6:
//                    removePartFromService();
                    break;
                case 7:
//                    listAllServices();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static int getValidInteger(String prompt) {
        int value = -1;
        while (true) {
            try {
                System.out.print(prompt);
                value = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Clear the buffer
            }
        }
    }

    private static double getValidDouble(String prompt) {
        double value = -1;
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                // if (InputValidation.isValidDouble(input)) {
                //     value = Double.parseDouble(input);
                //     return value;
                // } else {
                //     System.out.println("Invalid input. Please enter a valid number.");
                // }
                value = Double.parseDouble(input); // Direct parsing without validation
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private static Date getValidDate(String prompt) {
        Date date = null;
        while (date == null) {
            try {
                System.out.print(prompt);
                date = DATE_FORMAT.parse(scanner.nextLine());
                return date;
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter a date in the format dd/MM/yyyy.");
            }
        }
        return date;
    }

    private static void addService() {
        System.out.print("Enter Service ID: ");
        String serviceID = scanner.nextLine();
        // while (!InputValidation.isValidServiceID(serviceID)) {
        //     System.out.println("Invalid Service ID. It should start with 's-'. Please try again.");
        //     serviceID = scanner.nextLine();
        // }

        Date serviceDate = getValidDate("Enter Service Date (dd/MM/yyyy): ");

        System.out.print("Enter Client ID: ");
        String clientID = scanner.nextLine();
        // while (!InputValidation.isValidClientID(clientID)) {
        //     System.out.println("Invalid Client ID. It should start with 'c-'. Please try again.");
        //     clientID = scanner.nextLine();
        // }

        System.out.print("Enter Mechanic ID: ");
        String mechanicID = scanner.nextLine();
        // while (!InputValidation.isValidMechanicID(mechanicID)) {
        //     System.out.println("Invalid Mechanic ID. It should start with 'm-'. Please try again.");
        //     mechanicID = scanner.nextLine();
        // }

        System.out.print("Enter Service Type: ");
        String serviceType = scanner.nextLine();

        double serviceCost = getValidDouble("Enter Service Cost: ");

        System.out.print("Enter Notes: ");
        String notes = scanner.nextLine();

        Service newService = new Service(serviceID, serviceDate, clientID, mechanicID, serviceType, serviceCost, notes);
        Service.addService(newService);
    }

    private static void getServiceByID() {
        System.out.print("Enter Service ID: ");
        String serviceID = scanner.nextLine();
        // while (!InputValidation.isValidServiceID(serviceID)) {
        //     System.out.println("Invalid Service ID. It should start with 's-'. Please try again.");
        //     serviceID = scanner.nextLine();
        // }

        Service service = Service.getServiceByID(serviceID);
        if (service != null) {
            System.out.println(service.toString());
        } else {
            System.out.println("Service not found with ID: " + serviceID);
        }
    }

    private static void updateService() {
        System.out.print("Enter Service ID to Update: ");
        String serviceID = scanner.nextLine();
        // while (!InputValidation.isValidServiceID(serviceID)) {
        //     System.out.println("Invalid Service ID. It should start with 's-'. Please try again.");
        //     serviceID = scanner.nextLine();
        // }

        Service service = Service.getServiceByID(serviceID);
        if (service == null) {
            System.out.println("Service not found with ID: " + serviceID);
            return;
        }

        System.out.print("Enter New Service Date (dd/MM/yyyy) or press Enter to keep current: ");
        String dateInput = scanner.nextLine();
        Date serviceDate = service.getServiceDate();
        if (!dateInput.trim().isEmpty()) {
            try {
                serviceDate = DATE_FORMAT.parse(dateInput);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Keeping current date.");
            }
        }

        System.out.print("Enter New Client ID or press Enter to keep current: ");
        String clientID = scanner.nextLine();
        if (clientID.trim().isEmpty()) {
            clientID = service.getClientID();
        }
        // else if (!InputValidation.isValidClientID(clientID)) {
        //     System.out.println("Invalid Client ID. Keeping current ID.");
        //     clientID = service.getClientID();
        // }

        System.out.print("Enter New Mechanic ID or press Enter to keep current: ");
        String mechanicID = scanner.nextLine();
        if (mechanicID.trim().isEmpty()) {
            mechanicID = service.getMechanicID();
        }
        // else if (!InputValidation.isValidMechanicID(mechanicID)) {
        //     System.out.println("Invalid Mechanic ID. Keeping current ID.");
        //     mechanicID = service.getMechanicID();
        // }

        System.out.print("Enter New Service Type or press Enter to keep current: ");
        String serviceType = scanner.nextLine();
        if (serviceType.trim().isEmpty()) {
            serviceType = service.getServiceType();
        }

        System.out.print("Enter New Service Cost or press Enter to keep current: ");
        String serviceCostInput = scanner.nextLine();
        double serviceCost = service.getServiceCost();
        if (!serviceCostInput.trim().isEmpty()) {
            // if (InputValidation.isValidDouble(serviceCostInput)) {
            //     serviceCost = Double.parseDouble(serviceCostInput);
            // } else {
            //     System.out.println("Invalid cost input. Keeping current cost.");
            // }
            try {
                serviceCost = Double.parseDouble(serviceCostInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid cost input. Keeping current cost.");
            }
        }

        System.out.print("Enter New Notes or press Enter to keep current: ");
        String notes = scanner.nextLine();
        if (notes.trim().isEmpty()) {
            notes = service.getNotes();
        }

        service.updateService(serviceDate, clientID, mechanicID, serviceType, serviceCost, notes);
    }

    private static void deleteService() {
        System.out.print("Enter Service ID to Delete: ");
        String serviceID = scanner.nextLine();
        // while (!InputValidation.isValidServiceID(serviceID)) {
        //     System.out.println("Invalid Service ID. It should start with 's-'. Please try again.");
        //     serviceID = scanner.nextLine();
        // }
        Service.deleteService(serviceID);
    }
/*

//    private static void addPartToService() {
//        System.out.print("Enter Service ID: ");
//        String serviceID = scanner.nextLine();
//        // while (!InputValidation.isValidServiceID(serviceID)) {
//        //     System.out.println("Invalid Service ID. It should start with 's-'. Please try again.");
//        //     serviceID = scanner.nextLine();
//        // }
//
//        Service service = Service.getServiceByID(serviceID);
//        if (service != null) {
//            System.out.print("Enter Part ID: ");
//            String partID = scanner.nextLine();
//
//            System.out.print("Enter Part Name: ");
//            String partName = scanner.nextLine();
//
//            System.out.print("Enter Part Price: ");
//            double partPrice = getValidDouble("Enter Part Price: ");
//
//            AutoPart part = new AutoPart(partID, partName, partPrice);
//            service.addPart(part);
//        } else {
//            System.out.println("Service not found with ID: " + serviceID);
//        }
//    }
//
//    private static void removePartFromService() {
//        System.out.print("Enter Service ID: ");
//        String serviceID = scanner.nextLine();
//        // while (!InputValidation.isValidServiceID(serviceID)) {
//        //     System.out.println("Invalid Service ID. It should start with 's-'. Please try again.");
//        //     serviceID = scanner.nextLine();
//        // }
//
//        Service service = Service.getServiceByID(serviceID);
//        if (service != null) {
//            System.out.print("Enter Part ID to Remove: ");
//            String partID = scanner.nextLine();
//            service.removePart(partID);
//        } else {
//            System.out.println("Service not found with ID: " + serviceID);
//        }
//    }
//
//    private static void listAllServices() {
//        for (Service service : Service.getAllServices()) {
//            System.out.println(service.toString());
//        }
//    }

 */
}
