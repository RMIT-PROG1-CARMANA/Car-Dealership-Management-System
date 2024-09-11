package operations.statistics;

import part.AutoPart;
import service.Service;
import FileHandling.ServiceFileHandler;

import java.text.SimpleDateFormat;
import java.util.*;

public class ServiceStatistics implements  interfaces.statistics.ServiceStatisticsInterfaces {

    private final List<Service> serviceList;

    // Constructor to initialize services list from file
    public ServiceStatistics() {
        this.serviceList = ServiceFileHandler.loadServices();  // Load from the file handler
    }

    @Override
    public List<Service> getServicesByDay(Date day) {
        Date[] range = getDayRange(day);
        return filterServicesByDateRange(range[0], range[1]);
    }

    @Override
    public List<Service> getServicesByWeek(Date weekStart) {
        Date[] range = getWeekRange(weekStart);
        return filterServicesByDateRange(range[0], range[1]);
    }

    @Override
    public List<Service> getServicesByMonth(Date monthStart) {
        Date[] range = getMonthRange(monthStart);
        return filterServicesByDateRange(range[0], range[1]);
    }

    @Override
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
            if (serviceDate != null && serviceDate.after(start) && serviceDate.before(end)) {
                filteredServices.add(service);
            }
        }
        return filteredServices;
    }

    // Get the start and end of the day for filtering
    private Date[] getDayRange(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date start = cal.getTime();

        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date end = cal.getTime();

        return new Date[]{start, end};
    }

    // Get the start and end of the week for filtering
    private Date[] getWeekRange(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date start = cal.getTime();

        cal.add(Calendar.WEEK_OF_YEAR, 1);
        Date end = cal.getTime();

        return new Date[]{start, end};
    }

    // Get the start and end of the month for filtering
    private Date[] getMonthRange(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date start = cal.getTime();

        cal.add(Calendar.MONTH, 1);
        Date end = cal.getTime();

        return new Date[]{start, end};
    }
}
