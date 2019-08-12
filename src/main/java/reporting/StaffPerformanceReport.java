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
        j.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD, 14));
        j.setFont(new Font("Times New Roman",Font.BOLD,12));
        j.setBounds(30, 300, 300, 30);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);

        JLabel label1 = new JLabel(new ImageIcon("C:\\Users\\User\\Desktop\\HOLY.jpeg"));
        label1.setPreferredSize(new Dimension(200,200));
        JLabel label2 = new JLabel("HOLY Fast Food Restaurant");
        JLabel label3 = new JLabel("No. 1 & 2 Jalan 54, Desa Jaya, 52100, Kepong, Selangor, Malaysia");

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Staff Performance Report", TitledBorder.CENTER, TitledBorder.BOTTOM));

        label2.setBounds(692,100, 180,180);
        label3.setBounds(607,112, 400,180);

        f.add(label2);
        f.add(label3);

        panel.add(label1);

        f.add(panel, BorderLayout.NORTH);

        f.add(sp);
        // Frame Size
        f.setSize(500, 200);
        // Frame Visible = true
        f.setVisible(true);
    }
}
