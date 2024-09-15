package filehandling;

import logsystem.ActivityLog;

import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActivityLogDataHandler {
    private static final String FILE_PATH = "src/database/activitylog.txt";

    // Read all activity logs from the file
    // Load logs from the file
    public List<ActivityLog> loadLogs() {
        List<ActivityLog> logs = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            // Read all logs from the file as an array
            ActivityLog[] logsArray = (ActivityLog[]) ois.readObject();
            logs.addAll(Arrays.asList(logsArray));
        } catch (EOFException e) {
            // End of file reached, means no more logs to read
            System.out.println("No logs found.");
        } catch (FileNotFoundException e) {
            // File not found, no logs to read
            System.err.println("Log file not found. No logs to load.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return logs;
    }

    // Save logs to the file
    public void saveLogs(List<ActivityLog> logs) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            // Write all logs to the file as an array
            oos.writeObject(logs.toArray(new ActivityLog[0]));
        } catch (IOException e) {
            System.err.println("Failed to write logs to file: " + FILE_PATH);
            e.printStackTrace(); // Consider logging this in production
        }
    }
    public boolean doesLogIDExist(String logID) {
        List<ActivityLog> logs = loadLogs();
        return logs.stream().anyMatch(log -> log.getLogID().equals(logID));
    }
}
