package reporting;

import food_menu.FoodMenu;
import food_menu.Product;
import order.Order;
import order.Receipt;

import javax.swing.*;
import java.util.ArrayList;

public class DailySalesReport {


    // frame
    JFrame f;
    // Table
    JTable j;

    // Constructor
    public DailySalesReport() {
        ArrayList<Receipt> receipt = Order.getReceiptList();
        ArrayList<Product> products = FoodMenu.getProducts();
        // Frame initiallization
        f = new JFrame();

        // Frame Title
        f.setTitle("Daily Sales Report");

        // Data to be displayed in the JTable
        String[][] data = new String[][]{
                new String[]{String.valueOf(receipt.get(0).getTransactionTime()), receipt.get(0).getBillID(), String.valueOf(receipt.get(0).getListOfItems().get(0).getProduct().getTitle()), String.valueOf(receipt.get(0).getListOfItems().get(0).getProduct().getPrice()), String.valueOf(receipt.get(0).getListOfItems().get(0).getQty()), String.valueOf(receipt.get(0).getListOfItems().get(0).getProduct().getTax()), String.valueOf(receipt.get(0).getTotal())},
                new String[]{String.valueOf(receipt.get(1).getTransactionTime()), receipt.get(1).getBillID(), String.valueOf(receipt.get(1).getListOfItems().get(1).getProduct().getTitle()), String.valueOf(receipt.get(1).getListOfItems().get(1).getProduct().getPrice()), String.valueOf(receipt.get(1).getListOfItems().get(1).getQty()), String.valueOf(receipt.get(1).getListOfItems().get(1).getProduct().getTax()), String.valueOf(receipt.get(1).getTotal())},
                //new String[]{String.valueOf(receipt.get(2).getTransactionTime()), receipt.get(2).getBillID(), String.valueOf(receipt.get(2).getListOfItems().get(2).getProduct().getTitle()), String.valueOf(receipt.get(2).getListOfItems().get(2).getProduct().getPrice()), String.valueOf(receipt.get(2).getListOfItems().get(2).getQty()), String.valueOf(receipt.get(2).getListOfItems().get(2).getProduct().getTax()), String.valueOf(receipt.get(2).getTotal())},

                {"Total Sales per day", "", "", "", "", "1.2", "20"}
        };

        // Column Names
        String[] columnNames = { "Date", "Bill ID", "Product Name","Price(RM)","Quantity","Tax Amount(RM)","Amount(RM)"};

        // Initializing the JTable
        j = new JTable(data, columnNames);
        j.setBounds(30, 200, 200, 30);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
        // Frame Size
        f.setSize(500, 200);
        // Frame Visible = true
        f.setVisible(true);

        System.out.println(String.valueOf(receipt.get(1).getListOfItems().get(1).getQty()));
    }


    public void arrayList(ArrayList<Receipt> receipt){

        System.out.println(receipt.get(1).getListOfItems().get(1).getProduct());

    }




}
