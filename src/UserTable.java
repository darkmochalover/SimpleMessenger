import java.io.*;
import java.sql.*;

public class UserTable extends Table{

    public UserTable(Connection con) {
        super(con);
    }
    public UserTable(Connection con, String tName) {
        super(con, tName);
    }

    public void deleteTxtFile(String fileName) {
        File f = new File(fileName);
        if(f.exists()) {
            if(f.delete()) System.out.println(fileName + " Delete Success!");
            else System.out.println(fileName + " Delete Fail");
        }
    }

    public int returnUserNumID(String id) {
        ResultSet rs = executeQuery("select num from " + tableName + " where id = '" + id + "';");
        int numID = 0;
        try {
            if (rs.next()) {
                numID = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return numID;
    }
    public boolean insertAUser(String ID, String pw, String name, String phonenumber) {
        if(returnUserNumID(ID) == 0) {
            try {
                makeTable("user" + Integer.toString(cnt) + "_following", "(id int not null, primary key(id) )");
                makeTable("user" + Integer.toString(cnt) + "_follower", "(id int not null, primary key(id) )");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            int insertRow = executeUpdate("insert into " + tableName + " values (" + cnt + ", '" + ID + "', '" + pw + "', '" + name + "', '" + phonenumber + "' );");
            cnt++;
            return true;
        }
        return false;
    }

    public String delete(String id,String pw){
        String delete = "";
        int num1;
        try {
            Statement stmt = con.createStatement();
            num1 = stmt.executeUpdate("delete from usertables where id = \""+id+"\";");

        }
        catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
            e.printStackTrace();

        }
        return delete;
    }

    public boolean isThereNumID(String tableName, int n) {
        ResultSet rs = executeQuery("select count(*) from " + tableName + " where id = " + Integer.toString(n) + ";");
        try {
            if(rs.next()) {
                int count = rs.getInt(1);
                if(count == 0) return false;
                else return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    // isFollow - false : unfollow, true : follow
    //애들아 여긴 팔로우 그때 한곳이라 아마 지워도 될거야

    public void follow(boolean isFollow, int meNumID, int someoneNumID) {
        String followingTableName = "user" + Integer.toString(meNumID) + "_following";
        String followerTableName = "user" + Integer.toString(someoneNumID) + "_follower";
        if(isFollow) {
            if(!isThereNumID(followingTableName, someoneNumID)) { // check duplicate data
                int insertRow = executeUpdate("insert into " + followingTableName + " values (" + someoneNumID + ");");
                insertRow = executeUpdate("insert into " + followerTableName + " values (" + meNumID + ");");
            }
        }
        else { //delete tuple(data) https://m.blog.naver.com/kee5004/60017447664
            if(isThereNumID(followingTableName, someoneNumID)) {
                int deleteRow = executeUpdate("delete from " + followingTableName + " where id = " + Integer.toString(someoneNumID) + ";");
                deleteRow = executeUpdate("delete from " + followerTableName + " where id = " + Integer.toString(meNumID) + ";");
            }
        }
    }

    public int loginReturnNum(String id, String pw) throws SQLException {
        ResultSet rs = executeQuery("select num from " + tableName + " where id = '" + id + "' and password = '" + pw + "';");
        int numID = 0;
        if(rs.next()) {
            String s = rs.getString("num");
            numID = Integer.parseInt(s);
            return numID;
        }
        else return 0;
        //https://allg.tistory.com/21
    }


    public String getUserNickname(int numID) {
        ResultSet rs = executeQuery("select nickname from " + tableName + " where num = " + Integer.toString(numID) + ";");
        String res = "";
        try {
            if(rs.next()) {
                res = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public String getUserID(String phone,String name)  {

        ResultSet rs = executeQuery("select ID from "+ tableName +" where nickname = \"" + phone +"\" and name = \""+ name +"\";");
        String res = "";
        try {
            if(rs.next()) {
                res = rs.getString("ID");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public String getUserPW(String phone,String name,String id)  {

        ResultSet rs = executeQuery("select password from "+ tableName +" where nickname = \"" + phone +"\" and name = \""+ name +"\" and id = \""+ id +"\"" + ";");
        String res = "";
        try {
            if(rs.next()) {
                res = rs.getString("password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public String selectUserNicknameString(int id) throws SQLException {
        ResultSet rs = executeQuery("select nickname from usertables where num = " + id + ";");
        String s = "";
        if(rs.next()) s = rs.getString("nickname");
        return s;
    }

    public String getUserName(String id){

        ResultSet rs = executeQuery("select name from userTables where id = \""+id+"\" ;");
        String name=""; // 이름을 받아옴
        try {
            if (rs.next()) {
                name = rs.getString("name");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
            return name;
    }

}