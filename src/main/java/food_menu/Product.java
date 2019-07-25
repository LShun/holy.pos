package food_menu;

import javax.management.BadStringOperationException;
import java.util.Objects;
import java.util.Scanner;

public class Product {
    private String id, title, desc;
    private double price;
    private double tax;

    /*
        Constructors
    */

    public Product() {
        this.id = "";
        this.title = "";
        this.desc = "";
        this.price = 0.0;
        this.tax = 0.0;
    }

    public Product(String id, String title, String desc, double price, double tax) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.price = price;
        this.tax = tax;
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

    public double getTax() {
        return tax;
    }

    /*
        Setters
    */
    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    /*

        Setters with validation

     */

    public void setID() {
        Scanner in = new Scanner(System.in);
        String id;

        System.out.print("Product ID [Max: 10 characters] or -1 to cancel: ");
        id = in.nextLine();

        while (id.length() > 10) {
            System.out.print("ID Length > 10, Reenter: ");
            id = in.nextLine();
        }

        setId(id);
    }

    public void setTitle() {
        Scanner in = new Scanner(System.in);
        String title;

        System.out.print("Enter product title or -1 to cancel: ");
        title = in.nextLine();

        setTitle(title);
    }

    public void setDesc() {
        Scanner in = new Scanner(System.in);
        String desc;

        System.out.print("Enter product description or -1 to cancel: ");
        desc = in.nextLine();

        setTitle(desc);
    }

    public void setPrice() {
        Scanner in = new Scanner(System.in);
        double price;

        System.out.print("Enter product price (ex: 12.00) or -1 to cancel: ");
        price = in.nextDouble();

        while (price < 0) {
            System.out.print("Negative price invalid, retry: ");
            price = in.nextDouble();
        }
        setPrice(price);
    }

    public void setTax() {
        Scanner in = new Scanner(System.in);
        double tax;

        System.out.print("Enter product tax percentage (ex: 0.1) or -1 to cancel: ");
        tax = in.nextDouble();

        while (tax < 0) {
            System.out.print("Negative tax invalid, retry: ");
            tax = in.nextDouble();
        }
        setPrice(tax);
    }

    /*
        Other methods
    */

    public void showProduct() {
        System.out.println(
                "ID: " + this.id + "\n" +
                "TITLE: " + this.title + "\n" +
                "DESC: " + this.desc + "\n" +
                "PRICE: " + this.price + "\n" +
                "TAX: " + this.tax + "\n");
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

        if (hash == p.hashCode())
            return true;
        else
            return false;
    }
}
