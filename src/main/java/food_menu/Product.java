package food_menu;

public class Product {
    private String id, title, desc;
    private double price;
    private int tax;

    public Product(String id, String title, String desc, double price, int tax) {
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

    public int getTax() {
        return tax;
    }

    public void setPrice(int price) {
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

    public void setTax(int tax) {
        this.tax = tax;
    }
}
