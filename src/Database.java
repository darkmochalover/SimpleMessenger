import java.sql.*;

public class Database {
    Connection con = null;
    String databaseName;
    public Database(Connection con) {
        this.con = con;
    }

    private ResultSet executeQuery(String query) {
        ResultSet rs = null;
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    private int executeUpdate(String query) {
        int res = 0;
        try {
            Statement stmt = con.createStatement();
            res = stmt.executeUpdate(query);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    private boolean isDatabase(String name) throws SQLException {
        int cnt = 0;
        ResultSet rs = executeQuery("select 1 from Information_schema.SCHEMATA where schema_name = '" + name + "';");
        while(rs.next()) {
            cnt++;
        }
        if(cnt >= 1) {
            System.out.println("ERROR: DB does already exist");
            return true;
        }
        else {
            System.out.println("doesn't exist : " + name);
            return false;
        }
    }

    public void makeDB(String name) throws SQLException {
        if(!isDatabase(name)) {
            int createRow = executeUpdate("create database " + name + ";");
            int useRow = executeUpdate("use " + name + ";");
        }
        else {
            int alreadyExistRow = executeUpdate("use " + name + ";");
        }
    }

    public void dropDB(String name) {
        int dropRow = executeUpdate("drop database " + name + ";");
    }

}
