package order;

import auth.Auth;
import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_FixedWidth;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import product_menu.Product;
import staff.Worker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Cart extends CartOrReceipt {

    private double subTotal;

    public Cart() {
        this.billID = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd")) + String.format("%04d", super.transactionMade);
        this.worker = Auth.getSession();
    }

    public Cart(String billID, Worker worker) {
        this.billID = billID;
        this.worker = worker;
    }

    public Cart(String billID, Worker worker, ArrayList<Item> item) {
        this(billID, worker);
        for (Item i : item) {
            Product tempProduct = i.getProduct();
            i.setProduct(new Product(tempProduct.getId(), tempProduct.getTitle(), tempProduct.getDesc(), tempProduct.getPrice()));
        }
        this.listOfItems = item;
    }

    public void addOrMinus(Item item) {
        int index = listOfItems.indexOf(item);
        int qtyInParam = item.getQty();

        if (index != -1) {
            listOfItems.get(index).changeQtyBy(qtyInParam);
        } else if (qtyInParam > 0) {
            listOfItems.add(item);
        } else {
            System.out.println("Invalid input quantity!");
        }
        subTotal = calculateSubTotal();
    }

    public boolean del(Item obj) {
        boolean ans = listOfItems.remove(obj);
        subTotal = calculateSubTotal();

        return ans;
    }

    public void clearCart() {
        listOfItems.clear();
        subTotal = 0;
    }

    public double calculateSubTotal() {
        double subTotal = 0;
        for (Item e : listOfItems) {
            subTotal += e.getProduct().getPrice() * e.getQty();
        }
        return subTotal;
    }

    public void display() {
        AsciiTable at = new AsciiTable();
        CWC_FixedWidth width = new CWC_FixedWidth();
        at.getRenderer().setCWC(width);
        width.add(4).add(6).add(25).add(3).add(7).add(8); //Specify the width of each column
        at.addRule();

        AT_Row heading = at.addRow("No.", "ID", "Name", "Qty", "RM", "RM");
        heading.setTextAlignment(TextAlignment.CENTER);
        at.addRule();

        int i = 1;
        for (Item e : listOfItems) {
            AT_Row row = at.addRow(i, e.getProduct().getId(), e.getProduct().getTitle(), e.getQty(),
                    e.getProduct().getPrice(), e.getProduct().getPrice() * e.getQty());

            //Alignment
            row.getCells().get(0).getContext().setTextAlignment(TextAlignment.RIGHT);
            row.getCells().get(3).getContext().setTextAlignment(TextAlignment.CENTER);
            row.getCells().get(4).getContext().setTextAlignment(TextAlignment.RIGHT);
            row.getCells().get(5).getContext().setTextAlignment(TextAlignment.RIGHT);

            i++;
        }
        at.addRule();
        System.out.println(at.render());

    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public double getSubTotal() {
        return subTotal;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "subTotal=" + subTotal +
                "} " + super.toString();
    }
}
