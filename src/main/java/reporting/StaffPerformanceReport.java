package reporting;

import staff.Staff;

import javax.swing.*;
import java.util.ArrayList;

public class StaffPerformanceReport {

    // frame
    JFrame f;
    // Table
    JTable j;

    public StaffPerformanceReport()
    {
        ArrayList<Staff> staff= Staff.employeeList;
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

        ArrayList<Object[]> rowData = new ArrayList<Object[]>();
        for(int i = 0; i < staff.size(); i++){
            //int sizeOfListOfItems = staff.get(i)

                Object[] emp = new Object[5];

                emp[0] = staff.get(i).getDateOfEmployed();
                emp[1] = staff.get(i).getStaffID();
                emp[2] = staff.get(i).getName();
                emp[3] = staff.get(i).getTotalDurationWorked();
                emp[4] = staff.get(i).getSalesReceived();

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
