package food_menu;

import java.sql.*;
import java.util.Scanner;

public class FoodMenu {
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

        System.out.print("Enter product tax percentage (ex: 5) or -1 to cancel: ");
        // TODO: Add tests for invalid input
        tax = in.nextInt();
        if (tax == -1) {
            return;
        }

        // add into the database
        Product prod = new Product(id, title, description, price, tax);

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\user300\\repo\\holy.pos\\src\\main\\holyPos.db");
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO menu(id,title, desc, gross_price, tax) VALUES(?,?,?,?,?)");
            pstmt.setString(1, id);
            pstmt.setString(2, title);
            pstmt.setString(3, description);
            pstmt.setDouble(4, price);
            pstmt.setDouble(5, tax);
            pstmt.executeUpdate();
//            ResultSet rs = pstmt.executeQuery("select * from menu");
//            while(rs.next())
//            {
//
//                // output message, along with input data from the database to verify
//                System.out.println("ADD PRODUCT SUCCESS!");
//                System.out.println("New product information: ");
//
//                // read the result set
//                System.out.println("id = " + rs.getString("id"));
//                System.out.println("title = " + rs.getInt("title"));
//                System.out.println("description = " + rs.getInt("description"));
//                System.out.println("price = " + rs.getInt("price"));
//                System.out.println("tax = " + rs.getInt("tax"));
//            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }

    // user can call this module to modify a product's details.
    private static void modify() {
        Scanner in = new Scanner(System.in);
        String id, replacement;
        int choice = -1;

        System.out.println("PRODUCT MODIFICATION");
        System.out.print("Enter product ID: ");
        id = in.nextLine();

        if (true) {
            System.out.print("Found matching product.\n"
                    + "Product Details: \n"
                    + "TODO: Some product details here.\n"
                    + "What do you want to modify?\n");


            while (true) {
                System.out.print("TODO: Some modification choices here.\n"
                        + "Enter your choice:\n"
                        + "1. Flip Pizzas\n"
                        + "2. Flip Tomatoes\n"
                        + "3. Flip Onions\n"
                        + "5. Quit\n" +
                        "\n" +
                        "Enter your choice: ");

                choice = in.nextInt();
                in.nextLine();

                System.out.print("Enter your replacement value: ");
                replacement = in.nextLine();

                switch(choice) {
                    case 1:
                        System.out.println("You have selected: ");
                    case 2:
                    case 3:
                    case 4:
                        continue;
                    case 5:
                        return;
                    default:
                        break;
                }

                System.out.println("Success!");
                System.out.println();
                System.out.println("TODO: New product info: ");
        }
        }
        else {
            System.out.println("System failed to find any matches, maybe enter a new product ID?");
        }
    }

    private static void delete() {
        Scanner in = new Scanner(System.in);
        String id;

        System.out.println("Delete product");
        System.out.println("Enter product ID: ");
        id = in.nextLine();

        System.out.println("Product found: ");
        System.out.println("Are you sure you want to delete? (Y/N)");
        if (in.nextLine().equalsIgnoreCase("Y")) {
            System.out.println("Deleted.\n");
        }
        else {
            System.out.println("Not deleted.\n");
        }

    }

    private static void search() {
        Scanner in = new Scanner(System.in);
        String id;

        while(true) {
            System.out.println("Search product");
            System.out.println("Enter product ID: ");
            id = in.nextLine();

            System.out.println("Product found.");
            System.out.println("PRODUCT DETAILS");
            System.out.println("Search again? (Y/N)");
            if (in.nextLine().equalsIgnoreCase("N")) {
                return;
            }
        }


    }


}
