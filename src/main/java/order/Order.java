package order;

import java.util.Scanner;
import java.util.ArrayList;
import food_menu.*;

public class Order {
    public static void order() {
        /*
        * 1. get staff id
        * 2. display menu
        * 3. select product
        * 4. enter quantity
        * 5. check if exists
        * 6. Add to cart
        * 7. Repeat
        * 8. Proceed
        * 9. Produce receipt
        * */

        Cart c = new Cart();
        String code;
        int action;
        int qty;
        double total;
        Scanner in = new Scanner(System.in);
        ArrayList<Product> menu = FoodMenu.getProducts();

        System.out.println("1. Add Or Minus Products");
        System.out.println("2. Delete Products");
        System.out.println("3. Clear Cart");
        System.out.println("4. Proceed");
        System.out.println("5. Quit");

        //Action
        System.out.print("Enter the action code > ");
        action = in.nextInt();
        in.nextLine();

        while(action>=1 && action <=5) {
            if (action == 1) {
                //Declaration
                Product p = null;

                //Show Menu
                FoodMenu.showProducts();

                //Prompt input message
                System.out.print("Enter the product code > ");
                code = in.nextLine();

                //When the input is not 'q'
                while (!code.equalsIgnoreCase("q")) {
                    p = FoodMenu.getProductByID(code);
                    p = new Product(code, p.getTitle(), p.getDesc(), p.getPrice(), p.getTax());

                    //When the product exists in the menu
                    if (p.getId() != "") {
                        //And already exists in the cart
                        if (c.getItems().containsKey(p)) {
                            System.out.println("The items has already existed in the cart");
                        }
                        System.out.print("How many you want to add ? > ");
                        qty = in.nextInt();
                        in.nextLine();

                        c.addOrMinus(p, qty);
                    } else {
                        System.out.println("The code does not exists.");
                    }

                    p = null;

                    System.out.print("Enter the product code or q to quit > ");
                    code = in.nextLine();
                }
            } else if (action == 2) {
                //Declaration
                Product p;

                System.out.print("Enter the product code > ");
                code = in.nextLine();
                p = FoodMenu.getProductByID(code);
                p = new Product(code, p.getTitle(), p.getDesc(), p.getPrice(), p.getTax());
                if (c.getItems().containsKey(p)) {
                    c.del(p);
                }
            } else if (action == 3) {
                c.clearCart();
            } else if (action == 4) {
                break;
            } else if (action == 5) {
                return;
            } else {
                System.out.println("Invalid action!");
            }
            c.display();

            System.out.println("1. Add Or Minus Products");
            System.out.println("2. Delete Products");
            System.out.println("3. Clear Cart");
            System.out.println("4. Proceed");
            System.out.println("5. Quit");

            //Action
            System.out.print("Enter the action code > ");
            action = in.nextInt();
            in.nextLine();
        }

        total = c.proceed();

        System.out.println("The total price is RM" + total);
        System.out.print("Amount Received is RM");
        double amountReceived = in.nextDouble();
        while(amountReceived < total) {
            System.out.println("Not enough!");
            System.out.print("Amount Received is RM");
            amountReceived = in.nextDouble();
        }
        double change = amountReceived - total;
        System.out.println("Change Amount is RM" + change);

        Receipt r = new Receipt(c, total);
        System.out.printf("%-8s%-25s%4s%8s%8s\n","ID","Name","Qty","RM","RM");
        System.out.println("---------------------------------------------+-------");
        r.display();
        System.out.println("---------------------------------------------+-------");
        System.out.printf("%45s%8.2f\n","Total", r.getTotal());
    }
}