package reporting;

import javax.swing.*;

public class MonthlySalesReport {

    // frame
    JFrame f;
    // Table
    JTable j;

    // Constructor
    public MonthlySalesReport()
    {
        // Frame initiallization
        f = new JFrame();

        // Frame Title
        f.setTitle("Monthly Sales Report");

        // Data to be displayed in the JTable
        String[][] data = {
                { "14 July 2019", "A001", "Fried Chicken","5","2","0.6","10" },
                { "15 July 2019", "A002", "French Fries","5","2","0.6","10" },
                {"Total Sales per month","","","","","1.2","20"}
        };

        // Column Names
        String[] columnNames = { "Date", "Product ID", "Product Name","Price(RM)","Quantity","Tax Amount(RM)","Amount(RM)"};

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
