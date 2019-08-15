package product_menu;

import java.util.Objects;

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
        this.id = id.toUpperCase();
    }

    /*
        Accepts fields to be passed to setters from the user.
     */

    public double getNetPrice() {
        return this.price + this.price * this.tax;
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
                        "NETT (Include tax)   : " + String.format("%.2f", this.getNetPrice()) + "\n");
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
