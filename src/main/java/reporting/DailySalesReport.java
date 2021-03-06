package reporting;

import order.Order;
import order.Receipt;

import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static pub.FormatPrint.printHeader;

public class DailySalesReport {


    // frame
    private JFrame f;
    // Table
    private JTable j;

    // Constructor
    public DailySalesReport() {
        ArrayList<Receipt> receipt = Order.getReceiptList();

        // Frame initiallization
        f = new JFrame();

        // Frame Title
        f.setTitle("Daily Sales Report");

        Scanner s = new Scanner(System.in);
        int day, month;

        printHeader("DAILY SALES REPORT");

        System.out.println("Daily Sales Report Date List");
        System.out.println("----------------------------");
        LocalDate current = null;
        for (int i = 0; i < receipt.size(); i++) {
            if (receipt.get(i).getTransactionTime().toLocalDate().equals(current))
                continue;
            current = receipt.get(i).getTransactionTime().toLocalDate();
            System.out.println(receipt.get(i).getTransactionTime().toLocalDate());
        }

        System.out.print("Please enter the day of report for viewing(DD): ");
        day = s.nextInt();
        System.out.print("Please enter the month of report for viewing(MM): ");
        month = s.nextInt();
        String date = String.format("%02d-%02d-%04d",day,month,2019);

        try{
        	LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }catch(Exception e){
        	System.out.println("Invalid Date!");
        	return;	
       	}

        ArrayList<Object[]> rowData = new ArrayList<Object[]>();
        double sum = 0, tax = 0;
        for (int i = 0; i < receipt.size(); i++) {
            if (day != receipt.get(i).getTransactionTime().getDayOfMonth() || month != receipt.get(i).getTransactionTime().getMonthValue())
                continue;

            int sizeOfListOfItems = receipt.get(i).getListOfItems().size();

            Object[] temp = new Object[4];
            temp[0] = receipt.get(i).getTransactionTime().toString();
            temp[1] = receipt.get(i).getBillID();
            temp[2] = receipt.get(i).getTax();
            temp[3] = receipt.get(i).getTotal();

            sum = sum + receipt.get(i).getTotal().doubleValue();
            tax = tax + receipt.get(i).getTax().doubleValue();

            rowData.add(temp);
        }

        for (int i = 0; i < 1; i++) {

            Object[] title = new Object[4];
            title[0] = "";
            title[1] = "";
            title[2] = "Total Tax Amount:";
            title[3] = "Total Bill Amount:";
            rowData.add(title);

            Object[] total = new Object[4];

            total[0] = total[1] = "";
            total[2] = tax;
            total[3] = sum;
            rowData.add(total);

        }

        Object[][] realRowData = new Object[rowData.size()][];
        for (int i = 0; i < rowData.size(); i++) {
            realRowData[i] = rowData.get(i);
        }

        // Column Names
        String[] columnNames = {"Date", "Bill ID", "Tax Amount", "Bill Amount(RM)"};

        //addRowToJTable();

        // Initializing the JTable
        j = new JTable(realRowData, columnNames);
        j.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 14));
        j.setFont(new Font("Times New Roman", Font.BOLD, 12));
        j.setBounds(30, 200, 200, 30);

        // adding it to JScrollPane

        JScrollPane sp = new JScrollPane(j);

        JLabel label = new JLabel();

		/*Place the HOLY KMB picture inside C:/Users/User/Desktop/holy.pos/src/main/java/reporting/HOLY.jpeg, 
		 *otherwise the report is still functional, but 
		 *picture will not be displayed */
        label.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\User\\Desktop\\holy.pos\\src\\main\\java\\reporting\\HOLY.jpeg").getImage().getScaledInstance(110, 110, Image.SCALE_AREA_AVERAGING)));

        JLabel label2 = new JLabel("HOLY Fast Food Restaurant");
        JLabel label3 = new JLabel("No. 1 & 2 Jalan 54, Desa Jaya, 52100, Kepong, Selangor, Malaysia");
        JLabel label4 = new JLabel("Date Generated: " + day + "-" + month + "-" + receipt.get(1).getTransactionTime().getYear());

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Daily Sales Report", TitledBorder.CENTER, TitledBorder.BOTTOM));
        panel.setPreferredSize(new Dimension(215, 215));

        label2.setBounds(888, 45, 180, 180);
        label3.setBounds(807, 60, 400, 180);
        label4.setBounds(1760, 100, 300, 180);


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
