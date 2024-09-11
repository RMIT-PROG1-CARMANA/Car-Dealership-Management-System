package interfaces.statistics;

import vehicle.Car;

import java.util.Date;
import java.util.List;

public interface CarStatisticsInterfaces {
    List<Car> getSoldCarsByDay(Date date);

    List<Car> getSoldCarsByWeek(Date date);

    List<Car> getSoldCarsByMonth(Date date);

    void displaySoldCars(List<Car> soldCars);
}
