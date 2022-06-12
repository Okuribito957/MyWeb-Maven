package dao;

import java.sql.*;

public class MyConnection {

    //类的静态块仅在加载类时执行
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("驱动类未找到！");
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        String url = "jdbc:mysql://localhost:3306/studentmisdb?characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";
        try {
            return DriverManager.getConnection(url,"root","123456");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("获取链接出错！");
        }
        return null;
    }

    public static void close(Connection conn){
        try {
            if (conn != null && !conn.isClosed()){
                conn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("关闭链接出错！");
        }
    }

    public static void close(Statement st){
        try {
            if (st != null){
                st.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("关闭语句时出错！");
        }
    }

    public static void close(ResultSet rs){
        try {
            if (rs != null){
                rs.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("关闭结果集时出错！");
        }
    }

    public static void close(Statement st ,Connection conn){
        close(st);
        close(conn);
    }

    public static void close(ResultSet rs, Statement st, Connection conn) {
        close(rs);
        close(st);
        close(conn);
    }
}
