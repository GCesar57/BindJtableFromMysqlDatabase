
package com.cesar;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;
import java.awt.BorderLayout;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
public class Main {
    public static void main(String[] args) {
        //
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String myQuery;
        //
        try {
            //
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees", "root", "");
            st = conn.createStatement();
            myQuery = "SELECT * from employees";
            rs = st.executeQuery(myQuery);
            ResultSetMetaData rsmt = rs.getMetaData();
            int columnCount = rsmt.getColumnCount();
            Vector column = new Vector(columnCount);
            for(int i=1; i<=columnCount; i++){
                column.add(rsmt.getColumnName(i));
            }//FOR
            Vector data = new Vector();
            Vector row = new Vector();
            while(rs.next()){
                row = new Vector(columnCount);
                for(int i=1; i<=columnCount; i++){
                    row.add(rs.getString(i));
                }//
                data.add(row);
            }//While
            JFrame frame = new JFrame();
            frame.setSize(800, 420);
            frame.setTitle("employees");
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel();
            JTable table = new JTable(data, column);
            JScrollPane jsp = new JScrollPane(table);
            panel.setLayout(new BorderLayout());
            panel.add(jsp, BorderLayout.CENTER);
            frame.setContentPane(panel);
            frame.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error with the Connection");
        }finally{
            try {
                conn.close();
                st.close();
                rs.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error with the Connection");
            }
        }//
    }
}
