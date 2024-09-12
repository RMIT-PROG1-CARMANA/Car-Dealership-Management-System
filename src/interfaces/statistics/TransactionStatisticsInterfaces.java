package interfaces.statistics;
import java.util.Date;
import java.util.List;

import sales.SalesTransaction;
import vehicle.Car;

public interface TransactionStatisticsInterfaces {

    void getTransactionsByDay();

    void getTransactionsByWeek();

    void getTransactionsByMonth();

}
