package reporting;

import order.Order;
import order.Receipt;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

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

        System.out.println("\n\n1.January\n"+
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
        for(int i = 0; i < receipt.size(); i++){
            if(month!=receipt.get(i).getTransactionTime().getMonthValue())
                continue;


            int sizeOfListOfItems = receipt.get(i).getListOfItems().size();


            Object[] temp = new Object[7];
            temp[0] = receipt.get(i).getTransactionTime();
            //temp[1] = receipt.get(i).getBillID();

            for(int j = 0;j < sizeOfListOfItems ; j++){

                temp[1]=receipt.get(i).getListOfItems().get(j).getProduct().getId();
                temp[2] = receipt.get(i).getListOfItems().get(j).getProduct().getTitle();
                temp[3] = receipt.get(i).getListOfItems().get(j).getProduct().getPrice();
                temp[4] = receipt.get(i).getListOfItems().get(j).getQty();
                temp[5] = receipt.get(i).getListOfItems().get(j).getProduct().getTax();
                temp[6] = receipt.get(i).getListOfItems().get(j).getQty()*receipt.get(i).getListOfItems().get(j).getProduct().getPrice();
                rowData.add(temp);

                temp = new Object[7];
                temp[0] = "";
                //temp[1] = "";
            }

            temp[0] = "Total Amount";
            temp[2] = temp[3] = temp[4] = temp[5] = "";
            temp[6] = receipt.get(i).getTotal();
            rowData.add(temp);

            temp = new Object[7];
            temp[0] = temp[6] = "";
            rowData.add(temp);
        }

        Object[][] realRowData = new Object[rowData.size()][];
        for(int i = 0; i < rowData.size(); i++){
            realRowData[i] = rowData.get(i);
        }

        // Column Names
        String[] columnNames = { "Date(Month)", "Product ID", "Product Name","Price(RM)","Quantity","Tax Amount(RM)","Amount(RM)"};

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
