package menu.UserMenu.EmployeeRoles;

import logout.UserLogOut;
import menu.UserMenu.EmployeeBaseMenu;
import utils.Divider;

public class MechanicMenu extends EmployeeBaseMenu {
    @Override
    public void displayEmployeeMenu() {
        int choice;
//        uiUtils.clearScreen();
            System.out.println("Welcome Mechanic!");
            System.out.println();

            displayMenuHeader("MECHANIC MENU ", 53);
            displayOption("0. Add Service");
            displayOption("1. Update Service");
            displayOption("2. Calculate Revenue (Day/Week/Month)");
            displayOption("3. List Number of Services (Day/Week/Month)");
            displayOption("4. Update Service");
            displayOption("5. Go Back Profile");
            displayOption("6. Logout");
            Divider.printDivider();

            System.out.print("Enter Selection: ");
            System.out.println();

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
                    new UserLogOut();
                    break;
                default:
                    System.err.println("\n**Please, Enter a Valid Input**");
                    System.out.println();
            }

    }

}