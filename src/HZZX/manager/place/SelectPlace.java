package HZZX.manager.place;

//import HZZX.customer.placeReserve.InsertReserve;
import HZZX.utils.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class SelectPlace extends JFrame{
    JTable jt;
    JScrollPane js = new JScrollPane();
    Vector columnNames = null;
    Vector rowData = null;
    Connection con = null;
    ResultSet rs;



    public SelectPlace(){
        columnNames = new Vector();
        rowData = new Vector();
        columnNames.add("展馆编号");
        columnNames.add("名称");
        columnNames.add("面积");
        columnNames.add("地址");
        columnNames.add("负责人");
        columnNames.add("展位数");
        columnNames.add("会展编号");

        try{
            con = DatabaseConnection.getConnection();
            PreparedStatement ps;
            ps = con.prepareStatement("select * from Place");
            rs = ps.executeQuery();
            while (rs.next()){
                Vector vector = new Vector();
                vector.add(rs.getString(1));
                vector.add(rs.getString(2));
                vector.add(rs.getString(3));
                vector.add(rs.getString(4));
                vector.add(rs.getString(5));
                vector.add(rs.getString(6));
                vector.add(rs.getString(7));
                rowData.add(vector);
            }
            System.out.println("OK");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                rs.close();
                con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        jt = new JTable(rowData,columnNames);
        js = new JScrollPane(jt);

        this.add(js);

        this.setTitle("查询");
        this.setLayout(new GridLayout(2,2));
        this.setBounds(630,300,850,500);
        this.setVisible(true);
        this.setResizable(false);
    }


}
