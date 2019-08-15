package product_menu;

import auth.Auth;
import de.vandermeer.asciitable.AsciiTable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static pub.FormatPrint.printHeader;
import static pub.VScan.*;

public class ProductMenu {
    private static final int ID_FIELD = 1;
    private static final int TITLE_FIELD = 2;
    private static final int DESC_FIELD = 3;

    // array storing all the products being sold in fast food restaurant
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
    // PRODUCTMENU USE ONLY
    // =======================

    // menu for manipulating the products menu
    public static void productMenu() {
        // variables
        int choice;

        // show user possible actions & accept choice
        while (true) {
            // show user possible actions
            printHeader("PRODUCT MENU");
            System.out.println("1.      Search Product");
            if (Auth.isManager()) {
                System.out.println("2.      New Product");
                System.out.println("3.      Modify Product");
                System.out.println("4.      Delete Product");
            }
            System.out.println("Other.  Back");
            System.out.print("Enter your choice: ");

            // accept choice
            choice = getInt();

            if (Auth.isManager()) {
                switch (choice) {
                    case 1:
                        search();
                        break;
                    case 2:
                        add();
                        break;
                    case 3:
                        modify();
                        break;
                    case 4:
                        delete();
                        break;
                    default:
                        printHeader("EXITED MODULE");
                        return;
                }
            }
            else {
                if (choice == 1) {
                    search();
                }
                else {
                    printHeader("EXITED MODULE");
                    return;
                }
            }
        }
    }

    // add a new product to the array
    private static void add() {
        Product temp = new Product();

        // obtain required details
        printHeader("ADD PRODUCTS");

        do {
            inputId(temp);
            if (temp.getId().equals("-1")) {
                return;
            }
        }
        while (!validateId(temp));

        inputTitle(temp);
        if (temp.getTitle().equals("-1")) {
            return;
        }

        inputDesc(temp);
        if (temp.getDesc().equals("-1")) {
            return;
        }

        inputPrice(temp);
        if (temp.getPrice() == -1) {
            return;
        }

        // add into the products arrayList
        products.add(temp);

        // print successful message
        printHeader("Product addition successful!");
        System.out.println(temp.toString());
    }

    // user can call this module to modify a product's details.
    private static void modify() {
        int index, choice;
        Product temp;

        printHeader("PRODUCT MODIFICATION");

        System.out.print(
                "What do you want to modify? \n" +
                        "1. Products\n" +
                        "2: Tax\n" +
                        "Other. Cancel\n" +
                        "Enter your choice: ");
        choice = getInt();

        switch (choice) {
            case 1:
                // get the specific Product
                temp = getProduct(products);

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
                break;
            case 2:
                printHeader("Tax modification (all products)");
                System.out.println("Current tax amount: " + Product.getTax() * 100 + " %");
                System.out.print("Enter new tax percentage/100 (ex: 0.16 for 16%), enter same amount to cancel: ");
                Product.setTax(getDouble());
                printHeader("Tax successfully set, new tax is: " + Product.getTax() * 100 + "% ");
                break;
        }
    }

    // deletes a product from ProductMenu array
    private static void delete() {
        int index;
        Product temp;

        printHeader("PRODUCT DELETION");

        // Copy the single product entry into a new temporary product variable
        temp = getProduct(products);

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

    // searches products inside ProductMenu array
    private static void search() {
        int basis;
        Product found;

        while (true) {
            printHeader("SEARCH PRODUCT");

            System.out.println("Select your operation: \n" +
                    "1. View All\n" +
                    "2. Search products\n" +
                    "Other. exit\n");

            System.out.print("Enter your operation (Number): ");
            basis = getInt();

            if (basis == 1) {
                showProducts();
                continue;
            } else if (basis != 2) {
                return;
            }

            found = getProduct(products);

            // Display results
            if (found != null) {
                printHeader("Detailed information");
                System.out.println(found.toString());
            }
        }
    }

    // helper function -- modify, delete -- gets a single Product
    // returns null if either no product is found, or user choose to quit
    private static Product getProduct(ArrayList<Product> results) {

        int basis, choice;
        String term;

        do {
            System.out.println("\nSearch on basis: \n");
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
                        results = filterProduct(term, ID_FIELD);
                        break;
                    case TITLE_FIELD:
                        results = filterProduct(term, TITLE_FIELD);
                        break;
                    case DESC_FIELD:
                        results = filterProduct(term, DESC_FIELD);
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
    private static ArrayList<Product> filterProduct(String term, int field) {
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

            if (choice >= 1 && choice <= 4) {
                System.out.print("Enter new ");
            }
            switch (choice) {
                case 1: // ID
                    do {
                        inputId(temp);
                    } while (!validateId(temp));
                    break;
                case 2: // TITLE
                    inputTitle(temp);
                    break;
                case 3: // DESC
                    inputDesc(temp);
                    break;
                case 4: // PRICE
                    inputPrice(temp);
                    break;
            }
        }
        while (choice >= 1 && choice <= 4);

    }

    // helper function -- add, modify -- validates the product ID to ensure no repetition before adding
    private static boolean validateId(Product temp) {
        // get new ID
        for (Product p : products) {
            if (temp.getId().equals(p.getId())) {
                System.out.println("Duplicate product found\n");
                System.out.println(p.toString());
                System.out.println("Duplicate product ID is not allowed.\n");
                return false;
            }
        }
        return true;
    }

    public static void inputId(Product p) {
        String id;
        System.out.print("Product ID (Eg: FRENCHFRIES) or -1 to cancel: ");
        id = getString();
        p.setId(id);
    }

    public static void inputTitle(Product p) {
        String title;
        System.out.print("Enter product title (Eg: French Fries) or -1 to cancel: ");
        title = getString();
        p.setTitle(title);
    }

    public static void inputDesc(Product p) {
        String desc;
        System.out.print("Enter product desc (Eg: Salted French Fries) or -1 to cancel: ");
        desc = getString();
        p.setDesc(desc);
    }

    public static void inputPrice(Product p) {
        double price;
        System.out.print("Enter product price (ex: 12.00) or -1 to cancel: ");
        price = getDouble();

        while (price < 0) {
            System.out.print("Negative price invalid, retry: ");
            price = getDouble();
        }

        p.setPrice(price);
    }

    // show the products inside the array in a consistent format
    public static void showProducts(ArrayList<Product> products) {
        String rend = getTable(products);
        System.out.println(rend);
    }

    // overloading: show all products
    public static void showProducts() {
        showProducts(products);
    }

    private static String getTable(ArrayList<Product> products) {
        int index = 0;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        AsciiTable at = new AsciiTable();

        // Print Header
        printHeader("PRODUCTS");
        printHeader("REPORT GENERATED ON: " + dateFormat.format(date));
        printHeader("TAX AMOUNT: " + Product.getTax() * 100 + "%");

        // Format contents
        at.addRule();
        at.addRow("Index", "ID", "TITLE", "DESC.", "PRICE (Exclude Tax)", "NETT (Include Tax)");
        at.addRule();

        for (Product p : products) {
            at.addRow(index++, p.getId(), p.getTitle(), p.getDesc(), String.format("%.2f", p.getPrice()), String.format("%.2f", p.getPrice() + p.getPrice() * Product.getTax()));
            at.addRule();
        }

        // Print the table
        return at.render();
    }

    // void -> ArrayList<Product>
    // get all products from the menu (allow other functions to get a copy of the array)
    public static ArrayList<Product> getProducts() {
        return products;
    }

    // String -> Product
    // get a single product from the ProductMenu by ID
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

    @Override
    public String toString() {
        return getTable(products);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        else if (o == null) return false;
        else return this.getClass() == o.getClass();
    }


}
