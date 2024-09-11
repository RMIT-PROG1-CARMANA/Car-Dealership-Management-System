package interfaces.statistics;

import service.Service;

import java.util.Date;
import java.util.List;

public interface ServiceStatisticsInterfaces {
    List<Service> getServicesByDay(Date day);

    List<Service> getServicesByWeek(Date weekStart);

    List<Service> getServicesByMonth(Date monthStart);

    void displayServices(List<Service> services);
}
