package operations.statistics;

import interfaces.statistics.ServiceStatisticsInterfaces;
import service.Service;

import java.util.List;

public class ServiceStatistics implements ServiceStatisticsInterfaces {
    private List<Service> services;
    private String mechanicID;
    // Calculate the revenue of the services done of a mechanic
    @Override
    public void calculateRevenueFromServices() {
        double totalRevenue = 0.0;

        for (Service service : services) {
            // Check if the service's mechanic matches the given ID
            if (service.getMechanicID().equals(mechanicID)) {
                // Sum up the service cost
                totalRevenue += service.getServiceCost();
            }
        }
        // Print the total revenue
        System.out.println("Total revenue from services done by mechanic ID " + mechanicID + ": " + totalRevenue);
    }

}
