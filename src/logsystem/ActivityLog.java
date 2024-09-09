package logsystem;

import java.io.Serializable;
import java.util.Date;
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

    // Static method to generate a new log ID
    public static String generateLogID() {
        return ID_PREFIX + String.format("%04d", idCounter.incrementAndGet());
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
