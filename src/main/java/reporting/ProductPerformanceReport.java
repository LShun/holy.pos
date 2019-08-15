package reporting;

import food_menu.FoodMenu;
import food_menu.Product;
import order.Item;
import order.Order;
import order.Receipt;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;


public class ProductPerformanceReport {

    // frame
    JFrame f;
    // Table
    JTable j;

    // Constructor
    public ProductPerformanceReport()
    {
        ArrayList<Product> products= FoodMenu.getProducts();
        ArrayList<Receipt> receipt = Order.getReceiptList();

        // Frame initiallization
        f = new JFrame();

        // Frame Title
        f.setTitle("Product Performance Report");

        // Data to be displayed in the JTable

        ArrayList<Object[]> rowData = new ArrayList<Object[]>();

//        ArrayList<Product> p = FoodMenu.getProducts();
        ArrayList<Item> item = new ArrayList<Item>();
//        for(int i = 0;i < p.size(); i++){
//            item.add(new Item(p.get(i),0));
//        }

        for(int i = 0; i < receipt.size(); i++){

            int sizeOfListOfItems = receipt.get(i).getListOfItems().size();

            for(int j = 0;j < sizeOfListOfItems ; j++){
                Item itemInReceipt = receipt.get(i).getListOfItems().get(j); //Get the product object from the receipt
                int index = item.indexOf(itemInReceipt); //Find the position of the item
                if(index == -1){
                    item.add(new Item(itemInReceipt.getProduct(),0));
                    index = item.indexOf(itemInReceipt); //Find the position of the item
                }
                Item itemInFoodGroup = item.get(index);  //Get the product from the item ArrayList
                itemInFoodGroup.changeQtyBy(receipt.get(i).getListOfItems().get(j).getQty()); //Increase the amount
            }
        }

        Vector<String> columnNames = new Vector<String>();

        //columnNames.add("Month");
        columnNames.add("Product ID");
        columnNames.add("Product Name");
        columnNames.add("Quantity");
        columnNames.add("Price(RM)");
        columnNames.add("Total Sales(RM)");

        Vector<Vector> allRowData = new Vector<Vector>();
        for(int i = 0;i < item.size(); i++){
            Vector<Object> temp = new Vector<Object>();

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

        JLabel label = new JLabel() ;

        label.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\User\\Desktop\\HOLY.jpeg").getImage().getScaledInstance(110, 110, Image.SCALE_AREA_AVERAGING)));

        JLabel label2 = new JLabel("HOLY Fast Food Restaurant");
        JLabel label3 = new JLabel("No. 1 & 2 Jalan 54, Desa Jaya, 52100, Kepong, Selangor, Malaysia");
        JLabel label4 = new JLabel("Date Generated: " + LocalDate.now());

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Product Performance Report", TitledBorder.CENTER, TitledBorder.BOTTOM));
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
