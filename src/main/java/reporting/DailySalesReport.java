package reporting;

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
    public DailySalesReport()
    {
        ArrayList<Receipt> receipt= Order.getReceiptList();
        // Frame initiallization
        f = new JFrame();

        // Frame Title
        f.setTitle("Daily Sales Report");

        // Data to be displayed in the JTable
        String[][] data = new String[][]{
                new String[]{receipt.get(0).toString(), receipt.get(0).getBillID(), String.valueOf(receipt.get(0).getListOfItems()), "5", "2","", String.valueOf(receipt.get(0).getTotal())},
                {receipt.get(1).toString(), receipt.get(1).getBillID(), "French Fries", "5", "3", "1.20", "5"},
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
    }
}
