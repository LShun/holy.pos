import auth.Auth;
import food_menu.FoodMenu;
import order.Order;
import reporting.Reporting;
import staff.Staff;

import static pub.vScan.*;

public class Main {
    public static void main(String[] args) {

        int choice;

        while (true) {
            System.out.println("\n" +
                    "______  ________________  __     _______________________\n" +
                    "___/ / / /_  __ \\__  /_ \\/ /     ___  __ \\_  __ \\_  ___/\n" +
                    "__/ /_/ /_  / / /_  / __  /________  /_/ /  / / /____ \\ \n" +
                    "_/ __  / / /_/ /_  /___  /_/_____/  ____// /_/ /____/ / \n" +
                    "/_/ /_/  \\____/ /_____/_/        /_/     \\____/ /____/  \n" +
                    "                                                        \n");
            System.out.println("1. Authentication");
            System.out.println("2. Food Menu");
            System.out.println("3. Order");
            System.out.println("4. Staff");
            System.out.println("5. Reporting");
            System.out.println("Enter any other number to exit.");
            System.out.println();
        System.out.print("Please select your module: ");

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
