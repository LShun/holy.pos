package reporting;

import food_menu.FoodMenu;
import food_menu.Product;
import order.Item;
import order.Order;
import order.Receipt;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;


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

        //String[] name={"Beefburger","Cheeseburger","Double cheeseburger","Chicken burger","Sausage Deluxe Breakfast","Burger Set Lunch"};

        /*ArrayList<Product> food=FoodMenu.getProducts();
        for(int i=0;i<food.size();i++){
            double[] total=new double[10];

            //int size = receipt.get(i).getListOfItems().size();

            for(int j=0;j<receipt.get(i).getListOfItems().size();j++)
            if(!(food.get(i).getId() == receipt.get(i).getListOfItems().get(j).getProduct().getId())){
                total[i]=receipt.get(i).getListOfItems().get(j).getQty()*receipt.get(i).getListOfItems().get(j).getProduct().getPrice();

            }

            System.out.println(food.get(i).getId()+"="+total[i]);

        }*/

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
//                Object[] product = new Object[6];
//
//                product[0]  = receipt.get(i).getTransactionTime().toString();
//                product[1] = receipt.get(i).getListOfItems().get(j).getProduct().getId();
//                //product[1] = products.get(i).getId();
//                product[2] = receipt.get(i).getListOfItems().get(j).getProduct().getTitle();
//                //product[2] = products.get(i).getTitle();
//                product[3] = receipt.get(i).getListOfItems().get(j).getQty();
//                product[4] = receipt.get(i).getListOfItems().get(j).getProduct().getPrice();
//                product[5] = receipt.get(i).getListOfItems().get(j).getProduct().getPrice()*receipt.get(i).getListOfItems().get(j).getQty();
//                //product[6] = receipt.get(i).getTotal();
//
//                rowData.add(product);

                Item itemInReceipt = receipt.get(i).getListOfItems().get(j); //Get the product object from the receipt
                int index = item.indexOf(itemInReceipt); //Find the position of the item
                Item itemInFoodGroup = item.get(index);  //Get the product from the item ArrayList
                itemInFoodGroup.increment(receipt.get(i).getListOfItems().get(j).getQty()); //Increase the amount
            }
        }

        /*for(int i=0;i<products.size();i++){
            int sizeOfListOfItems=products.size();

            for(int j=0;j<sizeOfListOfItems;i++){
                Object[] product=new Object[3];

                product[0]=products.get(i).getTitle();
                product[1]=products.get(i).getId();
                product[2]=products.get(i).getPrice();
                //product[3]=receipt.get(i).getListOfItems().get(j).getProduct().getPrice()*receipt.get(i).getListOfItems().get(j).getQty();

                rowData.add(product);
            }
        }*/

//        Object[][] realRowData = new Object[rowData.size()][];
//        for(int i = 0; i < rowData.size(); i++){
//            realRowData[i] = rowData.get(i);
//        }


        // Column Names
//        String[] columnNames = { "Date(month)", "Product ID", "Product Name","Quantity","Price(RM)","Total Sales(RM)"};
        Vector columnNames = new Vector();
        columnNames.add("Date(month)");
        columnNames.add("Product ID");
        columnNames.add("Product Name");
        columnNames.add("Quantity");
        columnNames.add("Price(RM)");
        columnNames.add("Total Sales(RM)");

        Vector<Vector> allRowData = new Vector<Vector>();
        for(int i = 0;i < item.size(); i++){
            Vector temp = new Vector<Vector>();

            temp.add("MONTH");
            temp.add(item.get(i).getProduct().getId());
            temp.add(item.get(i).getProduct().getTitle());
            temp.add(item.get(i).getQty());
            temp.add(item.get(i).getProduct().getPrice());
            temp.add(item.get(i).getProduct().getPrice() * item.get(i).getQty());

            allRowData.add(temp);
        }
        // Initializing the JTable
//        j = new JTable(realRowData, columnNames);
        j = new JTable(allRowData, columnNames);
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
