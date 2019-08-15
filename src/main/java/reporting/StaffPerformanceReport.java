package reporting;

import staff.Staff;
import staff.Worker;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDate;
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

        ArrayList<Object[]> rowData = new ArrayList<Object[]>();
        for(int i = 0; i < workers.size(); i++){
            //int sizeOfListOfItems = workers.get(i)

                Object[] emp = new Object[5];

                emp[0] = workers.get(i).getDateOfEmployed();
                emp[1] = workers.get(i).getStaffID();
                emp[2] = workers.get(i).getName();
                emp[3] = workers.get(i).getTotalDurationWorked().toHours();
                emp[4] = workers.get(i).getSalesReceived();

                rowData.add(emp);


        }

        Object[][] realRowData = new Object[rowData.size()][];
        for(int i = 0; i < rowData.size(); i++){
            realRowData[i] = rowData.get(i);
        }

        // Column Names
        String[] columnNames = { "Date", "Staff ID", "Staff Name","Working hours(Hours)","Total Sales Received(RM)"};

        // Initializing the JTable
        j = new JTable(realRowData, columnNames);
        j.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD, 14));

        j.setFont(new Font("Times New Roman",Font.BOLD,12));
        j.setBounds(30, 300, 300, 30);
        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);

        JLabel label = new JLabel() ;

        label.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\User\\Desktop\\HOLY.jpeg").getImage().getScaledInstance(110, 110, Image.SCALE_AREA_AVERAGING)));

        JLabel label2 = new JLabel("HOLY Fast Food Restaurant");
        JLabel label3 = new JLabel("No. 1 & 2 Jalan 54, Desa Jaya, 52100, Kepong, Selangor, Malaysia");
        JLabel label4 = new JLabel("Date Generated: " + LocalDate.now());

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Staff Performance Report", TitledBorder.CENTER, TitledBorder.BOTTOM));
        panel.setPreferredSize(new Dimension(215,215));

        label2.setBounds(688,45, 180,180);
        label3.setBounds(607,60, 400,180);
        label4.setBounds(1375,100,300,180);

        f.add(label2);
        f.add(label3);
        f.add(label4);

        panel.add(label);

        f.add(panel, BorderLayout.NORTH);

        f.add(sp);
        // Frame Size
        f.setSize(800, 300);
        // Frame Visible = true
        f.setVisible(true);
    }
}
