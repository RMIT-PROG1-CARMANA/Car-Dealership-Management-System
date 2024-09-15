package menu;

public class MenuStyle {

    // ANSI escape codes for styling
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String CYAN_BOLD = "\033[1;36m"; // Cyan
    public static final String YELLOW_BOLD = "\033[1;33m"; // Yellow
    public static final String GREEN_BOLD = "\033[1;32m";  // Green

    // Method for printing styled text
    public void printStyledText(String text, String style) {
        System.out.print(style + text + RESET);
    }

    // Method to print a menu header
    public void printMenuHeader(String menuName, int totalWidth) {
        // Ensure at least enough width to fit the menu name with two dashes
        int minimumWidth = menuName.length() + 4; // 2 spaces for padding and 2 dashes on either side
        if (totalWidth < minimumWidth) {
            totalWidth = minimumWidth;
        }

        // Calculate remaining space for dashes on both sides
        int remainingWidth = totalWidth - menuName.length();
        int halfWidth = remainingWidth / 2;

        // Print dashes, title, and additional dashes if needed
        String leftDashes = "-".repeat(halfWidth - 1); // Subtract 1 for space before the title
        String rightDashes = "-".repeat(halfWidth - 1 + (remainingWidth % 2)); // Adjust for odd lengths

        // Print the header with the menu name inline with dashes
        System.out.println(leftDashes + " " +  GREEN + menuName + RESET +  " " + rightDashes);
    }
    public void printLogoutMessage(){
        printStyledText("Logging out ...\n",RED);
    }
}
