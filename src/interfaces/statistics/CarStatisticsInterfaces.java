package interfaces.statistics;

import vehicle.Car;

import java.util.Date;
import java.util.List;

public interface CarStatisticsInterfaces {

    // Method to calculate number of cars sold in a specific month
    void calculateCarsSoldInMonth();

    //Calculate the revenue of the cars sold of a salesperson
    void calculateRevenueFromCars();

    void getSoldCarsByDay();

    void getSoldCarsByWeek();

    void getSoldCarsByMonth();
}
