package FileHandling;

import java.io.*;

public class FileHandler {

    // Method to write a line to a file
    public static void writeToFile(String filePath, String line, boolean append) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append))) {
            writer.write(line);
            writer.newLine();
        }
    }

    // Method to read all lines from a file
    public static BufferedReader readFromFile(String filePath) throws IOException {
        return new BufferedReader(new FileReader(filePath));
    }

    // Method to rename a file
    public static boolean renameFile(File oldFile, File newFile) {
        return oldFile.renameTo(newFile);
    }

    // Method to delete a file
    public static boolean deleteFile(File file) {
        return file.delete();
    }
}