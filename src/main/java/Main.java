import auth.Auth;
import food_menu.FoodMenu;
import order.Order;
import reporting.Reporting;
import staff.Staff;

import static pub.VScan.*;

public class Main {
    public static void main(String[] args) {

        int choice;

        System.out.println();

        // Authenticate

        while (true) {
            System.out.println("\n" +
                    "______  ________________  __     _______________________\n" +
                    "___/ / / /_  __ \\__  /_ \\/ /     ___  __ \\_  __ \\_  ___/\n" +
                    "__/ /_/ /_  / / /_  / __  /________  /_/ /  / / /____ \\ \n" +
                    "_/ __  / / /_/ /_  /___  /_/_____/  ____// /_/ /____/ / \n" +
                    "/_/ /_/  \\____/ /_____/_/        /_/     \\____/ /____/  \n" +
                    "                                                        \n");
            System.out.println("1. Authentication\n" +
                    "2. Food Menu\n" +
                    "3. Order\n" +
                    "4. Staff\n" +
                    "5. Reporting\n" +
                    "Enter any other number to exit.\n\n" +

                    "Please select your module: ");

            choice = getInt();

            switch (choice) {
                case 1:
                    // put auth classes under here
                    Auth.auth();
                    break;
                case 2:
                    // put food_menu classes under here
                    FoodMenu.foodMenu();
                    break;
                case 3:
                    // put order classes under here
                    Order.order();
                    break;
                case 4:
                    // put staff classes under here
                    Staff.staff();
                    break;
                case 5:
                    // put reporting classes under here
                    Reporting.reporting();
                    break;
                default:
                    return;
            }
        }
    }
}
