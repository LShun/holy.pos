package order;

import auth.AuthV2;
import pub.VScan;
import food_menu.*;
import staff.Staff;
import staff.Worker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Order {
    private static Cart c = null;
    private static ArrayList<Receipt> receiptList = new ArrayList<Receipt>(Arrays.asList(
            new Receipt(new Cart("1906270001", Staff.getEmployeeList().get(0), new ArrayList<Item>(Arrays.asList(
                    new Item(FoodMenu.getProductByID("LUBS"),2),
                    new Item(FoodMenu.getProductByID("BC"),2)
                    ))),50.00, LocalDateTime.of(2019,6,27,8,30,30)),
            new Receipt(new Cart("1906270002", Staff.getEmployeeList().get(0), new ArrayList<Item>(Arrays.asList(
                    new Item(FoodMenu.getProductByID("BDC"),2),
                    new Item(FoodMenu.getProductByID("BCH"),3)
                    ))),20, LocalDateTime.of(2019,6,27,10,00,25)),
            new Receipt(new Cart("1906270003", Staff.getEmployeeList().get(1), new ArrayList<Item>(Arrays.asList(
                    new Item(FoodMenu.getProductByID("LUBS"),3),
                    new Item(FoodMenu.getProductByID("BRSD"),1)
                    ))),36, LocalDateTime.of(2019,6,27,10,20,35)),
            new Receipt(new Cart("1906270004", Staff.getEmployeeList().get(2), new ArrayList<Item>(Arrays.asList(
                    new Item(FoodMenu.getProductByID("LUBS"),4)
                    ))),40, LocalDateTime.of(2019,6,27,10,50,57)),
            new Receipt(new Cart("1906270005", Staff.getEmployeeList().get(3), new ArrayList<Item>(Arrays.asList(
                    new Item(FoodMenu.getProductByID("LUBS"),4),
                    new Item(FoodMenu.getProductByID("BRSD"),10)
                    ))),150, LocalDateTime.of(2019,7,28,11,50,57)),
            new Receipt(new Cart("1906270006", Staff.getEmployeeList().get(2), new ArrayList<Item>(Arrays.asList(
                    new Item(FoodMenu.getProductByID("BDC"),2),
                    new Item(FoodMenu.getProductByID("LUBS"),4),
                    new Item(FoodMenu.getProductByID("BCH"),1)
                    ))), 40, LocalDateTime.of(2019,7,29,14,50,57)),
            new Receipt(new Cart("1906270007", Staff.getEmployeeList().get(2), new ArrayList<Item>(Arrays.asList(
                    new Item(FoodMenu.getProductByID("COLAS"),6),
                    new Item(FoodMenu.getProductByID("BRSD"),4),
                    new Item(FoodMenu.getProductByID("BCH"),2)
                    ))), 40, LocalDateTime.of(2019,8,14,16,40,57)),
            new Receipt(new Cart("1906270008", Staff.getEmployeeList().get(2), new ArrayList<Item>(Arrays.asList(
                    new Item(FoodMenu.getProductByID("BC"),6),
                    new Item(FoodMenu.getProductByID("COLAL"),4),
                    new Item(FoodMenu.getProductByID("BCH"),1),
                    new Item(FoodMenu.getProductByID("LUBS"),1)
            ))), 40, LocalDateTime.of(2019,8,14,15,32,29))
            )
    );

    public static void order() {

        Worker session = AuthV2.getSession();
        if(session == null){
            System.out.println("You have not log in.\nPlease log in to continue.");
            return;
        }

        //If There is transaction undone, the user is the same with the one quit order module last time
        if(c != null && c.getWorker().equals(session)){
            System.out.println("There is still transaction undone last time."); //Prompt use if wan to continue the transaction or not
            System.out.println("Do you want to continue the transaction ?");
            System.out.println("Y - Yes | N- No");
            char action = VScan.getChar();
            while(action!='N' && action !='Y'){
                System.out.println("Invalid Options!\nPlease reenter.");
                System.out.println("There is still transaction undone last time.");
                System.out.println("Do you want to continue the transaction ?");
                System.out.println("Y - Yes | N- No");
                action = VScan.getChar();
            }
            if(action == 'N')
                c = new Cart();
//            else {
//                boolean hasBeenChange = c.getListOfItems().retainAll(Item.productAvailable());  //Delete the item from the cart tht is not available due to the deleted product
//                if(hasBeenChange)
//                    System.out.println("Some items have been remove due to unavailable product in the cart.\nPlease check the cart");
//            }

        }else {
            c = new Cart();
        }

        int action;

        orderMenu(); //Display the menu

        //Action
        System.out.print("Enter the action code : ");
        action = VScan.getInt();

        while(action != 6) {
            switch (action) {
                case 1:
                    addOrMinusProduct();
                    break;
                case 2:
                    c.display();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    c.clearCart();
                    break;
                case 5:
                    if (c.getListOfItems().isEmpty()) {
                        System.out.println("The cart is empty!");
                    }else {
                        double total = c.getSubTotal() * (1 + Product.getTax());
                        proceed(session, total);
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid action!");
            }

            orderMenu();

            //Action
            System.out.print("Enter the action code : ");
            action = VScan.getInt();
        }
    }

    private static void proceed(Worker session, double total) {
        BigDecimal totalRounded = new BigDecimal(total).setScale(2,RoundingMode.HALF_EVEN);
        System.out.println("Unscaled - " + totalRounded);
        System.out.println("Scaled - " + totalRounded);

        System.out.println("The total price is RM" + totalRounded);
        System.out.print("Amount Received is RM");
        double amountReceived = VScan.getDouble();

        while(amountReceived < totalRounded.doubleValue()) {
            System.out.println("Not enough!");
            System.out.print("Amount Received is RM");
            amountReceived = VScan.getDouble();
        }
        double change = amountReceived - totalRounded.doubleValue();
        System.out.println("Change Amount is RM" + new BigDecimal(change).setScale(2, RoundingMode.HALF_EVEN));

        Receipt r = new Receipt(c, amountReceived); //Create a Receipt obj
        c = new Cart();      // Delete the reference to the cart obj
        r.display();         // Display receipt
        receiptList.add(r);  // Add the receipt into the ArrayList

        //Increase the total sales made by this current session
        session.setSalesReceived(session.getSalesReceived()+1);
    }

    private static void orderMenu() {
        System.out.println("1. Add Or Minus Products");
        System.out.println("2. Display Added Products");
        System.out.println("3. Delete Products");
        System.out.println("4. Clear Cart");
        System.out.println("5. Proceed");
        System.out.println("6. Quit");
    }

    private static void delete() {
        String code;//Declaration
        Item item;

        System.out.print("Enter the product code > ");
        code = VScan.getString();

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

        //Prompt input message for the product ID
        System.out.print("Enter the product code or -1 to quit : ");
        code = VScan.getString();

        //When the input is not '-1', get into the loop
        while (!code.equalsIgnoreCase("-1")) {
            product = FoodMenu.getProductByID(code);

            //When the product exists in the menu
            if (product.getId() != "") {
                //And already exists in the cart
                if (c.getListOfItems().contains(new Item(product, 0))) {
                    System.out.println("The item has already existed in the cart.");
                }
                System.out.print("How many you want to add ? : ");
                qty = VScan.getInt();
                item = new Item(product, qty);

                c.addOrMinus(item);
            } else {
                System.out.println("The code does not exists.");
            }

            System.out.print("Enter the product code or -1 to quit : ");
            code = VScan.getString();
        }
    }

    //For Reporting
    public static ArrayList<Receipt> getReceiptList(){
        return receiptList;
    }
}