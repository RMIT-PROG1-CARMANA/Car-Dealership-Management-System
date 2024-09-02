package FileHandling;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class EditFile {

    public static void editLineInFile(String fileName, String oldLine, String newLine) throws IOException {
        Path path = Paths.get(fileName);
        List<String> lines = Files.readAllLines(path);
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            if (line.equals(oldLine)) {
                updatedLines.add(newLine);
            } else {
                updatedLines.add(line);
            }
        }

        Files.write(path, updatedLines, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static void appendToFile(String fileName, String text) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writer.write(text);
        writer.newLine();
        writer.close();
    }

    public static void removeLineFromFile(String fileName, String lineToRemove) throws IOException {
        Path path = Paths.get(fileName);
        List<String> lines = Files.readAllLines(path);
        List<String> updatedLines = new ArrayList<>();

        for (String line : lines) {
            if (!line.equals(lineToRemove)) {
                updatedLines.add(line);
            }
        }

        Files.write(path, updatedLines, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static List<String> readFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        return Files.readAllLines(path);
    }

    public static void writeUserDataToFile(String userID, String userData) throws IOException {
        String fileName = "DataBase/UserDatabase/u" + userID + ".txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false));
        writer.write(userData);
        writer.newLine();
        writer.close();
    }

    public static void saveToFile(String fileName, String data) throws IOException {
        Files.createDirectories(Paths.get(Paths.get(fileName).getParent().toString()));
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(data);
            writer.newLine();
        }
    }

    public static List<String> readUserDataFromFile(String userID) throws IOException {
        String fileName = "DataBase/UserDatabase/u" + userID + ".txt";
        return readFile(fileName);
    }
}
