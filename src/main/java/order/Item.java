package order;

import food_menu.Product;

import java.util.Objects;

public class Item {
    private Product product;
    private int qty;

    public Item(Product product, int qty){
        this.product = product;
        this.qty     = qty;
    }

    public Product getProduct() { return product; }
    public int getQty() { return qty; }
    public void setProduct(Product p) { this.product = p; }
    public void setQty(int qty) {
        if(qty > 0)
            this.qty = qty;
        else
            System.out.println("Invalid input quantity!");
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