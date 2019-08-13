package reporting;
import order.Order;
import order.Receipt;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static pub.FormatPrint.printHeader;

public class MonthlySalesReport {

    // frame
    JFrame f;
    // Table
    JTable j;

    // Constructor
    public MonthlySalesReport() {
        ArrayList<Receipt> receipt = Order.getReceiptList();
        // Frame initiallization
        f = new JFrame();

        // Frame Title
        f.setTitle("Monthly Sales Report");

        Scanner scanner=new Scanner(System.in);
        int month;


        printHeader("MONTHLY SALES REPORT");
        System.out.print("1.January\n"+
                "2.February\n"+
                "3.March\n"+
                "4.April\n"+
                "5.May\n"+
                "6.June\n"+
                "7.July\n"+
                "8.August\n"+
                "9.September\n"+
                "10.October\n"+
                "11.November\n"+
                "12.December\n\n"+

                "Please enter month of report for viewing: ");
        month=scanner.nextInt();


        ArrayList<Object[]> rowData = new ArrayList<Object[]>();
        double sum=0, tax=0;
        for(int i = 0; i < receipt.size(); i++){
            if(month!=receipt.get(i).getTransactionTime().getMonthValue())
                continue;

            int sizeOfListOfItems = receipt.get(i).getListOfItems().size();

            Object[] temp = new Object[4];
            temp[0] = receipt.get(i).getTransactionTime().getYear()+" "+receipt.get(i).getTransactionTime().getMonthValue();
            temp[1] = receipt.get(i).getBillID();
            temp[2] = receipt.get(i).getTax();
            temp[3] = receipt.get(i).getTotal();

            sum+=receipt.get(i).getTotal().doubleValue();
            tax+=receipt.get(i).getTax().doubleValue();

            rowData.add(temp);

        }

        for(int i=0;i<1;i++){

            Object[] title = new Object[4];
            title[0] = "";
            title[1] = "";
            title[2]="Total Tax Amount:";
            title[3]="Total Bill Amount:";
            rowData.add(title);

            Object[] total=new Object[4];

            total[0]=total[1]="";
            total[2]=tax;
            total[3]=sum;
            rowData.add(total);
        }

        Object[][] realRowData = new Object[rowData.size()][];
        for(int i = 0; i < rowData.size(); i++){
            realRowData[i] = rowData.get(i);
        }

        // Column Names
        String[] columnNames = { "Date(Month)", "Bill ID","Tax Amount(RM)","Bill Amount(RM)"};

        // Initializing the JTable
        j = new JTable(realRowData, columnNames);
        j.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD, 14));
        j.setFont(new Font("Times New Roman",Font.BOLD,12));
        j.setBounds(30, 200, 200, 30);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);

        JLabel label = new JLabel() ;

        label.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\User\\Desktop\\HOLY.jpeg").getImage().getScaledInstance(110, 110, Image.SCALE_AREA_AVERAGING)));

        JLabel label2 = new JLabel("HOLY Fast Food Restaurant");
        JLabel label3 = new JLabel("No. 1 & 2 Jalan 54, Desa Jaya, 52100, Kepong, Selangor, Malaysia");
        JLabel label4 = new JLabel("Date Generated: " + LocalDate.now());

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Monthly Sales Report", TitledBorder.CENTER, TitledBorder.BOTTOM));
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
        f.setSize(500, 200);
        // Frame Visible = true
        f.setVisible(true);
    }
}
