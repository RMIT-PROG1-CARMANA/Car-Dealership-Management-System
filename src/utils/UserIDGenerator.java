package utils;


public class UserIDGenerator {
    private static int counter = 1;

    public static String generateUserID() {
        return "u-" + counter++;
    }
}
