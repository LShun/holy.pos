import auth.Auth;
import example.ExampleHello;
import food_menu.FoodMenu;
import order.Order;
import reporting.Reporting;
import staff.Staff;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int choice = 0;

        while (true) {
            System.out.println();
            System.out.println("1. Example");
            System.out.println("2. Authentication");
            System.out.println("3. Food Menu");
            System.out.println("4. Order");
            System.out.println("5. Staff");
            System.out.println("6. Reporting");
            System.out.println("Enter any other number to exit.");
            System.out.println();
            System.out.print("Please select your module: ");

            choice = in.nextInt();

            switch (choice) {
                case 1:
                    // example: put example.Hello class
                    ExampleHello.hello();
                    break;
                case 2:
                    // put auth classes under here
                    Auth.auth();
                    break;
                case 3:
                    // put food_menu classes under here
                    FoodMenu.foodMenu();
                    break;
                case 4:
                    // put order classes under here
                    Order.order();
                    break;
                case 5:
                    // put staff classes under here
                    Staff.staff();
                    break;
                case 6:
                    // put reporting classes under here
                    Reporting.reporting();
                default:
                    return;
            }
        }
    }
}
