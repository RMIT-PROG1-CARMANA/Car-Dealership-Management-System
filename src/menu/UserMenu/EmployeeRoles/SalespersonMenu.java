package menu.UserMenu.EmployeeRoles;

import menu.UserMenu.EmployeeBaseMenu;
import user.Authenticator;
import utils.Divider;

public class SalespersonMenu extends EmployeeBaseMenu {
    @Override
    public void displayEmployeeMenu() {
        int choice;
//        uiUtils.clearScreen();
        System.out.println("Welcome Sales!");

        displayMenuHeader(" ", 53);
        displayOption("0. Record Sales Transaction");
        displayOption("1. ");
        displayOption("2. Calculate Revenue (Day/Week/Month)");
        displayOption("3. List Number of Services (Day/Week/Month)");
        displayOption("4. ");
        displayOption("5. ");
        displayOption("6. ");
        Divider.printDivider();

        System.out.print("Enter Selection: ");

        choice = getValidatedChoice(0, 6);

        switch (choice) {
            case 0:

                break;

            case 1:

                break;
            case 2:
                calculateRevenue();
                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:
                System.exit(0);// terminates the program
                Authenticator.UserLogOut();
                break;
            default:
                System.err.println("\n**Please, Enter a Valid Input**");
                System.out.println();
        }
    }

}
