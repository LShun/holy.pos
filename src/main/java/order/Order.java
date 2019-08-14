package order;

import auth.AuthV2;
import pub.*;
import menu.*;
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
                    new Item(Menu.getProductByID("LUBS"),2),
                    new Item(Menu.getProductByID("BC"),2)
                    ))),50.00, LocalDateTime.of(2019,6,27,8,30,30)),
            new Receipt(new Cart("1906270002", Staff.getEmployeeList().get(0), new ArrayList<Item>(Arrays.asList(
                    new Item(Menu.getProductByID("BDC"),2),
                    new Item(Menu.getProductByID("BCH"),3)
                    ))),20, LocalDateTime.of(2019,6,27,10,00,25)),
            new Receipt(new Cart("1906270003", Staff.getEmployeeList().get(1), new ArrayList<Item>(Arrays.asList(
                    new Item(Menu.getProductByID("LUBS"),3),
                    new Item(Menu.getProductByID("BRSD"),1)
                    ))),36, LocalDateTime.of(2019,6,27,10,20,35)),
            new Receipt(new Cart("1906270004", Staff.getEmployeeList().get(2), new ArrayList<Item>(Arrays.asList(
                    new Item(Menu.getProductByID("LUBS"),4)
                    ))),40, LocalDateTime.of(2019,6,27,10,50,57)),
            new Receipt(new Cart("1907280005", Staff.getEmployeeList().get(3), new ArrayList<Item>(Arrays.asList(
                    new Item(Menu.getProductByID("LUBS"),4),
                    new Item(Menu.getProductByID("BRSD"),10)
                    ))),150, LocalDateTime.of(2019,7,28,11,50,57)),
            new Receipt(new Cart("1907290006", Staff.getEmployeeList().get(2), new ArrayList<Item>(Arrays.asList(
                    new Item(Menu.getProductByID("BDC"),2),
                    new Item(Menu.getProductByID("LUBS"),4),
                    new Item(Menu.getProductByID("BCH"),1)
                    ))), 40, LocalDateTime.of(2019,7,29,14,50,57)),

            new Receipt(new Cart("1906270007", Staff.getEmployeeList().get(2), new ArrayList<Item>(Arrays.asList(
                    new Item(Menu.getProductByID("BC"),6),
                    new Item(Menu.getProductByID("COLAL"),4),
                    new Item(Menu.getProductByID("BCH"),1),
                    new Item(Menu.getProductByID("LUBS"),1)
            ))), 40, LocalDateTime.of(2019,8,14,15,32,29)),
            new Receipt(new Cart("1906270008", Staff.getEmployeeList().get(2), new ArrayList<Item>(Arrays.asList(
                    new Item(Menu.getProductByID("COLAS"),6),
                    new Item(Menu.getProductByID("BRSD"),4),
                    new Item(Menu.getProductByID("BCH"),2)
                    ))), 40, LocalDateTime.of(2019,8,14,16,40,57))

    );

    public static void order() {

        Worker session = AuthV2.getSession();
        if(session == null){
            System.out.println("You have not log in.\nPlease log in to continue.");
            return;
        }

        FormatPrint.printHeader("ORDER");

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
            else {
                boolean hasBeenChange = c.getListOfItems().retainAll(Item.productAvailable());  //Delete the item from the cart tht is not available due to the deleted product
                if(hasBeenChange)
                    System.out.println("Some items have been remove due to unavailable product in the cart.\nPlease check the cart");
            }
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
                    FormatPrint.printHeader("ADD OR MINUS PRODUCT");
                    addOrMinusProduct();
                    break;
                case 2:
                    FormatPrint.printHeader("DISPLAYING PRODUCT");
                    c.display();
                    break;
                case 3:
                    FormatPrint.printHeader("DELETE PRODUCT");
                    delete();
                    break;
                case 4:
                    FormatPrint.printHeader("CLEARING CART");
                    c.clearCart();
                    break;
                case 5:
                    if (c.getListOfItems().isEmpty()) {
                        System.out.println("The cart is empty!");
                    }else {
                        FormatPrint.printHeader("PROCEED");
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

        System.out.print("Enter the product code : ");
        code = VScan.getString();

        item = new Item(Menu.getProductByID(code), 0);
        if(c.del(item))
            System.out.println("The item has been delete successfully!");
        else
            System.out.println("Failed to delete the item!");
    }

    private static void addOrMinusProduct(){
        //Declaration
        Item item;
        Product product;
        String code;
        int qty;

        //Show Menu
        Menu.showProducts();

        //Prompt input message for the product ID
        System.out.print("Enter the product code or -1 to quit : ");
        code = VScan.getString();

        //When the input is not '-1', get into the loop
        while (!code.equalsIgnoreCase("-1")) {
            product = Menu.getProductByID(code);

            //When the product exists in the menu
            if (!product.getId().equals("")) {
                //Instantiate a product that is independent from the one in the FoodMenu
                Product tempProduct = new Product(product.getId(),product.getTitle(),product.getDesc(),product.getPrice());

                //Check if the item is already existed in the cart
                item = new Item(tempProduct, 0);
                if (c.getListOfItems().contains(item)) {
                    int index = c.getListOfItems().indexOf(item);
                    System.out.println("The item has already existed in the cart.");
                    System.out.println("Current quantity - " + c.getListOfItems().get(index).getQty());
                }
                System.out.print("How many you want to add ? : ");
                qty  = VScan.getInt();
                item = new Item(tempProduct, qty);

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