import auth.Auth;
import product_menu.ProductMenu;
import order.Order;
import reporting.Reporting;
import staff.Staff;

import static pub.VScan.*;
import static pub.FormatPrint.*;

public class Main {

    public static void main(String[] args) {
        new Order(); //Initialization
        int choice;

        // Authenticate

        while (true) {
            showLogo();
            if (Auth.getSession() == null) {
                Auth.auth();
            }

            printHeader("MAIN MENU");
            System.out.print(
                    "1. Product Menu\n" +
                    "2. Order\n" +
                    "3. Staff\n" +
                    "4. Reporting\n" +
                    "Enter any other number to exit/logout.\n\n" +

                    "Please select your module: ");

            choice = getInt();

            switch (choice) {
                case 1:
                    // put food_menu classes under here
                    ProductMenu.productMenu();
                    break;
                case 2:
                    // put order classes under here
                    Order.order();
                    break;
                case 3:
                    // put staff classes under here
                    Staff.staff();
                    break;
                case 4:
                    // put reporting classes under here
                    Reporting.reporting();
                    break;
                default:
                    Auth.endSession();
            }
        }
    }
        private static void showLogo() {
            System.out.println("\n" +
                    "______  ________________  __     _______________________\n" +
                    "___/ / / /_  __ \\__  /_ \\/ /     ___  __ \\_  __ \\_  ___/\n" +
                    "__/ /_/ /_  / / /_  / __  /________  /_/ /  / / /____ \\ \n" +
                    "_/ __  / / /_/ /_  /___  /_/_____/  ____// /_/ /____/ / \n" +
                    "/_/ /_/  \\____/ /_____/_/        /_/     \\____/ /____/  \n" +
                    "                                                        \n");
        }
}
