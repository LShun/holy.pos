package reporting;

import order.Order;
import order.Receipt;
import javax.swing.*;
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
        for(int i = 0; i < receipt.size(); i++){
            if(day!=receipt.get(i).getTransactionTime().getDayOfMonth()||month!=receipt.get(i).getTransactionTime().getMonthValue())
                continue;

            int sizeOfListOfItems = receipt.get(i).getListOfItems().size();

            Object[] temp = new Object[6];
            temp[0] = receipt.get(i).getTransactionTime().toString();
            temp[1] = receipt.get(i).getBillID();

            for(int j = 0;j < sizeOfListOfItems ; j++){

                temp[2] = receipt.get(i).getListOfItems().get(j).getProduct().getTitle();
                temp[3] = receipt.get(i).getListOfItems().get(j).getProduct().getPrice();
                temp[4] = receipt.get(i).getListOfItems().get(j).getQty();
                //temp[5] = receipt.get(i).getListOfItems().get(j).getProduct().getTax();
                temp[5] = receipt.get(i).getListOfItems().get(j).getQty()*receipt.get(i).getListOfItems().get(j).getProduct().getPrice();
                rowData.add(temp);

                temp = new Object[6];
                temp[0] = "";
                temp[1] = "";
            }

            temp[0] = "";
            temp[2] = temp[3] ="";
            temp[4]= "Total Tax";
            temp[5] = receipt.get(i).getTax();

            //System.out.println(receipt.get(i).getTotal());
            rowData.add(temp);

            temp = new Object[6];
            temp[0] = "";
            temp[4] = "Total Amount";
            temp[5] = receipt.get(i).getTotal();
            rowData.add(temp);

            temp = new Object[6];
            temp[0] = temp[5] = "";
            rowData.add(temp);
        }

        Object[][] realRowData = new Object[rowData.size()][];
        for(int i = 0; i < rowData.size(); i++){
            realRowData[i] = rowData.get(i);
        }



        // Column Names
        String[] columnNames = { "Date", "Bill ID", "Product Name","Price(RM)","Quantity","Amount(RM)"};

        //addRowToJTable();

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
