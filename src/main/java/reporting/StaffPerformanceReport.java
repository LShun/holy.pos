package reporting;

import javax.swing.*;

public class StaffPerformanceReport {

    // frame
    JFrame f;
    // Table
    JTable j;

    public StaffPerformanceReport()
    {
        // Frame initiallization
        f = new JFrame();

        // Frame Title
        f.setTitle("Staff Performance Report");

        // Data to be displayed in the JTable
        String[][] data = {
                { "14 July 2019", "A001", "Fried Chicken","5","10","50" },
                { "14 July 2019", "A002", "French Fries","5","10","50" },
                {"Total","","","10","20","100"}
        };

        // Column Names
        String[] columnNames = { "Date", "Staff ID", "Staff Name","Working hours","Total Sales Received(RM)"};

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