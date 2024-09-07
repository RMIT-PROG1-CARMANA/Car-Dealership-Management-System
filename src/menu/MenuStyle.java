package menu;

public class MenuStyle {

    // ANSI escape codes for styling
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String CYAN_BOLD = "\033[1;36m"; // Cyan
    public static final String YELLOW_BOLD = "\033[1;33m"; // Yellow
    public static final String GREEN_BOLD = "\033[1;32m";  // Green

    // Method for printing styled text
    public void printStyledText(String text, String style) {
        System.out.print(style + text + RESET);
    }

    // Method to create a horizontal separator
    public void printSeparator(int width, char separatorChar) {
        System.out.println(String.valueOf(separatorChar).repeat(width));
    }

    // Method to print centered text with padding
    public void printCentered(String text, int width) {
        int paddingSize = (width - text.length()) / 2;
        String padding = " ".repeat(Math.max(0, paddingSize));
        System.out.println(padding + text + padding);
    }

    // Method to print a menu header
    public void printMenuHeader(String menuName, int totalWidth) {
        int remainingWidth = totalWidth - menuName.length() - 2; // Subtract 2 for spaces around the name
        int halfWidth = remainingWidth / 2;

        // Print left border
        printSeparator(halfWidth, '-');

        // Print centered title
        printStyledText(" " + menuName + " ", BOLD + GREEN);

        // Print right border
        printSeparator(halfWidth + menuName.length() + 2, '-');
    }

    // Method to print a logout message with style
    public void printLogoutMessage() {
        printStyledText("Logging out...\n", RED);
        System.out.println("Thank you for using our system!");
    }
}
