package food_menu;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FoodMenu {
    private static final int ID_FIELD = 1;
    private static final int TITLE_FIELD = 2;
    private static final int DESC_FIELD = 3;

    private static ArrayList<Product> products = new ArrayList<Product>();

    public static void foodMenu() {
        Scanner in = new Scanner(System.in);
        int choice;

        products.add(new Product("BBEEF", "Beefburger",	"Beefburger", 5.99, 0.16));
        products.add(new Product("BC", "Cheeseburger", "Cheeseburger", 5.99, 0.16));
        products.add(new Product("BDC", "Double cheeseburger", "Double cheeseburger", 7.99, 0.16));
        products.add(new Product("BCH", "Chicken Burger", "Chicken Burger", 5.99, 0.16));
        products.add(new Product("BRSD", "Sausage Deluxe Breakfast", "Sausage burger x 1 + Hashbrown + 1 x coffee (S)", 8.99, 0.16));
        products.add(new Product("LUBS", "Burger Set Lunch", "Double chicken burger + French fries (L) x Chicken nuggets (S) + Coca Cola Drink (L)", 8.99, 0.16));

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

    private static void add() {
        String id, title, description;
        double price;
        int tax;
        Scanner in = new Scanner(System.in);
        Connection connection = null;

        // obtain required details
        System.out.println("ADD PRODUCTS");
        System.out.println();
        System.out.print("Enter product ID [Max: 5 characters] or -1 to cancel: ");
        // TODO: Add tests for 5 characters & invalid input
        id = in.nextLine();
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
        tax = in.nextInt();
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

    // user can call this module to modify a product's details.
    private static void modify() {
        Scanner in = new Scanner(System.in);
        String term;
        int basis, index;
        ArrayList<Product> results = new ArrayList<>();
        Product temp;

        System.out.println("PRODUCT MODIFICATION");

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
                return;
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
                System.out.println("We found: ");
                showProducts(results);
                System.out.println("Please narrow down your search term so it only match 1 product!");
            }
            else if (results.size() < 1) {
                System.out.println("No results found, please enter a new search term.");
            }
        }
        while (results.size() != 1);

        // Copy the single product entry into a new temporary product variable
        temp = results.get(0);

        // keep the product index
        index = products.indexOf(temp);

        // accept all temporary changes from the user
        temp = modifyProd(temp);

        // ask for confirmation
        System.out.println("WARNING: You are going to irreversibly change: ");
        results.get(0).showProduct();
        System.out.println("== TO ==");
        temp.showProduct();

        System.out.println("Are you sure you want to commit the changes (Y/N): ");
        // copy the temporary product back into the real product
        if (in.nextLine().toLowerCase().equals("y")) {
            products.set(index, temp);
            System.out.println("Modification successful!");
        }
        else {
            System.out.println("No modification has been done.");
        }

        // display successful message
        products.get(index).showProduct();
    }

    private static Product modifyProd(Product temp) {
        int choice;
        Scanner in = new Scanner (System.in);
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
            switch(choice) {
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

    private static ArrayList<Product> searchX(String term, int field) {
        ArrayList<Product> results = new ArrayList<>();

        switch (field) {
            case ID_FIELD:
                for (Product p : products) {
                    if (p.getId().equals(term)) {
                        results.add(p);
                    }
                }
                break;
            case TITLE_FIELD:
                for (Product p : products) {
                    if (p.getTitle().equals(term)) {
                        results.add(p);
                    }
                }
                break;
            case DESC_FIELD:
                for (Product p : products) {
                    if (p.getDesc().equals(term)) {
                        results.add(p);
                    }
                }
                break;
        }
        return results;
    }

    private static void delete() {
        Scanner in = new Scanner(System.in);
        String term;
        int basis, index;
        ArrayList<Product> results = new ArrayList<>();
        Product temp;

        System.out.println("PRODUCT DELETION");

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
                return;
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
                System.out.println("We found: ");
                showProducts(results);
                System.out.println("Please narrow down your search term so it only match 1 product!");
            }
            else if (results.size() < 1) {
                System.out.println("No results found, please enter a new search term.");
            }
        }
        while (results.size() != 1);

        // Copy the single product entry into a new temporary product variable
        temp = results.get(0);

        // keep the product index
        index = products.indexOf(temp);

        // ask for confirmation
        System.out.println("WARNING: You are going to irreversibly delete: ");
        results.get(0).showProduct();

        System.out.println("Are you sure you want to delete the product (Y/N): ");
        // copy the temporary product back into the real product
        if (in.nextLine().toLowerCase().equals("y")) {
            products.remove(index);
            System.out.println("Deletion successful!");
        }
        else {
            System.out.println("No deletion has been done.");
        }
    }

    private static void search() {
        Scanner in = new Scanner(System.in);
        String id;
        ArrayList<Product> found = new ArrayList<>();

        while(true) {
            System.out.println("Search product");
            System.out.println("Enter product ID (or -1 to view all): ");
            id = in.nextLine();

            if (id.equals("-1")) {
                showProducts(products);
            }
            else {
                // Search by ID
                found = find(id);

                // Display results
                showProducts(found);
            }
            System.out.println("Search again? (Y = yes/Other = no)");
            if (in.nextLine().equalsIgnoreCase("y")) {
                continue;
            }
            else {
                return;
            }
        }
    }

    // narrow down the amount of products satisfying the given criteria
    private static ArrayList<Product> find(String criteria) {
        ArrayList<Product> found = new ArrayList<>();
        for (Product p : products) {
            if (p.getId().equals(criteria)) {
                found.add(p);
            }
        }
        return found;
    }

    // show all the products inside the array in a consistent format
    private static void showProducts(ArrayList<Product> products) {
        // TODO: Print header
        System.out.printf("%40s", "PRODUCTS\n");
        System.out.printf("| %5.24s | %24.24s | %24.24s | %6s | %6s | %6s |\n", "ID", "TITLE", "DESC.", "PRICE", "TAX", "NETT");
        System.out.printf("| %5.24s | %24.24s | %24.24s | %6s | %6s | %6s |\n", "**", "*****", "*****", "*****", "***", "****");
        // TODO: Print content
        for (Product p : products) {
            System.out.printf("| %5.24s | %24.24s | %24.24s | %6.2f | %6.2f | %6.2f |\n", p.getId(), p.getTitle(), p.getDesc(), p.getPrice(), p.getTax(), p.getPrice() + p.getPrice() * p.getTax());
        }
        // TODO: Print footer
        System.out.println("=END=");
    }

    // get products from the menu (allow other functions to get a copy of the array)
    public static ArrayList<Product> getProducts() {
        return products;
    }
}
