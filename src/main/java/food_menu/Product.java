package food_menu;

public class Product {
    private String id, title, desc;
    private double price;
    private double tax;

    public Product(String id, String title, String desc, double price, double tax) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.price = price;
        this.tax = tax;
    }

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

    public void setPrice(double price) {
        this.price = price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public void showProduct() {
        System.out.println(
                "ID: " + this.id + "\n" +
                "TITLE: " + this.title + "\n" +
                "DESC: " + this.desc + "\n" +
                "PRICE: " + this.price + "\n" +
                "TAX: " + this.tax + "\n");
    }
}
