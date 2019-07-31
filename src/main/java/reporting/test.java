package reporting;
import java.awt.BorderLayout;
import java.util.ArrayList;
//from   ww w .  java  2 s. co m
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;




public class test extends JFrame {
    JTable  table = new JTable();
    DefaultTableModel model = new DefaultTableModel();
    JScrollPane scroll;
    String headers[] = { "col1", "col2" };
    String[] data = { "AA", "BB", "CC", "DD", "EE", "FF", "GG", "HH", "II", "JJ",
            "KK", "LL", "MM", "NN", "OO", "PP", "QQ", "RR" };

    public test() {
        model.setColumnIdentifiers(headers);
        table.setModel(model);
        scroll = new JScrollPane(table);

        insert();

        add(scroll, BorderLayout.CENTER);
        setSize(300, 300);
        setVisible(true);
    }

    public void insert() {
        ArrayList<String> ar = new ArrayList<String>();
        for (int i = 0; i < data.length; i++) {
            ar.add(data[i]);
        }

        for (int i = 0; i < (ar.size() / 2); i++) {
            model.addRow(new Object[] { String.valueOf(ar.get(2 * i)),
                    String.valueOf(ar.get((2 * i) + 1)) });
        }
    }

    public static void main(String[] args) {
        new test();
    }
}

