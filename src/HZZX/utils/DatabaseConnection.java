package HZZX.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/*
将数据库连接封装
 */
public class DatabaseConnection {

    private static Connection conn = null;

    public static Connection getConnection() {
        try {
            System.out.println("我在连数据库");
            String URL = "jdbc:sqlserver://localhost:1433;DatabaseName=STable";
            String USERNAME = "lijing";
            String PASSWORD = "111111";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //加载驱动
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD); //得到连接

            System.out.println("已成功连接数据库...");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}