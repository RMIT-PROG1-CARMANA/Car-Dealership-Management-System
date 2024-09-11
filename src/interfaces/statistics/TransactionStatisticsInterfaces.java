package interfaces.statistics;
import java.util.Date;
import java.util.List;

import sales.SalesTransaction;
import vehicle.Car;

public interface TransactionStatisticsInterfaces {

    List<SalesTransaction> getTransactionsByDay(Date date);

    List<SalesTransaction> getTransactionsByWeek(Date date);

    List<SalesTransaction> getTransactionsByMonth(Date date);

    void displayTransactions(List<SalesTransaction> transactions);
}
