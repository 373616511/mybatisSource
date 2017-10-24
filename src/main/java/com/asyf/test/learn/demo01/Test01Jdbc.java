package com.asyf.test.learn.demo01;


import java.sql.*;

/**
 * Created by Administrator on 2017/10/24.
 */
public class Test01Jdbc {

    public static void main(String[] args) {
        Test01Jdbc t = new Test01Jdbc();
        Connection con = t.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = new User();
        try {
            pstmt = con.prepareStatement("SELECT * FROM user WHERE id = ?");
            pstmt.setString(1, "1");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                user.setName(rs.getString(2));
            }
            System.err.println(user.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //遵循先开后闭的原则
            t.close(rs, pstmt, con);
        }
    }

    //获得数据库连接
    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mydb?zeroDateTimeBehavior=convertToNull";
            String user = "root";
            String password = "12345678";
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //释放资源
    private void close(ResultSet rs, PreparedStatement pstmt, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
