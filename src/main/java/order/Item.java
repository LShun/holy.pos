package order;

import product_menu.Product;
import product_menu.ProductMenu;

import java.util.ArrayList;
import java.util.Objects;

public class Item {
    private Product product;
    private int qty;

    public Item(Product product, int qty) {
        this.product = product;
        this.qty = qty;
    }

    //Convert the Products into Items
    public static ArrayList<Item> productAvailable() {
        ArrayList<Item> temp = new ArrayList<Item>();
        ArrayList<Product> prod = ProductMenu.getProducts();

        for (Product x : prod)
            temp.add(new Item(x, 0));

        return temp;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product p) {
        this.product = p;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        if (qty > 0)
            this.qty = qty;
        else
            System.out.println("Invalid input quantity!");
    }

    public void changeQtyBy(int qty) {
        this.setQty(this.qty + qty);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return getProduct().equals(item.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduct());
    }
}
