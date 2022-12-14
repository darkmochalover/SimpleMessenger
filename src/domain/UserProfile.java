package domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

public class UserProfile {

	public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost/messenger_db";
    public static final String USERID = "root";
    public static final String USERPWD = "2948";
    
    private String user_name; 		// 이름 
    private String id; 				// 아이디 
    private String image; 			// 프로필 사진 
    private String description; 	// 한 줄 소개 
    private int birthday; 		// 생일 
    private String mail; 			// 메일 
    private int phone_num; 		// 전화번호 
    private String status;			 // 상태 
    
    
    
    public UserProfile(){
    	
    }
    
    public UserProfile(
    		String user_name, 		// 이름 
    	    String id,				// 아이디 
    	    String image, 			// 프로필 사진 
    	    String description,		// 한 줄 소개 
    	    int birthday,		// 생일 
    	    String mail ,			// 메일 
    	    int phone_num 	,	// 전화번호 
    	    String status			 // 상태 
    	    )
    {
    	this.user_name = user_name; 		// 이름 
    	this.id = id;						// 아이디 
	    this.image = image; 				// 프로필 사진 
	    this.description = description;		// 한 줄 소개 
	    this.birthday = birthday;			// 생일 
	    this.mail = mail; 					// 메일 
	    this.phone_num = phone_num; 		// 전화번호 
	    this.status = status;
    }

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(int phone_num) {
		this.phone_num = phone_num;
	}

	public int getBirthday() {
		return birthday;
	}

	public void setBirthday(int birthday) {
		this.birthday = birthday;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public static String phone_format(int number) {
			String phone_num =  "0" + Integer.toString(number);
			String regEx = "(\\d{3})(\\d{3,4})(\\d{4})";
			return phone_num.replaceAll(regEx, "$1-$2-$3");
	}
	

	public void printUserProfile(UserProfile user)
	{
		System.out.println("["+ this.user_name + "]");
		System.out.println("id: @" + this.id);
		System.out.println("description: " + this.description);
		System.out.println("phone number: " + phone_format(this.phone_num));
		System.out.println("mail: " + this.mail);
	}
	
	public ResultSet search(String searchString, String searchBy, JPanel panel) throws ClassNotFoundException
	{	
		Connection conn = null;
        try{
        	// 1. 드라이버 로딩
            Class.forName(DRIVER_NAME);

            // 2. 연결하기
            String url = "jdbc:mysql://localhost/messenger_db";
            String db_id = "root";		String db_pw = "2948";
            conn = DriverManager.getConnection(URL, USERID, USERPWD);
            
            String user_load = "select * from users where * = ?";
    	    
        	PreparedStatement psmt = conn.prepareStatement(user_load);
        	psmt.setString(1, searchString);
        	psmt.setString(2, searchBy);
        	
        	return psmt.executeQuery();
        }catch(SQLException e){
        	if (conn == null) {
                JOptionPane.showMessageDialog(panel, "Connection to database failed.");
            } else {
                JOptionPane.showMessageDialog(panel, "No results found for" + searchString + " in the database.");
            }
            e.printStackTrace();
            System.out.println("에러: 유저 로드에 실패했습니다.\n -> 상세 에러 내용: " + e);
    		return null;
        }
	}
	
	
	public ResultSet search(String searchString, JPanel panel) throws ClassNotFoundException
	{	
		Connection conn = null;
        try{
        	// 1. 드라이버 로딩
            Class.forName(DRIVER_NAME);

            // 2. 연결하기
            String url = "jdbc:mysql://localhost/messenger_db";
            String db_id = "root";		String db_pw = "2948";
            conn = DriverManager.getConnection(URL, USERID, USERPWD);
            
//            String user_load = "select * from users where id = ? or user_name = ? or phon_number = ? ";
            
            String user_load = "select * from users where id like '%"+ searchString + "%'"
            		+ " or user_name like '%" + searchString + "%' or phon_number like '%" + searchString + "%'";
    	    
        	PreparedStatement psmt = conn.prepareStatement(user_load);
        	
        	return psmt.executeQuery();
        }catch(SQLException e){
        	if (conn == null) {
                JOptionPane.showMessageDialog(panel, "Connection to database failed.");
            } else {
                JOptionPane.showMessageDialog(panel, "No results found for" + searchString + " in the database.");
            }
            e.printStackTrace();
            System.out.println("에러: 유저 로드에 실패했습니다.\n -> 상세 에러 내용: " + e);
    		return null;
        }
	}

	
	
	public ArrayList<UserProfile> ResultSetToList(ResultSet rs) throws SQLException 
	{
		if(rs == null)
			return null;	
		
		ArrayList<UserProfile> info_list = new ArrayList<UserProfile>();
		
		
		while(rs.next())
		{
			System.out.println(rs.getString(2));
			UserProfile crnt_profile = new UserProfile(
					rs.getString(1),		 	// 이름 
					rs.getString(2),			// 아이디 
					rs.getString(3),			// 프로필 사진 
					rs.getString(4),			// 한 줄 소개 
					rs.getInt(5),				// 생일 
					rs.getString(6),			// 메일 
					rs.getInt(7),				// 전화번호 
					rs.getString(8) 			// 상태 
					);
			
			info_list.add(crnt_profile);
		}
		
		return info_list;
	}

}
