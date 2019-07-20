package reporting;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;



public class TaxReport {
    // frame
    JFrame f;
    // Table
    JTable j;


// Constructor
public TaxReport()
{
    // Frame initiallization
    f = new JFrame();

    // Frame Title
    f.setTitle("Tax Report");

    // Data to be displayed in the JTable
    String[][] data = {
            { "INV001", "0.60", "10","14 July 2019","" },
            { "INV002", "0.90", "15","14 July 2019","" },
            {"Total Tax","","","","1.50"}
    };

    // Column Names
    String[] columnNames = { "Tax Name", "Tax Amount(RM)", "Base Amount(RM)","Date","References"};

    // Initializing the JTable
    j = new JTable(data, columnNames);
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