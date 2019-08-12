package reporting;

import food_menu.FoodMenu;
import food_menu.Product;
import order.Item;
import order.Order;
import order.Receipt;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import static pub.FormatPrint.printHeader;


public class ProductPerformanceReport {

    // frame
    JFrame f;
    // Table
    JTable j;

    // Constructor
    public ProductPerformanceReport()
    {
        ArrayList<Product> products=FoodMenu.getProducts();
        ArrayList<Receipt> receipt = Order.getReceiptList();

        // Frame initiallization
        f = new JFrame();

        // Frame Title
        f.setTitle("Product Performance Report");

        Scanner scanner=new Scanner(System.in);
        int month;

        printHeader("PRODUCT PERFORMANCE REPORT");
        System.out.print("\n\n1.January\n"+
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

        // Data to be displayed in the JTable

        ArrayList<Object[]> rowData = new ArrayList<Object[]>();

        ArrayList<Product> p = FoodMenu.getProducts();
        ArrayList<Item> item = new ArrayList<Item>();
        for(int i = 0;i < p.size(); i++){
            item.add(new Item(p.get(i),0));
        }

        for(int i = 0; i < receipt.size(); i++){
            if(month != receipt.get(i).getTransactionTime().getMonthValue())
                continue;

            int sizeOfListOfItems = receipt.get(i).getListOfItems().size();

            for(int j = 0;j < sizeOfListOfItems ; j++){

                Item itemInReceipt = receipt.get(i).getListOfItems().get(j); //Get the product object from the receipt
                int index = item.indexOf(itemInReceipt); //Find the position of the item
                Item itemInFoodGroup = item.get(index);  //Get the product from the item ArrayList
                itemInFoodGroup.increment(receipt.get(i).getListOfItems().get(j).getQty()); //Increase the amount
            }
        }

        // Column Names
//        String[] columnNames = { "Date(month)", "Product ID", "Product Name","Quantity","Price(RM)","Total Sales(RM)"};
        Vector columnNames = new Vector();
        columnNames.add("Month");
        columnNames.add("Product ID");
        columnNames.add("Product Name");
        columnNames.add("Quantity");
        columnNames.add("Price(RM)");
        columnNames.add("Total Sales(RM)");

        Vector<Vector> allRowData = new Vector<Vector>();
        for(int i = 0;i < item.size(); i++){
            Vector temp = new Vector<Vector>();

            temp.add(month);
            temp.add(item.get(i).getProduct().getId());
            temp.add(item.get(i).getProduct().getTitle());
            temp.add(item.get(i).getQty());
            temp.add(item.get(i).getProduct().getPrice());
            temp.add(String.format("%.2f",item.get(i).getProduct().getPrice() * item.get(i).getQty()));

            allRowData.add(temp);
        }
        // Initializing the JTable
        //j = new JTable(realRowData, columnNames);
        j = new JTable(allRowData, columnNames);
        j.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD, 14));
        j.setFont(new Font("Times New Roman",Font.BOLD,12));
        j.setBounds(30, 200, 200, 30);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(j);

        JLabel label1 = new JLabel(new ImageIcon("C:\\Users\\User\\Desktop\\HOLY.jpeg"));
        label1.setPreferredSize(new Dimension(200,200));
        JLabel label2 = new JLabel("HOLY Fast Food Restaurant");
        JLabel label3 = new JLabel("No. 1 & 2 Jalan 54, Desa Jaya, 52100, Kepong, Selangor, Malaysia");

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Product Performance Report", TitledBorder.CENTER, TitledBorder.BOTTOM));


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
