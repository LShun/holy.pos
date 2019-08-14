package food_menu;

import java.util.Objects;
import java.util.Scanner;

import static pub.VScan.getDouble;
import static pub.VScan.getString;

public class Product {
    private static double tax = 0.16;
    private String id, title, desc;
    private double price;

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

    public static double getTax() {
        return tax;
    }

    public static void setTax(double tax) {
        Product.tax = tax;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    /*
        Setters : Assume validation already done after entering through the input.. functions
     */

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /*
        Accepts fields to be passed to setters from the user.
     */

    public void inputId() {
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

    public void inputTitle() {
        Scanner in = new Scanner(System.in);
        String title;

        System.out.print("Enter product title or -1 to cancel: ");
        title = getString();

        setTitle(title);
    }

    public void inputDesc() {
        Scanner in = new Scanner(System.in);
        String desc;

        System.out.print("Enter product description or -1 to cancel: ");
        desc = getString();

        setDesc(desc);
    }

    public void inputPrice() {
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
                "ID                   : " + this.id + "\n" +
                        "TITLE                : " + this.title + "\n" +
                        "DESC                 : " + this.desc + "\n" +
                        "PRICE (Exclude tax)  : " + this.price + "\n" +
                        "NETT (Include tax)   : " + this.getNetPrice() + "\n");
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.title, this.price);
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
