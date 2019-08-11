package reporting;

import order.Order;
import order.Receipt;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DailySalesReport {


    // frame
    JFrame f;
    // Table
    JTable j;

    // Constructor
    public DailySalesReport() {
        ArrayList<Receipt> receipt = Order.getReceiptList();

        // Frame initiallization
        f = new JFrame();

        // Frame Title
        f.setTitle("Daily Sales Report");

        Scanner s=new Scanner(System.in);
        int day,month;

        System.out.print("Please enter the day of report for viewing(DD): ");
        day=s.nextInt();
        System.out.print("Please enter the month of report for viewing(MM): ");
        month=s.nextInt();

        ArrayList<Object[]> rowData = new ArrayList<Object[]>();
        double sum=0,tax=0;
        for(int i = 0; i < receipt.size(); i++){
            if(day!=receipt.get(i).getTransactionTime().getDayOfMonth()||month!=receipt.get(i).getTransactionTime().getMonthValue())
                continue;

            int sizeOfListOfItems = receipt.get(i).getListOfItems().size();

            Object[] temp = new Object[4];
            temp[0] = receipt.get(i).getTransactionTime().toString();
            temp[1] = receipt.get(i).getBillID();
            temp[2] = receipt.get(i).getTax();
            temp[3]=receipt.get(i).getTotal();

            sum=sum+receipt.get(i).getTotal();
            tax=tax+receipt.get(i).getTax();

            rowData.add(temp);
        }



        for(int i=0; i<1;i++){

           Object[] total=new Object[4];

            total[0]=total[1]="";
            total[2]="Total Tax Amount : " +tax;
            total[3]="Total Bill Amount : " +sum;
            rowData.add(total);

        }


        //rowData.add();

        Object[][] realRowData = new Object[rowData.size()][];
        for(int i = 0; i < rowData.size(); i++){
            realRowData[i] = rowData.get(i);
        }



        // Column Names
        String[] columnNames = { "Date", "Bill ID","Tax Amount","Bill Amount(RM)"};

        //addRowToJTable();

        // Initializing the JTable
        j = new JTable(realRowData, columnNames);
        j.setBounds(30, 200, 200, 30);

        // adding it to JScrollPane

        JScrollPane sp = new JScrollPane(j);

        JLabel label = new JLabel(new ImageIcon("C:\\Users\\User\\Desktop\\OOPT.jpeg"));
        JPanel panel = new JPanel();
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
