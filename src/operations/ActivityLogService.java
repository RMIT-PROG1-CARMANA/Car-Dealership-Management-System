package operations;

import FileHandling.ActivityLogDataHandler;
import logsystem.ActivityLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ActivityLogService {
    private static final ActivityLogDataHandler logDAO = new ActivityLogDataHandler();

    public static void logActivity(String logID, Date date,String username , String userID, String action) {
        List<ActivityLog> logs = logDAO.loadLogs();
        ActivityLog newLog = new ActivityLog(logID, date, username, userID, action);
        logs.add(newLog);
        logDAO.saveLogs(logs);
    }

    public List<ActivityLog> viewAllLogs() {
        return logDAO.loadLogs();
    }

    public List<ActivityLog> viewLogById(String logID) {
        return logDAO.loadLogs().stream()
                .filter(log -> log.getLogID().equals(logID))
                .collect(Collectors.toList());
    }

    public List<ActivityLog> viewLogsByUsername(String username) {
        return logDAO.loadLogs().stream()
                .filter(log -> log.getUsername().equals(username))
                .collect(Collectors.toList());
    }

    public List<ActivityLog> viewLogsByUserID(String userID) {
        return logDAO.loadLogs().stream()
                .filter(log -> log.getUserID().equals(userID))
                .collect(Collectors.toList());
    }

    public List<ActivityLog> viewLogsByDate(Date date) {
        if (date == null) {
            System.err.println("Date cannot be null");
            return new ArrayList<>();
        }
        return logDAO.loadLogs().stream()
                .filter(log -> {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    return sdf.format(log.getTimestamp()).equals(sdf.format(date));
                })
                .collect(Collectors.toList());
    }

    public void displayLogs(List<ActivityLog> logs) {
        if (logs.isEmpty()) {
            System.out.println("No logs found.");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println(String.format("%-20s %-20s %-15s %-10s %-50s", "LogID", "Timestamp", "Username", "UserID", "Action"));
        System.out.println("---------------------------------------------------------------------------------------------");

        for (ActivityLog log : logs) {
            System.out.println(String.format("%-20s %-20s %-15s %-10s %-50s",
                    log.getLogID(),
                    sdf.format(log.getTimestamp()),
                    log.getUsername(),
                    log.getUserID(),
                    log.getAction()
            ));
        }
    }
}
