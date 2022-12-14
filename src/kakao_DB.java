import java.sql.Connection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class kakao_DB extends Database{
    kakao_DB(Connection con) {
        super(con);
    }
}
