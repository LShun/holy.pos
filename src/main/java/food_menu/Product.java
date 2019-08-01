package food_menu;

import java.util.Objects;
import java.util.Scanner;

import static pub.vScan.*;

public class Product {
    private String id, title, desc;
    private double price;
    private static double tax = 0.16;

    /*
        Constructors
    */

    public Product() {
        this.id = "";
        this.title = "";
        this.desc = "";
        this.price = 0.0;
    }

    public Product(String id, String title, String desc, double price) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.price = price;
    }

    /*
        Getters
    */

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public double getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public static double getTax() {
        return tax;
    }

    /*
            Setters
        */
    public void setId(String id) {
        this.id = id;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setDesc(String desc) {
        this.desc = desc;
    }

    private void setPrice(double price) {
        this.price = price;
    }

    public static void setTax(double tax) {
        Product.tax = tax;
    }

    /*
        Setters with validation
     */

    public void setID() {
        Scanner in = new Scanner(System.in);
        String id;

        System.out.print("Product ID [Max: 10 characters] or -1 to cancel: ");
        id = getString();

        while (id.length() > 10) {
            System.out.print("ID Length > 10, Reenter: ");
            id = getString();
        }

        setId(id);
    }

    public void setTitle() {
        Scanner in = new Scanner(System.in);
        String title;

        System.out.print("Enter product title or -1 to cancel: ");
        title = getString();

        setTitle(title);
    }

    public void setDesc() {
        Scanner in = new Scanner(System.in);
        String desc;

        System.out.print("Enter product description or -1 to cancel: ");
        desc = getString();

        setDesc(desc);
    }

    public void setPrice() {
        Scanner in = new Scanner(System.in);
        double price;

        System.out.print("Enter product price (ex: 12.00) or -1 to cancel: ");
        price = getDouble();

        while (price < 0) {
            System.out.print("Negative price invalid, retry: ");
            price = getDouble();
        }
        setPrice(price);
    }

    public double getNetPrice() {
        return this.price * this.tax;
    }

    /*
        Other methods
    */

    public String toString() {
        return (
                "ID     : " + this.id + "\n" +
                "TITLE  : " + this.title + "\n" +
                "DESC   : " + this.desc + "\n" +
                "PRICE  : " + this.price + "\n");
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        else if (o == null) return false;
        else if (this.getClass() != o.getClass()) return false;
        Product p = (Product) o;

        int hash = hashCode();

        return hash == p.hashCode();
    }
}
