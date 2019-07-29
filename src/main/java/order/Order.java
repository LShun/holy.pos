package order;

import auth.Auth;
import pub.vScan;
import food_menu.*;
import java.util.ArrayList;

public class Order {
    private static Cart c;
    private static ArrayList<Receipt> receiptList = new ArrayList<Receipt>();

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

        if(Auth.s == null){
            System.out.println("You have not log in.\nPlease log in to continue.");
            return;
        }

        c = new Cart();
        int action;
        double total;

        orderMenu();

        //Action
        System.out.print("Enter the action code : ");
        action = vScan.getInt();

        while(action>=1 && action <=5) {
            if (action == 1) {
                addOrMinusProduct();
            } else if (action == 2) {
                delete();
            } else if (action == 3) {
                c.clearCart();
            } else if (action == 4) {
                if(c.getListOfItems().isEmpty())
                    System.out.println("The cart is empty!");
                else
                    break;
            } else if (action == 5) {
                return;
            } else {
                System.out.println("Invalid action!");
            }
            c.display();

            orderMenu();

            //Action
            System.out.print("Enter the action code : ");
            action = vScan.getInt();
        }

        total = c.getTotal();

        System.out.println("The total price is RM" + total);
        System.out.print("Amount Received is RM");
        double amountReceived = vScan.getDouble();
        while(amountReceived < total) {
            System.out.println("Not enough!");
            System.out.print("Amount Received is RM");
            amountReceived = vScan.getDouble();
        }
        double change = amountReceived - total;
        System.out.println("Change Amount is RM" + change);

        Receipt r = new Receipt(c, amountReceived);
        r.display();

        receiptList.add(r);
    }

    private static void orderMenu() {
        System.out.println("1. Add Or Minus Products");
        System.out.println("2. Delete Products");
        System.out.println("3. Clear Cart");
        System.out.println("4. Proceed");
        System.out.println("5. Quit");
    }

    private static void delete() {
        String code;//Declaration
        Product p;

        System.out.print("Enter the product code > ");
        code = vScan.getString();

        p = FoodMenu.getProductByID(code);
        p = new Product(code, p.getTitle(), p.getDesc(), p.getPrice(), p.getTax());
        if (c.getListOfItems().contains(p)) {
            c.del(p);
        }
    }

    private static void addOrMinusProduct(){
        //Declaration
        Item p = null;
        String code;
        int qty;

        //Show Menu
        FoodMenu.showProducts();

        //Prompt input message
        System.out.print("Enter the product code or -1 to quit : ");
        code = vScan.getString();

        //When the input is not 'q'
        while (!code.equalsIgnoreCase("-1")) {
            p = new Item(FoodMenu.getProductByID(code), 0);
            //p = new Product(p.getId(), p.getTitle(), p.getDesc(), p.getPrice(), p.getTax());

            //When the product exists in the menu
            if (p.getProduct().getId() != "") {
                //And already exists in the cart
                if (c.getListOfItems().contains(p)) {
                    System.out.println("The item has already existed in the cart.");
                }
                System.out.print("How many you want to add ? : ");
                qty = vScan.getInt();

                p.setQty(qty);
                c.addOrMinus(p);
                c.display();
            } else {
                System.out.println("The code does not exists.");
            }

            p = null;

            System.out.print("Enter the product code or -1 to quit : ");
            code = vScan.getString();
        }
    }

    /*
    * For Reporting
    */

    public static ArrayList<Receipt> getReceiptList(){
        return receiptList;
    }
}