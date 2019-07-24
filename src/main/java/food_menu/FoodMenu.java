package food_menu;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FoodMenu {
    private static final int ID_FIELD = 1;
    private static final int TITLE_FIELD = 2;
    private static final int DESC_FIELD = 3;

    // array storing all the food being sold in fast food restaurant
    private static ArrayList<Product> products = new ArrayList<Product>(Arrays.asList
            (new Product("BBEEF", "Beefburger", "Beefburger", 5.99, 0.16),
                    new Product("BC", "Cheeseburger", "Cheeseburger", 5.99, 0.16),
                    new Product("BDC", "Double cheeseburger", "Double cheeseburger", 7.99, 0.16),
                    new Product("BCH", "Chicken Burger", "Chicken Burger", 5.99, 0.16),
                    new Product("BRSD", "Sausage Deluxe Breakfast", "Sausage burger x 1 + Hashbrown + 1 x coffee (S)", 8.99, 0.16),
                    new Product("LUBS", "Burger Set Lunch", "Double chicken burger + French fries (L) x Chicken nuggets (S) + Coca Cola Drink (L)", 8.99, 0.16)
            ));

    // =======================
    // FOODMENU USE ONLY
    // =======================

    // menu for manipulating the food menu
    public static void foodMenu() {
        Scanner in = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("1.  New Product");
            System.out.println("2.  Modify Product");
            System.out.println("3.  Search Product");
            System.out.println("4.  Delete Product");
            System.out.println("Other. Back");
            System.out.println();
            System.out.print("Enter your choice: ");

            choice = in.nextInt();

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
                    return;
            }
        }
    }

    // add a new product to the array
    private static void add() {
        String id, title, description;
        double price;
        double tax;
        Scanner in = new Scanner(System.in);

        // obtain required details
        System.out.println("ADD PRODUCTS\n");

        id = getID(in);
        if (id.equals("-1")) {
            return;
        }

        System.out.print("Enter product title [Max: 64 characters] or -1 to cancel: ");
        // TODO: Add tests for 64 characters & invalid input
        title = in.nextLine();
        if (title.equals("-1")) {
            return;
        }


        System.out.print("Enter product description [Max: 128 characters] or -1 to cancel: ");
        // TODO: Add tests for 128 characters & invalid input
        description = in.nextLine();
        if (description.equals("-1")) {
            return;
        }

        System.out.print("Enter product price (ex: 12.00) or -1 to cancel: ");
        // TODO: Add tests for invalid input
        price = in.nextDouble();
        if (price == -1) {
            return;
        }

        System.out.print("Enter product tax percentage (ex: 0.1) or -1 to cancel: ");
        // TODO: Add tests for invalid input
        tax = in.nextDouble();
        if (tax == -1) {
            return;
        }

        // add into the products arrayList
        products.add(new Product(id, title, description, price, tax));

        // print successful message
        System.out.println("Product addition successful!");

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

    private static String getID(Scanner in) {
        String id;
        System.out.print("Product ID [Max: 5 characters] or -1 to cancel: ");
        // TODO: Add tests for 5 characters & invalid input
        id = in.nextLine();
        return id;
    }

    // user can call this module to modify a product's details.
    private static void modify() {
        int index;
        Scanner in = new Scanner(System.in);
        Product temp;

        System.out.println("\nPRODUCT MODIFICATION\n");

        // Copy the single product entry into a new temporary product variable
        temp = filterProduct(products);

        // quit if returned 'null'
        if (temp == null) {
            return;
        }

        // keep the product index
        index = products.indexOf(temp);

        // accept all temporary changes from the user
        temp = modifyProd(temp);

        // ask for confirmation
        System.out.println("WARNING: You are irreversibly changing: ");
        products.get(index).showProduct();
        System.out.println("== TO ==");
        temp.showProduct();

        System.out.println("Are you sure? (Y/N): ");
        // copy the temporary product back into the real product
        if (in.nextLine().toLowerCase().equals("y")) {
            products.set(index, temp);
            System.out.println("Modification successful!");
        }
        else {
            System.out.println("Modification cancelled.");
        }

        // display successful message
        System.out.println("New product: ");
        products.get(index).showProduct();
    }

    // deletes a product from FoodMenu array
    private static void delete() {
        Scanner in = new Scanner(System.in);
        int index;
        Product temp;

        System.out.println("PRODUCT DELETION");

        // Copy the single product entry into a new temporary product variable
        temp = filterProduct(products);

        // quit if returned 'null'
        if (temp == null) {
            return;
        }

        // keep the product index
        index = products.indexOf(temp);

        // ask for confirmation
        System.out.println("WARNING: You are irreversibly deleting: \n");
        products.get(index).showProduct();

        System.out.println("Are you sure? (Y/N): ");
        // copy the temporary product back into the real product
        if (in.nextLine().toLowerCase().equals("y")) {
            products.remove(index);
            System.out.println("Deletion successful!");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    // searches products inside FoodMenu array
    private static void search() {
        Scanner in = new Scanner(System.in);
        String term;
        int basis;
        ArrayList<Product> found = new ArrayList<>();

        while (true) {
            System.out.println("Search product");

            System.out.println("Select your operation: \n" +
                                "1. View All\n" +
                                "2. Search products\n");
            if (in.nextInt() == 1) {
                showProducts();
                continue;
            }
            in.nextLine();

            System.out.println("Search on basis: \n");
            System.out.println("1. ID\n" +
                    "2. Name\n" +
                    "3. Description\n" +
                    "Other. Cancel\n");

            System.out.print("Enter your basis: ");
            basis = in.nextInt();
            in.nextLine();

            if (basis < 1 || basis > 3) {
                return;
            }

            System.out.print("Enter search term: ");
            term = in.nextLine();

            switch (basis) {
                case ID_FIELD:
                    found = searchX(term, ID_FIELD);
                    break;
                case TITLE_FIELD:
                    found = searchX(term, TITLE_FIELD);
                    break;
                case DESC_FIELD:
                    found = searchX(term, DESC_FIELD);
                    break;
                case 4:
                    found = products;
                    break;
            }
            // Display results
            showProducts(found);
        }
    }

    // helper function
    // gets a single Product for use in modify, delete
    private static Product filterProduct(ArrayList<Product> results) {

        int basis;
        String term;
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("Search on basis: \n");
            System.out.println("1. ID\n" +
                    "2. Name\n" +
                    "3. Description\n" +
                    "4. Cancel\n");

            System.out.print("Enter your basis: ");
            basis = in.nextInt();
            in.nextLine();

            if (basis == 4) {
                return null;
            }

            System.out.print("Enter your term: ");
            term = in.nextLine();

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
            showProducts(results);
            if (results.size() > 1) {
                showProducts(results);
                System.out.println("Please enter the index of your choice: ");
                return results.get(in.nextInt());
            } else if (results.size() < 1) {
                System.out.println("No results found, please enter a new search term.");
            }
        }
        while (results.size() != 1);

        return results.get(0);
    }

    // helper function -- narrows down the list of Products
    private static ArrayList<Product> searchX(String term, int field) {
        ArrayList<Product> results = new ArrayList<>();
        int termLength = term.length();

        switch (field) {
            case ID_FIELD:
                for (Product p : products) {
                    if (p.getId().substring(0, termLength).equals(term)) {
                        results.add(p);
                    }
                }
                break;
            case TITLE_FIELD:
                for (Product p : products) {
                    if (p.getTitle().substring(0, termLength).equals(term)) {
                        results.add(p);
                    }
                }
                break;
            case DESC_FIELD:
                for (Product p : products) {
                    if (p.getDesc().substring(0, termLength).equals(term)) {
                        results.add(p);
                    }
                }
                break;
        }
        return results;
    }

    // helper function
    private static Product modifyProd(Product temp) {
        int choice;
        Scanner in = new Scanner(System.in);
        String replaceString;
        double replaceNumber;

        do {
            System.out.println("Product Details (uncommitted): \n");
            temp.showProduct();
            System.out.println(
                    "What do you want to modify: \n"
                            + "1. ID\n"
                            + "2. Title\n"
                            + "3. Description\n"
                            + "4. Price\n"
                            + "5. Tax\n"
                            + "Other number. COMMIT/DISCARD changes\n"
                            + "Enter your choice (1-6): ");
            choice = in.nextInt();
            in.nextLine();

            System.out.print("Enter new ");
            switch (choice) {
                case 1: // ID
                    System.out.print(" ID: ");
                    replaceString = in.nextLine();
                    temp.setId(replaceString);
                    break;
                case 2: // TITLE
                    System.out.print(" TITLE: ");
                    replaceString = in.nextLine();
                    temp.setTitle(replaceString);
                    break;
                case 3: // DESC
                    System.out.print(" DESC: ");
                    replaceString = in.nextLine();
                    temp.setDesc(replaceString);
                    break;
                case 4: // PRICE
                    System.out.print(" PRICE (Eg: 5.0): ");
                    replaceNumber = in.nextDouble();
                    temp.setPrice(replaceNumber);
                    break;
                case 5: // TAX
                    System.out.print(" TAX [Percentage/100] (Eg: 0.06): ");
                    replaceNumber = in.nextDouble();
                    temp.setTax(replaceNumber);
                    break;
            }
        }
        while (choice >= 1 && choice <= 5);

        return temp;
    }

    // =======================
    // EXTERNAL & INTERNAL USE
    // =======================

    // show the products inside the array in a consistent format
    private static void showProducts(ArrayList<Product> products) {
        int index = 0;

        // TODO: Print header
        System.out.printf("%40s", "PRODUCTS\n");
        System.out.printf("| %5.24s | %5.24s | %24.24s | %24.24s | %6s | %6s | %6s |\n", "Index", "ID", "TITLE", "DESC.", "PRICE", "TAX", "NETT");
        System.out.printf("| %5.24s | %5.24s | %24.24s | %24.24s | %6s | %6s | %6s |\n", "*****", "**", "*****", "*****", "*****", "***", "****");
        // TODO: Print content
        for (Product p : products) {
            System.out.printf("| %5.24s | %5.24s | %24.24s | %24.24s | %6.2f | %6.2f | %6.2f |\n", index++, p.getId(), p.getTitle(), p.getDesc(), p.getPrice(), p.getTax(), p.getPrice() + p.getPrice() * p.getTax());
        }
        // TODO: Print footer
        System.out.println("=END=");
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
