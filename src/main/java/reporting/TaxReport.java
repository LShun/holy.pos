package reporting;
import order.Order;
import order.Receipt;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.ArrayList;


public class TaxReport {
    // frame
    JFrame f;
    // Table
    JTable j;


// Constructor
public TaxReport()
{
    ArrayList<Receipt> receipt = Order.getReceiptList();

    // Frame initiallization
    f = new JFrame();

    // Frame Title
    f.setTitle("Tax Report");

    // Data to be displayed in the JTable
    /*String[][] data = {
            { "INV001", "0.60", "10","14 July 2019","" },
            { "INV002", "0.90", "15","14 July 2019","" },
            {"Total Tax","","","","1.50"}
    };*/

    ArrayList<Object[]> rowData = new ArrayList<Object[]>();
    for(int i = 0; i < receipt.size(); i++){
        int sizeOfListOfItems = receipt.get(i).getListOfItems().size();

        Object[] tax = new Object[6];
        tax[0] = receipt.get(i).getBillID();
        tax[1] = receipt.get(i).getTransactionTime().toString();

        for(int j = 0;j < sizeOfListOfItems ; j++){

            tax[2] = receipt.get(i).getListOfItems().get(j).getQty();
            tax[3] = receipt.get(i).getListOfItems().get(j).getProduct().getTax();
            tax[4] = receipt.get(i).getTotal();
            tax[5] = "";

            rowData.add(tax);

            tax = new Object[7];
            tax[0] = "";
            tax[1] = "";
            tax[2] = "";
            tax[3] = receipt.get(i).getListOfItems().get(j).getProduct().getTax()*receipt.get(i).getListOfItems().get(j).getQty();

        }
        tax[0] = "Total Tax";


        tax[4] = tax[5] = "";


        rowData.add(tax);

        tax = new Object[6];
        tax[0] = tax[5] = "";
        rowData.add(tax);

    }

    Object[][] realRowData = new Object[rowData.size()][];
    for(int i = 0; i < rowData.size(); i++){
        realRowData[i] = rowData.get(i);
    }




    // Column Names
    String[] columnNames = { "Bill ID","Date","Quantity of products","Tax Amount(RM)", "Base Amount(RM)","References"};

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