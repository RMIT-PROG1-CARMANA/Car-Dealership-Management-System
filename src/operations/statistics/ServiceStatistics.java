package operations.statistics;

import java.util.*;
import filehandling.ServiceFileHandler;
import interfaces.statistics.ServiceStatisticsInterfaces;
import part.AutoPart;
import service.Service;
import utils.DateRange;
import utils.InputValidation;

import java.text.SimpleDateFormat;
import java.util.List;

public class ServiceStatistics implements ServiceStatisticsInterfaces {
    DateRange dateRange = new DateRange();
    private List<Service> services;
    private String mechanicID;
    private final List<Service> serviceList;

    // Constructor to initialize services list from file
    public ServiceStatistics() {
        this.serviceList = ServiceFileHandler.loadServices();  // Load from the file handler
    }

    // Calculate the revenue of the services done of a mechanic
    @Override
    public void calculateRevenueFromServices() {
        double totalRevenue = 0.0;

        for (Service service : serviceList) { // Use serviceList instead of services
            // Check if the service's mechanic matches the given ID

                // Sum up the service cost
                totalRevenue += service.getServiceCost();

        }
        // Print the total revenue
        System.out.println("Total revenue from services done by mechanic " + totalRevenue);
    }


    // Get services by day based on user input
    @Override
    public void getServicesByDay() {
        Date userInputDate = InputValidation.validateDate("Enter the date(dd/mm/yy): ");
        Date[] range = dateRange.getDayRange(userInputDate);
        List<Service> dayServices = filterServicesByDateRange(range[0], range[1]);
        displayServices(dayServices);
    }

    // Get services by week based on user input
    @Override
    public void getServicesByWeek() {
        Date userInputDate = InputValidation.validateDate("Enter the date(dd/mm/yy): ");
        Date[] range = dateRange.getDayRange(userInputDate);
        List<Service> weekServices = filterServicesByDateRange(range[0], range[1]);
        displayServices(weekServices);
    }

    // Get services by month based on user input
    @Override
    public void getServicesByMonth() {
        Date userInputDate = InputValidation.validateDate("Enter the date(dd/mm/yy): ");
        Date[] range = dateRange.getDayRange(userInputDate);
        List<Service> monthServices = filterServicesByDateRange(range[0], range[1]);
        displayServices(monthServices);
    }

    // Display the list of services with detailed information
    public void displayServices(List<Service> services) {
        if (services.isEmpty()) {
            System.out.println("No services found.");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Print the header for services
        System.out.println(String.format("%-15s %-15s %-15s %-15s %-20s %-10s %-50s",
                "ServiceID", "Service Date", "ClientID", "MechanicID", "Service Type", "Cost", "Notes"));
        System.out.println("-----------------------------------------------------------------------------------------------");

        // Print each service
        for (Service service : services) {
            System.out.println(String.format("%-15s %-15s %-15s %-15s %-20s $%-9.2f %-50s",
                    service.getServiceID(),
                    sdf.format(service.getServiceDate()),
                    service.getClientID(),
                    service.getMechanicID(),
                    service.getServiceType(),
                    service.getServiceCost(),
                    service.getNotes() != null ? service.getNotes() : ""));

            // Print the details of replaced parts
            if (!service.getReplacedParts().isEmpty()) {
                System.out.println("    Replaced Parts:");
                for (AutoPart part : service.getReplacedParts()) {
                    System.out.println(String.format("        PartID: %-10s PartName: %-20s Price: $%-8.2f",
                            part.getPartID(),
                            part.getPartName(),
                            part.getPrice()));
                }
            } else {
                System.out.println("    No parts replaced.");
            }

            System.out.println(); // Add an empty line for better readability between services
        }
    }

    // Filter the services based on the start and end date
    private List<Service> filterServicesByDateRange(Date start, Date end) {
        List<Service> filteredServices = new ArrayList<>();
        for (Service service : serviceList) {
            Date serviceDate = service.getServiceDate();
            if (serviceDate != null && (serviceDate.after(start) || serviceDate.equals(start)) && (serviceDate.before(end) || serviceDate.equals(end))) {
                filteredServices.add(service);
            }
        }
        return filteredServices;
    }
}


