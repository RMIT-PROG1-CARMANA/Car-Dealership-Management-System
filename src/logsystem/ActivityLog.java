package logsystem;

import filehandling.ActivityLogDataHandler;
import java.io.Serializable;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ActivityLog implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final AtomicInteger idCounter = new AtomicInteger(0);
    private static final String ID_PREFIX = "log-";
    private String logID;
    private Date timestamp;
    private String username;
    private String userID;
    private String action;

    // Constructor
    public ActivityLog(String logID, Date timestamp, String username, String userID, String action) {
        this.logID = logID;
        this.timestamp = timestamp;
        this.username = username;
        this.userID = userID;
        this.action = action;
    }

    // Getters
    public String getLogID() { return logID; }
    public Date getTimestamp() { return timestamp; }
    public String getUsername() { return username; }
    public String getUserID() { return userID; }
    public String getAction() { return action; }

    // Setters
    public void setLogID(String logID) { this.logID = logID; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
    public void setUsername(String username) { this.username = username; }
    public void setUserID(String userID) { this.userID = userID; }
    public void setAction(String action) { this.action = action; }

    private static final ActivityLogDataHandler logDAO = new ActivityLogDataHandler();

    // Static method to generate a new log ID
    public static String generateLogID() {
        String logID;
        do {
            logID = ID_PREFIX + generateRandomNumber(4);
        } while (logDAO.doesLogIDExist(logID));
        return logID;
    }

    // Generate a random 5-digit number
    private static String generateRandomNumber(int length) {
        Random random = new Random();
        int number = random.nextInt((int) Math.pow(10, length));
        return String.format("%0" + length + "d", number);
    }


    @Override
    public String toString() {
        return "LogID: " + logID +
                ", Timestamp: " + timestamp +
                ", Username: " + username +
                ", UserID: " + userID +
                ", Action: " + action;
    }
}
