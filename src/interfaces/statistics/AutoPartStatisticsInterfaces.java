package interfaces.statistics;

import part.AutoPart;

import java.util.Date;
import java.util.List;

public interface AutoPartStatisticsInterfaces {
    List<AutoPart> getSoldPartsByDay(Date date);

    List<AutoPart> getSoldPartsByWeek(Date date);

    List<AutoPart> getSoldPartsByMonth(Date date);

    void displaySoldParts(List<AutoPart> soldParts);
}
