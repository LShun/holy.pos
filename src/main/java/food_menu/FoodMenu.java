package food_menu;

import de.vandermeer.asciitable.AsciiTable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static pub.formatPrint.printHeader;
import static pub.vScan.*;

public class FoodMenu {
    private static final int ID_FIELD = 1;
    private static final int TITLE_FIELD = 2;
    private static final int DESC_FIELD = 3;

    // array storing all the food being sold in fast food restaurant
    private static ArrayList<Product> products = new ArrayList<>(Arrays.asList
            (new Product("BBEEF", "Beefburger", "Beefburger", 5.99),
                    new Product("BC", "Cheeseburger", "Cheeseburger", 5.99),
                    new Product("BDC", "Double cheeseburger", "Double cheeseburger", 7.99),
                    new Product("BCH", "Chicken Burger", "Chicken Burger", 5.99),
                    new Product("BRSD", "Sausage Deluxe Breakfast", "Sausage burger x 1 + Hashbrown + 1 x coffee (S)", 8.99),
                    new Product("LUBS", "Burger Set Lunch", "Double chicken burger + French fries (L) x Chicken nuggets (S) + Coca Cola Drink (L)", 8.99),
                    new Product("COLAS", "Coca-cola Drink (Small)", "Small coca-cola drink, 250ml x1", 1.99),
                    new Product("COLAL", "Coca-cola Drink (Large)", "Large coca-cola drink, 500ml x1", 1.99)
            ));

    // =======================
    // FOODMENU USE ONLY
    // =======================

    // menu for manipulating the food menu
    public static void foodMenu() {
        // variables
        int choice;

        // show user possible actions & accept choice
        while (true) {
            // show user possible actions
            printHeader("FOOD MENU");
            System.out.println("1.      New Product");
            System.out.println("2.      Modify Product");
            System.out.println("3.      Search Product");
            System.out.println("4.      Delete Product");
            System.out.println("Other.  Back");
            System.out.print("Enter your choice: ");

            // accept choice
            choice = getInt();

            switch (choice) {
                case 1:
                    add();
                    break;
                case 2:
                    modify();
                    break;
                case 3:
                    search();
                    break;
                case 4:
                    delete();
                    break;
                default:
                    printHeader("END");
                    return;
            }
        }
    }

    // add a new product to the array
    private static void add() {
        Product temp = new Product();

        // obtain required details
        printHeader("ADD PRODUCTS");

        do {
            temp.setID();
            if (temp.getId().equals("-1")) {
                return;
            }
        }
        while (!validateID(temp));

        temp.setTitle();
        if (temp.getTitle().equals("-1")) {
            return;
        }

        temp.setDesc();
        if (temp.getDesc().equals("-1")) {
            return;
        }

        temp.setPrice();
        if (temp.getPrice() == -1) {
            return;
        }

        // add into the products arrayList
        products.add(temp);

        // print successful message
        printHeader("Product addition successful!");
        System.out.println(temp.toString());

        // commented out...not using database
//        try {
//            // create a database connection
//            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\user300\\repo\\holy.pos\\src\\main\\holyPos.db");
//            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO menu(id,title, desc, gross_price, tax) VALUES(?,?,?,?,?)");
//            pstmt.setString(1, id);
//            pstmt.setString(2, title);
//            pstmt.setString(3, description);
//            pstmt.setDouble(4, price);
//            pstmt.setDouble(5, tax);
//            pstmt.executeUpdate();
////            ResultSet rs = pstmt.executeQuery("select * from menu");
////            while(rs.next())
////            {
////
////                // output message, along with input data from the database to verify
////                System.out.println("ADD PRODUCT SUCCESS!");
////                System.out.println("New product information: ");
////
////                // read the result set
////                System.out.println("id = " + rs.getString("id"));
////                System.out.println("title = " + rs.getInt("title"));
////                System.out.println("description = " + rs.getInt("description"));
////                System.out.println("price = " + rs.getInt("price"));
////                System.out.println("tax = " + rs.getInt("tax"));
////            }
//        } catch (SQLException e) {
//            // if the error message is "out of memory",
//            // it probably means no database file is found
//            System.err.println(e.getMessage());
//        } finally {
//            try {
//                if (connection != null)
//                    connection.close();
//            } catch (SQLException e) {
//                // connection close failed.
//                System.err.println(e.getMessage());
//            }
//        }
    }

    // user can call this module to modify a product's details.
    private static void modify() {
        int index;
        Product temp;

        printHeader("PRODUCT MODIFICATION");

        // get the specific Product
        temp = filterProduct(products);

        // if cancelled, return
        if (temp == null) {
            return;
        }

        /* keep the product index */
        index = products.indexOf(temp);

        /* copy the Product to make it local, to allow non-destructive editing */
        temp = new Product(temp.getId(), temp.getTitle(), temp.getDesc(), temp.getPrice());

        // accept all temporary changes from the user
        modifyProd(temp);

        // ask for confirmation
        printHeader("WARNING: You are irreversibly changing: ");
        System.out.println(products.get(index).toString());
        printHeader("TO");
        System.out.println(temp.toString());

        System.out.print("Are you sure? (Y/N): ");
        // copy the temporary product back into the real product
        if (getString().toLowerCase().equals("y")) {
            products.set(index, temp);
            printHeader("Modification successful!");
        } else {
            printHeader("Modification cancelled.");
        }

        // display successful message
        System.out.println("Product Details: ");
        System.out.println(products.get(index).toString());
    }

    // deletes a product from FoodMenu array
    private static void delete() {
        int index;
        Product temp;

        printHeader("PRODUCT DELETION");

        // Copy the single product entry into a new temporary product variable
        temp = filterProduct(products);

        // quit if returned 'null'
        if (temp == null) {
            return;
        }

        // keep the product index
        index = products.indexOf(temp);

        // ask for confirmation
        printHeader("WARNING: You are irreversibly delete");
        System.out.print(products.get(index).toString());

        System.out.print("Are you sure? (Y/N): ");
        // copy the temporary product back into the real product
        if (getString().toLowerCase().equals("y")) {
            products.remove(index);
            printHeader("Deletion successful!");
        } else {
            printHeader("Deletion cancelled.");
        }
    }

    // searches products inside FoodMenu array
    private static void search() {
        int basis;
        Product found;

        while (true) {
            printHeader("SEARCH PRODUCT");

            System.out.println("Select your operation: \n" +
                    "1. View All\n" +
                    "2. Search products\n" +
                    "Other. exit\n");

            basis = getInt();

            if (basis == 1) {
                showProducts();
                continue;
            } else if (basis != 2) {
                return;
            }

            found = filterProduct(products);

            // Display results
            if (found != null) {
                System.out.println(found.toString());
            }
        }
    }

    // helper function -- modify, delete -- gets a single Product
    // returns null if either no product is found, or user choose to quit
    private static Product filterProduct(ArrayList<Product> results) {

        int basis, choice;
        String term;

        do {
            System.out.println("Search on basis: \n");
            System.out.println("1. ID\n" +
                    "2. Name\n" +
                    "3. Description\n" +
                    "4. Show all\n" +
                    "Other. Cancel\n");

            System.out.print("Enter your basis: ");
            basis = getInt();

            if (basis < 1 || basis > 4) {
                return null;
            }

            if (basis == 4) {
                results = products;
            } else {
                System.out.print("Enter your term: ");
                term = getString();

                switch (basis) {
                    case ID_FIELD:
                        results = searchX(term, ID_FIELD);
                        break;
                    case TITLE_FIELD:
                        results = searchX(term, TITLE_FIELD);
                        break;
                    case DESC_FIELD:
                        results = searchX(term, DESC_FIELD);
                        break;
                }
            }

            if (results.size() > 1) {
                showProducts(results);
                System.out.print("Please enter the index of your choice (outside range = exit): ");
                choice = getInt();
                if (choice < 0 || choice >= results.size()) {
                    return null;
                }
                return results.get(choice);
            } else if (results.size() < 1) {
                System.out.println("No results found, please enter a new search term.");
            }
        }
        while (results.size() != 1);

        return results.get(0);
    }

    // helper function -- search() -- narrows down the list of Products
    private static ArrayList<Product> searchX(String term, int field) {
        ArrayList<Product> results = new ArrayList<>();
        int termLength = term.length();
        term = term.toLowerCase();

        for (Product p : products) {
            switch (field) {
                case ID_FIELD:
                    if (termLength <= p.getId().length()) {
                        if (p.getId().substring(0, termLength).toLowerCase().equals(term)) {
                            results.add(p);
                        }
                    }
                    break;
                case TITLE_FIELD:
                    if (termLength <= p.getTitle().length()) {
                        if (p.getTitle().substring(0, termLength).toLowerCase().equals(term)) {
                            results.add(p);
                        }
                    }
                    break;
                case DESC_FIELD:
                    if (termLength <= p.getDesc().length()) {
                        if (p.getDesc().substring(0, termLength).toLowerCase().equals(term)) {
                            results.add(p);
                        }
                    }
                    break;
            }
        }
        return results;
    }

    // helper function -- modify
    private static void modifyProd(Product temp) {
        int choice;

        do {
            printHeader("Product Details (uncommitted)");
            System.out.println(temp.toString());
            System.out.print(
                    "What do you want to modify: \n"
                            + "1. ID\n"
                            + "2. Title\n"
                            + "3. Description\n"
                            + "4. Price\n"
                            + "Other number. COMMIT/DISCARD changes\n"
                            + "Enter your choice (Any number): ");
            choice = getInt();

            System.out.print("Enter new ");
            switch (choice) {
                case 1: // ID
                    do {
                        temp.setID();
                    } while (!validateID(temp));
                    break;
                case 2: // TITLE
                    temp.setTitle();
                    break;
                case 3: // DESC
                    temp.setDesc();
                    break;
                case 4: // PRICE
                    temp.setPrice();
                    break;
            }
        }
        while (choice >= 1 && choice <= 4);

    }

    // helper function -- add, modify -- validates the product ID to ensure no repetition before adding
    private static boolean validateID(Product temp) {
        // get new ID
        for (Product p : products) {
            if (temp.getId().equals(p.getId())) {
                System.out.println("Duplicate product ID is not allowed.\n");
                return false;
            }
        }
        return true;
    }

    // =======================
    // EXTERNAL & INTERNAL USE
    // =======================

    // show the products inside the array in a consistent format
    private static void showProducts(ArrayList<Product> products) {
        int index = 0;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        AsciiTable at = new AsciiTable();

        // Print Header
        printHeader("PRODUCTS");
        printHeader("REPORT GENERATED ON: " + dateFormat.format(date));
        printHeader("TAX AMOUNT: " + Product.getTax());

        // Format contents
        at.addRule();
        at.addRow("Index", "ID", "TITLE", "DESC.", "PRICE", "NETT");
        at.addRule();

        for (Product p : products) {
            at.addRow(index++, p.getId(), p.getTitle(), p.getDesc(), String.format("%.2f", p.getPrice()), String.format("%.2f", p.getPrice() + p.getPrice() * Product.getTax()));
            at.addRule();
        }

        // Print the table
        String rend = at.render();
        System.out.println(rend);
    }

    // overloading: show all products
    public static void showProducts() {
        showProducts(products);
    }

    // void -> ArrayList<Product>
    // get all products from the menu (allow other functions to get a copy of the array)
    public static ArrayList<Product> getProducts() {
        return products;
    }

    // String -> Product
    // get a single product from the FoodMenu by ID
    // IF no such product is found, then return an "empty" product
    // with all String fields set to "" and all numeric fields set to 0 (or 0.0)
    public static Product getProductByID(String term) {
        for (Product p : products) {
            if (p.getId().equals(term)) {
                return p;
            }
        }
        return new Product();
    }
}
