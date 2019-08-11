package reporting;

import staff.Staff;
import staff.Worker;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class StaffPerformanceReport {

    // frame
    JFrame f;
    // Table
    JTable j;

    public StaffPerformanceReport()
    {
        ArrayList<Worker> workers = Staff.getEmployeeList();
        // Frame initiallization
        f = new JFrame();

        // Frame Title
        f.setTitle("Staff Performance Report");

        // Data to be displayed in the JTable
       /* String[][] data = {
                { "14 July 2019", "A001", "Fried Chicken","5","10","50" },
                { "14 July 2019", "A002", "French Fries","5","10","50" },
                {"Total","","","10","20","100"}
        };*/

        ArrayList<Object[]> rowData = new ArrayList<Object[]>();
        for(int i = 0; i < workers.size(); i++){
            //int sizeOfListOfItems = workers.get(i)

                Object[] emp = new Object[5];

                emp[0] = workers.get(i).getDateOfEmployed();
                emp[1] = workers.get(i).getStaffID();
                emp[2] = workers.get(i).getName();
                emp[3] = workers.get(i).getTotalDurationWorked();
                emp[4] = workers.get(i).getSalesReceived();

                rowData.add(emp);


        }

        Object[][] realRowData = new Object[rowData.size()][];
        for(int i = 0; i < rowData.size(); i++){
            realRowData[i] = rowData.get(i);
        }

        // Column Names
        String[] columnNames = { "Date", "Staff ID", "Staff Name","Working hours","Total Sales Received(RM)"};

        // Initializing the JTable
        j = new JTable(realRowData, columnNames);
        j.setBounds(30, 300, 300, 30);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);

        JLabel label = new JLabel(new ImageIcon("C:\\Users\\User\\Desktop\\OOPT.jpeg"));
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Staff Performance Report", TitledBorder.CENTER, TitledBorder.BOTTOM));
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
