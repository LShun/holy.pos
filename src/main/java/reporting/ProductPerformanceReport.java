package reporting;

import food_menu.FoodMenu;
import food_menu.Product;
import order.Order;
import order.Receipt;

import javax.swing.*;
import java.util.ArrayList;

import static javax.swing.UIManager.get;

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


        // Data to be displayed in the JTable

        double[] sales= new double[6];

                sales[0] = 5 * products.get(0).getPrice();

                sales[1] = 5 * products.get(1).getPrice();

                sales[2] = 5 * products.get(2).getPrice();

                sales[3] = 5 * products.get(3).getPrice();

                sales[4] = 5 * products.get(4).getPrice();

                sales[5] = 5 * products.get(5).getPrice();

                //sales[6] = 5 * products.get(6).getPrice();

       /* String[][] data =new String[][]{
                {"14 July 2019", products.get(0).getId(), products.get(0).getTitle(), "5", String.valueOf(products.get(0).getPrice()), String.valueOf(sales[0])},
                {"14 July 2019", products.get(1).getId(), products.get(1).getTitle(), "5", String.valueOf(products.get(1).getPrice()), String.valueOf(sales[1])},
                {"14 July 2019", products.get(2).getId(), products.get(2).getTitle(), "5", String.valueOf(products.get(2).getPrice()), String.valueOf(sales[2])},
                {"14 July 2019", products.get(3).getId(), products.get(3).getTitle(), "5", String.valueOf(products.get(3).getPrice()), String.valueOf(sales[3])},
                {"14 July 2019", products.get(4).getId(), products.get(4).getTitle(), "5", String.valueOf(products.get(4).getPrice()), String.valueOf(sales[4])},
                {"14 July 2019", products.get(5).getId(), products.get(5).getTitle(), "5", String.valueOf(products.get(5).getPrice()), String.valueOf(sales[5])},


                {"Total", "", "", "10", "5", "100"}
        };*/

        ArrayList<Object[]> rowData = new ArrayList<Object[]>();
        for(int i = 0; i < receipt.size(); i++){
            int sizeOfListOfItems = receipt.get(i).getListOfItems().size();

            for(int j = 0;j < sizeOfListOfItems ; j++){
                Object[] product = new Object[6];

                product[0]  = receipt.get(i).getTransactionTime().toString();
                product[1] = receipt.get(i).getBillID();
                product[2] = receipt.get(i).getListOfItems().get(j).getProduct().getTitle();
                product[3] = receipt.get(i).getListOfItems().get(j).getQty();
                product[4] = receipt.get(i).getListOfItems().get(j).getProduct().getPrice();
                product[5] = receipt.get(i).getTotal();
                //product[6] = receipt.get(i).getTotal();

                rowData.add(product);

            }

        }

        Object[][] realRowData = new Object[rowData.size()][];
        for(int i = 0; i < rowData.size(); i++){
            realRowData[i] = rowData.get(i);
        }


        // Column Names
        String[] columnNames = { "Date(month)", "Product ID", "Product Name","Quantity","Price(RM)","Total Sales(RM)"};

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
