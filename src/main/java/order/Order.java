package order;

import auth.Auth;
import pub.vScan;
import food_menu.*;
import staff.Staff;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    private static Cart c;
    private static ArrayList<Receipt> receiptList = new ArrayList<Receipt>();
    private static boolean haveBeenInitialized = false;

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

        if(!haveBeenInitialized){
            haveBeenInitialized = true;

            Cart initialization_cart = new Cart("1906270001",Staff.employeeList.get(0));
            initialization_cart.addOrMinus(new Item(FoodMenu.getProductByID("LUBS"),2));
            initialization_cart.addOrMinus(new Item(FoodMenu.getProductByID("BC"),2));
            receiptList.add(new Receipt(initialization_cart,50.0, LocalDateTime.of(2019,6,27,8,30,30)));

            initialization_cart = new Cart("1906270002",Staff.employeeList.get(0));
            initialization_cart.addOrMinus(new Item(FoodMenu.getProductByID("BDC"),2));
            initialization_cart.addOrMinus(new Item(FoodMenu.getProductByID("BCH"),3));
            receiptList.add(new Receipt(initialization_cart,20.0,LocalDateTime.of(2019,6,27,10,00,25)));

            initialization_cart = new Cart("1906270003",Staff.employeeList.get(1));
            initialization_cart.addOrMinus(new Item(FoodMenu.getProductByID("LUBS"),3));
            initialization_cart.addOrMinus(new Item(FoodMenu.getProductByID("BRSD"),1));
            receiptList.add(new Receipt(initialization_cart,36.0, LocalDateTime.of(2019,6,27,10,20,35)));

            initialization_cart = new Cart("1906270004",Staff.employeeList.get(2));
            initialization_cart.addOrMinus(new Item(FoodMenu.getProductByID("LUBS"),4));
            receiptList.add(new Receipt(initialization_cart,40.0, LocalDateTime.of(2019,6,27,10,50,57)));

            initialization_cart = new Cart("1906270005",Staff.employeeList.get(3));
            initialization_cart.addOrMinus(new Item(FoodMenu.getProductByID("LUBS"),4));
            initialization_cart.addOrMinus(new Item(FoodMenu.getProductByID("BRSD"),10));
            receiptList.add(new Receipt(initialization_cart,150, LocalDateTime.of(2019,7,28,11,50,57)));

            initialization_cart = new Cart("1906270006",Staff.employeeList.get(2));
            initialization_cart.addOrMinus(new Item(FoodMenu.getProductByID("BDC"),2));
            initialization_cart.addOrMinus(new Item(FoodMenu.getProductByID("LUBS"),4));
            initialization_cart.addOrMinus(new Item(FoodMenu.getProductByID("BCH"),1));
            receiptList.add(new Receipt(initialization_cart,40.0, LocalDateTime.of(2019,7,29,14,50,57)));
        }

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

        while(action != 4 || c.getListOfItems().isEmpty()) {
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

//        for(Receipt receipt : receiptList){
//            receipt.display();
//        }
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
        Item item;

        System.out.print("Enter the product code > ");
        code = vScan.getString();

        item = new Item(FoodMenu.getProductByID(code), 0);
        if(c.del(item))
            System.out.println("The item has been delete successfully!");
        else
            System.out.println("The item has not been delete successfully!");
    }

    private static void addOrMinusProduct(){
        //Declaration
        Item item = null;
        Product product;
        String code;
        int qty;

        //Show Menu
        FoodMenu.showProducts();

        //Prompt input message
        System.out.print("Enter the product code or -1 to quit : ");
        code = vScan.getString();

        //When the input is not 'q'
        while (!code.equalsIgnoreCase("-1")) {
            product = FoodMenu.getProductByID(code);
            //p = new Product(p.getId(), p.getTitle(), p.getDesc(), p.getPrice(), p.getTax());

            //When the product exists in the menu
            if (product.getId() != "") {
                //And already exists in the cart
                if (c.getListOfItems().contains(new Item(product, 0))) {
                    System.out.println("The item has already existed in the cart.");
                }
                System.out.print("How many you want to add ? : ");
                qty = vScan.getInt();
                item = new Item(product, qty);

                c.addOrMinus(item);
                c.display();
            } else {
                System.out.println("The code does not exists.");
            }

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