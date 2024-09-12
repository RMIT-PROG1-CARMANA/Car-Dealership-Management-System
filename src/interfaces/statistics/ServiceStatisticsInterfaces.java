package interfaces.statistics;

import service.Service;

import java.util.Date;
import java.util.List;

public interface ServiceStatisticsInterfaces {


    // Calculate the revenue of the services done of a mechanic
    void calculateRevenueFromServices();

    // Get services by day based on user input
    void getServicesByDay();

    // Get services by week based on user input
    void getServicesByWeek();

    // Get services by month based on user input
    void getServicesByMonth();


}
