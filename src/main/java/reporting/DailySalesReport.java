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
        ArrayList<Product> products = FoodMenu.getProducts();
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

      // String[][]data= addRowToJTable();

        // Column Names
        String[] columnNames = { "Date", "Bill ID", "Product Name","Price(RM)","Quantity","Tax Amount(RM)","Amount(RM)"};

        //addRowToJTable();

        // Initializing the JTable
        j = new JTable(addRowToJTable(), columnNames);
        j.setBounds(30, 200, 200, 30);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);
        f.add(sp);
        // Frame Size
        f.setSize(500, 200);
        // Frame Visible = true
        f.setVisible(true);
    }


    public String[][] addRowToJTable()
    {
        DefaultTableModel model= (DefaultTableModel) j.getModel();
        //ArrayList<User> list = ListUsers();

        ArrayList<Receipt> receipt = Order.getReceiptList();
        Object rowData[] = new Object[7];
        for(int i = 0; i < receipt.size(); i++)
        {
            rowData[0] =receipt.get(i).getTransactionTime();
            rowData[1]=receipt.get(i).getBillID();
            rowData[2]=receipt.get(i).getListOfItems().get(i).getProduct().getTitle();
            rowData[3]=receipt.get(i).getListOfItems().get(i).getProduct().getPrice();
            rowData[4]=receipt.get(i).getListOfItems().get(i).getQty();
            rowData[5]=receipt.get(i).getListOfItems().get(i).getProduct().getTax();
            rowData[6]=receipt.get(i).getTotal();

            model.addRow(rowData);
        }

        return (String[][]) rowData;
    }

}
