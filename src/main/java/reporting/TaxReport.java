package reporting;
import order.Order;
import order.Receipt;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
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
    double sum=0;
    for(int i = 0; i < receipt.size(); i++){
        int sizeOfListOfItems = receipt.get(i).getListOfItems().size();

        Object[] tax = new Object[4];
        tax[0] = receipt.get(i).getBillID();
        tax[1] = receipt.get(i).getTransactionTime().toString();
        tax[2] = receipt.get(i).getTax();
        tax[3] = receipt.get(i).getTotal();

        rowData.add(tax);

        sum+=receipt.get(i).getTax();

    }

    for(int i=0; i<1;i++){

        Object[] title = new Object[4];
        title[0] = "";
        title[1] = "";
        title[2]="Total Tax Amount:";
        title[3]="Total Bill Amount:";
        rowData.add(title);

        Object[] total=new Object[4];

        total[0]=total[1]="";
        total[2]=sum;
        total[3]="";
        rowData.add(total);

    }


    Object[][] realRowData = new Object[rowData.size()][];
    for(int i = 0; i < rowData.size(); i++){
        realRowData[i] = rowData.get(i);
    }




    // Column Names
    String[] columnNames = { "Bill ID","Date","Tax Amount(RM)", "Base Amount(RM)"};

    // Initializing the JTable
    j = new JTable(realRowData, columnNames);
    j.setBounds(30, 200, 200, 30);

    // adding it to JScrollPane
    JScrollPane sp = new JScrollPane(j);

    JLabel label = new JLabel(new ImageIcon("C:\\Users\\User\\Desktop\\OOPT.jpeg"));
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Tax Report", TitledBorder.CENTER, TitledBorder.BOTTOM));
    label.setBounds(0,0, 454,388);
    panel.add(label);
    f.add(panel, BorderLayout.NORTH);

    f.add(sp);
    // Frame Size
    f.setSize(500, 200);
    // Frame Visible = true
    f.setVisible(true);

}
}