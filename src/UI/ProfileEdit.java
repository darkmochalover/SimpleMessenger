package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import java.io.*;
import java.net.*;
import javax.imageio.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import domain.UserProfile;
import domain.UserProfile.*;

public class ProfileEdit extends JFrame implements ActionListener
{
	Frame f;
	private JPanel contentPane;
	
	public static String self_user_id;
	public static String target_user_id;
	

	private Image profile_pic = null;
	private String pic_path;
	
	public JTextField field_user_name; 		/* User Name */
	public JTextField field_user_id; 			/* User id */
	public JTextField field_follower; 			/* Follower */
	public JTextField field_following; 		/* Following */
	public JTextField field_birthday; 			/* 생일 */
	public JTextField field_phone; 			/* 연락처 - 휴대폰 */
	public JTextField field_mail; 				/* 연락처 - 메일 */
	public JTextField field_desc; 				/* 한 줄 소개 */
	
	
	
	Button apply; // 수정 적용하기
	Button cancel; // 수정 취소 
	
	

	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try {
					target_user_id = "admin_super";
					ProfileEdit pf = new ProfileEdit();
					pf.setVisible(true);
				}
				catch(Exception e){
		            System.out.println("에러: " + e);
		        }
			}
		});
		
	}
	
	public ProfileEdit() throws IOException, ClassNotFoundException
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(500, 700);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/* Buttons */
		apply = new Button("적용");
		apply.addActionListener(this);
		apply.setFont(new Font("Tahoma", Font.PLAIN, 12));
		apply.setBounds(30, 500, 150, 20);
		contentPane.add(apply);
		
		cancel = new Button("취소");
		cancel.addActionListener(this);
		cancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cancel.setBounds(300, 500, 150, 20);
		contentPane.add(cancel);
		
		/* Profile picture */
		String pic_path = "src/image/kitty.jpeg";
		File sourceimage = new File(pic_path);
		profile_pic = ImageIO.read(sourceimage);
		profile_pic = profile_pic.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		JLabel label_profile_pic = new JLabel(new ImageIcon(profile_pic));
		label_profile_pic.setBounds(40, 50, 150, 150);
		contentPane.add(label_profile_pic);
		
		/* User Name */
		field_user_name = new JTextField("ex..");
		field_user_name.setFont(new Font("Tahoma", Font.PLAIN, 16));
		field_user_name.setEditable(true);
		field_user_name.setBounds(200, 70, 200, 31);
		contentPane.add(field_user_name);
		
		/* User id */
		field_user_id = new JTextField("@ex..");
		field_user_id.setBounds(200, 100, 200, 20);
		field_user_id.setEditable(true);
		contentPane.add(field_user_id);
		field_user_id.setColumns(10);
		
//		/* Follower */
//		JLabel label_follower = new JLabel("Followers");
//		label_follower.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		label_follower.setBounds(230, 140, 70, 20);
//		contentPane.add(label_follower);
//		
//		field_follower = new JTextField("0");
//		field_follower.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		field_follower.setEditable(true);
//		field_follower.setBounds(230, 170, 70, 20);
//		field_follower.setColumns(10);
//		contentPane.add(field_follower);
//		
//		/* Following */ 
//		JLabel label_following = new JLabel("Following");
//		label_following.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		label_following.setBounds(320, 140, 70, 20);
//		contentPane.add(label_following);
//		
//		field_following = new JTextField("0");
//		field_following.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		field_following.setEditable(true);
//		field_following.setBounds(320, 170, 70, 20);
//		field_following.setColumns(10);
//		contentPane.add(field_following);
		
		
		/* 생일 */
		JLabel label_birthday = new JLabel("생일");
		label_birthday.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_birthday.setBounds(20, 240, 100, 20);
		contentPane.add(label_birthday);
		
		field_birthday = new JTextField();
		field_birthday.setFont(new Font("Tahoma", Font.PLAIN, 12));
		field_birthday.setEditable(true);
		field_birthday.setBounds(80, 240, 400, 20);
		field_birthday.setColumns(10);
		contentPane.add(field_birthday);
		
		/* 연락처 - 휴대폰 */
		JLabel label_phone = new JLabel("휴대폰");
		label_phone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_phone.setBounds(20, 260, 100, 20);
		contentPane.add(label_phone);
		
		field_phone = new JTextField();
		field_phone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		field_phone.setEditable(true);
		field_phone.setBounds(80, 260, 400, 20);
		field_phone.setColumns(10);
		contentPane.add(field_phone);
		
		/* 연락처 - 메일 */
		JLabel label_mail = new JLabel("메일");
		label_mail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_mail.setBounds(20, 280, 100, 20);
		contentPane.add(label_mail);
		
		field_mail = new JTextField();
		field_mail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		field_mail.setEditable(true);
		field_mail.setBounds(80, 280, 400, 20);
		field_mail.setColumns(10);
		contentPane.add(field_mail);
		
		/* 한 줄 소개 */
		JLabel label_desc = new JLabel("한 줄 소개");
		label_desc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_desc.setBounds(20, 320, 100, 20);
		contentPane.add(label_desc);
		
		field_desc = new JTextField("ex..");
		field_desc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		field_desc.setEditable(true);
		field_desc.setBounds(20, 340, 450, 100);
		field_desc.setColumns(10);
		contentPane.add(field_desc);
		
		try {
			// 1. 드라이버 로딩
            Class.forName("com.mysql.jdbc.Driver");

            // 2. 연결하기
            String url = "jdbc:mysql://localhost/messenger_db";
            String db_id = "root";		String db_pw = "2948";
            Connection conn = DriverManager.getConnection(url, db_id, db_pw);
            
            // 팔로우, 팔로잉 수 불러오기. 
            int follower_count = 0;
            int following_count = 0;
            String follower_counter = "select count(tgt_id) from follow where src_id = ?";
            String following_counter = "select count(tgt_id) from follow where tgt_id = ?";
            
            PreparedStatement follower_query = conn.prepareStatement(follower_counter);
            PreparedStatement following_query = conn.prepareStatement(following_counter);
            
            follower_query.setString(1, target_user_id);
        	following_query.setString(1, target_user_id);
        	
        	ResultSet followerResultSet = follower_query.executeQuery();
        	ResultSet followingResultSet = following_query.executeQuery();
        	
        	if(followerResultSet.next())
        	{
        		follower_count = followerResultSet.getInt(1);
        		System.out.println("follower: " + follower_count);
        	}
        	
        	if(followingResultSet.next())
        	{
        		following_count = followingResultSet.getInt(1);
        	}
        	
        	
            // 유저 기본적인 정보 다 불러옴. 
            String user_load = "select * from users where id = ?";
            PreparedStatement psmt = conn.prepareStatement(user_load);
        	psmt.setString(1, target_user_id);
        	
        	
        	ResultSet rs = psmt.executeQuery();
        	
        	
        	
        	if(rs.next())
        	{
        		field_user_name.setText(rs.getString(1)); 		/* User Name */
        		field_user_id.setText("@"+rs.getString(2)); 			/* User id */
//        		field_follower.setText(Integer.toString(follower_count));; 			/* Follower */
//        		field_following.setText(Integer.toString(following_count));; 		/* Following */
        		field_birthday.setText(Integer.toString(rs.getInt(6))); 			/* 생일 */
        		field_phone.setText(UserProfile.phone_format(rs.getInt(8))); 			/* 연락처 - 휴대폰 */
        		field_mail.setText(rs.getString(7)); 				/* 연락처 - 메일 */
        		field_desc.setText(rs.getString(5));				/* 한 줄 소개 */
        	}
            
		}
		catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }
        catch(SQLException e){
            System.out.println("에러: " + e);
        }
		
		
		setTitle("Profile Edit");
		setVisible(true);
		
	}
	
	public ProfileEdit(String target_user_id)
	{
		// super();
		this.target_user_id = target_user_id;

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Button bb = (Button) ae.getSource();
		
		
		if(bb == apply)
		{
			try {
				// 1. 드라이버 로딩
	            Class.forName("com.mysql.jdbc.Driver");

	            // 2. 연결하기
	            String url = "jdbc:mysql://localhost/messenger_db";
	            String db_id = "root";		String db_pw = "2948";
	            Connection conn = DriverManager.getConnection(url, db_id, db_pw);
	            
	            String edit_user = "update users set "
	            		+ "user_name = ?, "
	            		+ "id = ?, "
	            		+ "description = ?, "
	            		+ "birthday = ?,"
	            		+ " mail = ?, "
	            		+ "phon_number = ? "
	            		+ " where id = ?";
	            
	            PreparedStatement edit_stmt = conn.prepareStatement(edit_user);
	            
	            edit_stmt.setString(1, field_user_name.getText()); 						/* User Name */
	            edit_stmt.setString(2, field_user_id.getText().replace("@", "")); 						/* User id */
	            edit_stmt.setString(3, field_desc.getText()); 							/* 한 줄 소개 */
	            edit_stmt.setLong(4, Integer.parseInt(field_birthday.getText())); 		/* 생일 */
	            edit_stmt.setString(5, field_mail.getText()); 				/* 연락처 - 메일 */
	            edit_stmt.setLong(6, Integer.parseInt(field_phone.getText().replace("-",""))); 			/* 연락처 - 휴대폰 */
	            
	            
	            edit_stmt.setString(7, target_user_id);
	            
	            
	            
	            int cnt = edit_stmt.executeUpdate();
	            
	            ProfilePage pf_name = new ProfilePage("admin_super", field_user_id.getText().replace("@", ""));
	            ProfilePage pf = null;
				
            	pf = new ProfilePage();
            	pf.setVisible(true);
				
	            
			}
			catch(ClassNotFoundException e){
	            System.out.println("드라이버 로딩 실패");
	        }
	        catch(SQLException e){
	            System.out.println("에러: " + e);
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Apply");
		}
		if(bb == cancel)
		{
			dispose();
			
			try {
				ProfilePage pf_name = new ProfilePage("admin_super", target_user_id);
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ProfilePage pf = null;
			
        	pf = new ProfilePage();
        	pf.setVisible(true);
        	
			System.out.println("Cancel");
		}
		
	}
	
}
