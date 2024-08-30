package menu.UserMenu;

import menu.Menu;
import utils.InputValidation;

public abstract class EmployeeBaseMenu extends Menu {
    // Common functionality for all employees
    public abstract void displayEmployeeMenu();

    // this will calculate and contain for day,week and month
    protected void calculateRevenue() {
        //calculating revenue logic
        System.out.println("Calculating revenue...");
    }

    // this will list cars were brought in for service during day,week and month
    protected void displayEmployeeCarsServiced() {
        // listing cars/services logic
        System.out.println("Listing cars...");
    }

    // this will list total number of services performed during day,week and month
    protected void displayEmployeeServicesPerformed() {
        // listing cars/services logic
        System.out.println("Listing services...");
    }

}
