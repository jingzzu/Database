package HZZX.manager.meetingInformation;

import HZZX.manager.business.Business;
import HZZX.utils.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class SelectPlaceInformation extends JFrame{
    JTable jt;
    JScrollPane js = new JScrollPane();//容器带滚动条，通过移动滚动条的滑块
    Vector columnNames = null;
    Vector rowData = null;
    Connection con = null;
    ResultSet rs;
//String a;
    //  JButton jb;
    //JPanel jp;
    public SelectPlaceInformation() {

        columnNames = new Vector();
        rowData = new Vector();
        columnNames.add("展会编号");
        columnNames.add("展会名称");
        columnNames.add("展馆编号");
        columnNames.add("展馆名称");
        columnNames.add("展馆位数");
        columnNames.add("时间");

        try {
            con = DatabaseConnection.getConnection();
            PreparedStatement ps;
          //  String sql = ;
            ps = con.prepareStatement("select  Meeting.Mno,Meeting.Mname,Place.Pno,Place.Pname,Place.Pnum,Meeting.Mtime  " +
                    "from  Meeting,Place where  Meeting.Mno = Place.Mno and Meeting.Mno = "+SelectP.jt1.getText());//创建封装语句的对象
            rs = ps.executeQuery();//查询的结果集
            while (rs.next()) {
                Vector vector = new Vector();
                vector.add(rs.getString(1));
                vector.add(rs.getString(2));
                vector.add(rs.getString(3));
                vector.add(rs.getString(4));
                vector.add(rs.getString(5));
                vector.add(rs.getString(6));
                rowData.add(vector);
            }
            System.out.println("OK");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        jt = new JTable(rowData, columnNames);
        js = new JScrollPane(jt);

        this.add(js);
        //      this.add(jp);
        this.setTitle("查询");
        this.setLayout(new GridLayout(2, 1));
        this.setBounds(450, 300, 1200, 600);
        this.setVisible(true);
        this.setResizable(false);
    }
}

