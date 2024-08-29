package util;

public class InputValidation {

    public static boolean isValidPartID(String partID) {
        return partID.matches("p-\\d+");
    }

    public static boolean isPositiveNumber(double number) {
        return number > 0;
    }

    public static boolean isNonEmptyString(String str) {
        return str != null && !str.trim().isEmpty();
    }

    // Add a method for validating warranty format, if needed
    public static boolean isValidWarranty(String warranty) {
        // Example: "1 year", "6 months"
        return warranty.matches("\\d+\\s(year|years|month|months)");
    }

    // Validate Service ID (should start with 's-')
    public static boolean isValidServiceID(String serviceID) {
        return serviceID.startsWith("s-");
    }

    // Validate Client ID (should start with 'c-')
    public static boolean isValidClientID(String clientID) {
        return clientID.startsWith("c-");
    }

    // Validate Mechanic ID (should start with 'm-')
    public static boolean isValidMechanicID(String mechanicID) {
        return mechanicID.startsWith("m-");
    }

    // Validate if the input is a valid double
    public static boolean isValidDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
