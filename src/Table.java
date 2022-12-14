import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Table {
    Connection con = null;
    public String tableName;
    public int cnt;

    public Table(Connection con) {
        cnt = 1;
        this.con = con;
        this.tableName = "noName";
    }
    public Table(Connection con, String tName) {
        this.con = con;
        this.tableName = tName;
        cnt = returnTableLength(tName) + 1;
    }

    public int returnTableLength(String name) {
        try {
            if(!isTable(name))
                return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet rs = executeQuery("select count(*) from " + name + ";");
        int n = 1;
        try {
            if(rs.next()) {
                n = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return n;
    }

    public ResultSet executeQuery(String query) {
        ResultSet rs = null;
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public int executeUpdate(String query) {
        int res = 0;
        try {
            Statement stmt = con.createStatement();
            res = stmt.executeUpdate(query);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean isTable(String name) throws SQLException {
        int cnt = 0;
        ResultSet rs = executeQuery("select * from information_schema.tables where TABLE_NAME='" + name + "';");
        while(rs.next()) {
            cnt++;
        }
        if(cnt >= 1) {
            System.out.println("ERROR: '" + name + "' does already exist");
            return true;
        }
        else {
            System.out.println("doesn't exist : " + name);
            return false;
        }
    }
    public void makeTable(String name, String schema) throws SQLException {
        if(!isTable(name)) {
            int createRow = executeUpdate("create table " + name + " " + schema + ";");
        }
    }
    public void dropTable(String name) throws SQLException {
        if(isTable(name))
            executeUpdate("drop table " + name + ";");
    }
}
