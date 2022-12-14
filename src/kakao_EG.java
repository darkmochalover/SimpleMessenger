import java.sql.*;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class kakao_EG {

    static Connection con = null;
    static Statement statement = null;
    static ResultSet resultSet = null;

    public void makeConnection(String url, String userName, String password) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, password);
            System.out.println(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //NOTE : 1. check connector
    // 2. check jdk
    public kakao_EG () throws SQLException, ClassNotFoundException {

        String pw = "2948";
        makeConnection("jdbc:mysql://localhost", "root", pw);
        System.out.println("connection!");

        kakao_DB DB1 = new kakao_DB(con);
        DB1.makeDB("kakao_user");
        System.out.println("create database!");

        UserTable user1 = new UserTable(con, "userTables");
        user1.makeTable("userTables", "(num int not null, ID varchar(16) not null, password varchar(16) not null, name varchar(16), phonenumber varchar(45), primary key(num, ID) )");
        System.out.println("create table!");


        LoginFrame f = new LoginFrame(user1);
        f.createLoginFrame();
    }
}