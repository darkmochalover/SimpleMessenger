import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.sql.*;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {
    public static String url;
    public  static String userName;
    public static String password;
    public static UserTable user1;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        kakao_EG klog = new kakao_EG();
        //klog.makeConnection(url, userName, password);
        //klog.makeLg();
        LoginFrame mylogin = new LoginFrame(user1);
    }
}