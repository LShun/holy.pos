package reporting;

import food_menu.FoodMenu;
import food_menu.Product;
import order.Order;
import order.Receipt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class DailySalesReport {


    // frame
    JFrame f;
    // Table
    JTable j;

    // Constructor
    public DailySalesReport() {
        ArrayList<Receipt> receipt = Order.getReceiptList();

        // Frame initiallization
        f = new JFrame();

        // Frame Title
        f.setTitle("Daily Sales Report");



        // Data to be displayed in the JTable
       /* String[][] data = new String[][]{
                new String[]{String.valueOf(receipt.get(0).getTransactionTime()), receipt.get(0).getBillID(), String.valueOf(receipt.get(0).getListOfItems().get(0).getProduct().getTitle()), String.valueOf(receipt.get(0).getListOfItems().get(0).getProduct().getPrice()), String.valueOf(receipt.get(0).getListOfItems().get(0).getQty()), String.valueOf(receipt.get(0).getListOfItems().get(0).getProduct().getTax()), String.valueOf(receipt.get(0).getTotal())},
                new String[]{String.valueOf(receipt.get(1).getTransactionTime()), receipt.get(1).getBillID(), String.valueOf(receipt.get(1).getListOfItems().get(1).getProduct().getTitle()), String.valueOf(receipt.get(1).getListOfItems().get(1).getProduct().getPrice()), String.valueOf(receipt.get(1).getListOfItems().get(1).getQty()), String.valueOf(receipt.get(1).getListOfItems().get(1).getProduct().getTax()), String.valueOf(receipt.get(1).getTotal())},
                //new String[]{String.valueOf(receipt.get(2).getTransactionTime()), receipt.get(2).getBillID(), String.valueOf(receipt.get(2).getListOfItems().get(2).getProduct().getTitle()), String.valueOf(receipt.get(2).getListOfItems().get(2).getProduct().getPrice()), String.valueOf(receipt.get(2).getListOfItems().get(2).getQty()), String.valueOf(receipt.get(2).getListOfItems().get(2).getProduct().getTax()), String.valueOf(receipt.get(2).getTotal())},

                {"Total Sales per day", "", "", "", "", "1.2", "20"}
        };*/



        ArrayList<Object[]> rowData = new ArrayList<Object[]>();
        for(int i = 0; i < receipt.size(); i++){
            int sizeOfListOfItems = receipt.get(i).getListOfItems().size();

            for(int j = 0;j < sizeOfListOfItems ; j++){
                Object[] temp = new Object[7];

                temp[0] = receipt.get(i).getTransactionTime().toString();
                temp[1] = receipt.get(i).getBillID();
                temp[2] = receipt.get(i).getListOfItems().get(j).getProduct().getTitle();
                temp[3] = receipt.get(i).getListOfItems().get(j).getProduct().getPrice();
                temp[4] = receipt.get(i).getListOfItems().get(j).getQty();
                temp[5] = receipt.get(i).getListOfItems().get(j).getProduct().getTax();
                temp[6] = receipt.get(i).getTotal();

                rowData.add(temp);

            }

        }

        Object[][] realRowData = new Object[rowData.size()][];
        for(int i = 0; i < rowData.size(); i++){
            realRowData[i] = rowData.get(i);
        }



        // Column Names
        String[] columnNames = { "Date", "Bill ID", "Product Name","Price(RM)","Quantity","Tax Amount(RM)","Amount(RM)"};

        //addRowToJTable();

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
