package service;


import static service.ServiceMenu.listAllReplacedParts;
import static service.ServiceMenu.scanner;

public class Test {
    public static void main(String[] args) {
        // Example usage
        System.out.print("Enter the Service ID to list all replaced parts: ");
        String serviceID = scanner.nextLine();
        listAllReplacedParts(serviceID);
    }

}
