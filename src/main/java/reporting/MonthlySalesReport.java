package reporting;

import order.Order;
import order.Receipt;

import javax.swing.*;
import java.util.ArrayList;

public class MonthlySalesReport {

    // frame
    JFrame f;
    // Table
    JTable j;

    // Constructor
    public MonthlySalesReport()
    {
        ArrayList<Receipt> receipt = Order.getReceiptList();
        // Frame initiallization
        f = new JFrame();

        // Frame Title
        f.setTitle("Monthly Sales Report");

        // Data to be displayed in the JTable
        /*String[][] data = {
                { "14 July 2019", "A001", "Fried Chicken","5","2","0.6","10" },
                { "15 July 2019", "A002", "French Fries","5","2","0.6","10" },
                {"Total Sales per month","","","","","1.2","20"}
        };*/


        ArrayList<Object[]> rowData = new ArrayList<Object[]>();
        for(int i = 0; i < receipt.size(); i++){
            int sizeOfListOfItems = receipt.get(i).getListOfItems().size();

            Object[] temp = new Object[7];
            temp[0] = receipt.get(i).getTransactionTime().toString();
            //temp[1] = receipt.get(i).getBillID();

            for(int j = 0;j < sizeOfListOfItems ; j++){

                temp[1]=receipt.get(i).getListOfItems().get(j).getProduct().getId();
                temp[2] = receipt.get(i).getListOfItems().get(j).getProduct().getTitle();
                temp[3] = receipt.get(i).getListOfItems().get(j).getProduct().getPrice();
                temp[4] = receipt.get(i).getListOfItems().get(j).getQty();
                temp[5] = receipt.get(i).getListOfItems().get(j).getProduct().getTax();
                temp[6] = receipt.get(i).getListOfItems().get(j).getQty()*receipt.get(i).getListOfItems().get(j).getProduct().getPrice();
                rowData.add(temp);

                temp = new Object[7];
                temp[0] = "";
                //temp[1] = "";
            }

            temp[0] = "Total Amount";
            temp[2] = temp[3] = temp[4] = temp[5] = "";
            temp[6] = receipt.get(i).getTotal();
            rowData.add(temp);

            temp = new Object[7];
            temp[0] = temp[6] = "";
            rowData.add(temp);
        }

        Object[][] realRowData = new Object[rowData.size()][];
        for(int i = 0; i < rowData.size(); i++){
            realRowData[i] = rowData.get(i);
        }

        // Column Names
        String[] columnNames = { "Date(Month)", "Product ID", "Product Name","Price(RM)","Quantity","Tax Amount(RM)","Amount(RM)"};

        // Initializing the JTable
        j = new JTable(realRowData, columnNames);
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
