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

import UI.ProfileEdit;

public class ProfilePage extends JFrame implements ActionListener{
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
	
	/* if self, */
	Button edit; 	// 프로필 수정 
	
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try {
					target_user_id = "admin_super";
					ProfilePage pf = new ProfilePage("admin_super", "admin_super");
					pf.setVisible(true);
				}
				catch(Exception e){
		            System.out.println("에러: " + e);
		        }
			}
		});
		
	}
	
	public ProfilePage()
	{
		
	}
	
	public ProfilePage(String self_id, String target_id) throws ClassNotFoundException, IOException
	{
		this.self_user_id = self_id;
		this.target_user_id = target_id;
		init(this.self_user_id, this.target_user_id);
	}
	
	public void init(String self_id, String target_id) throws IOException, ClassNotFoundException
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
		edit = new Button("edit");
		edit.addActionListener(this);
		edit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		edit.setBounds(420, 70, 50, 20);
		contentPane.add(edit);
		
		
		/* Profile picture */
//		String pic_path = "/Users/ajin/Desktop/2022/2022 2학기/컴퓨터 네트워크 및 실습 /Active_Learning/Messenger Project/User/src/image/kitty.jpeg";
		String pic_path = "src/image/kitty.jpeg";
		File sourceimage = new File(pic_path);
		profile_pic = ImageIO.read(sourceimage);
		profile_pic = profile_pic.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		JLabel label_profile_pic = new JLabel(new ImageIcon(profile_pic));
		label_profile_pic.setBounds(180, 320, 150, 150);
		contentPane.add(label_profile_pic);
		
		/* User Name */
		field_user_name = new JTextField("관리자");
		field_user_name.setFont(new Font("Tahoma", Font.PLAIN, 16));
		field_user_name.setEditable(false);
		field_user_name.setHorizontalAlignment(JLabel.CENTER);
		field_user_name.setBackground(new Color(255, 0, 0, 0));
		field_user_name.setBounds(180, 480, 150, 31);
		contentPane.add(field_user_name);
		
		/* User id */
		field_user_id = new JTextField("@admin_super");
		field_user_id.setHorizontalAlignment(JLabel.CENTER);
		field_user_id.setBackground(new Color(255, 0, 0, 0));
		field_user_id.setBounds(180, 510, 150, 20);
		field_user_id.setEditable(false);
		contentPane.add(field_user_id);
		field_user_id.setColumns(10);

		
		/* 한 줄 소개란 */
		field_desc = new JTextField("test");
		field_desc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		field_desc.setHorizontalAlignment(JLabel.CENTER);
		field_desc.setBackground(new Color(255, 0, 0, 0));
		field_desc.setEditable(false);
		field_desc.setBounds(20, 540, 450, 100);
		field_desc.setColumns(10);
		contentPane.add(field_desc);
		
		setTitle("Profile");
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Button bb = (Button) e.getSource();
		
		if(bb == edit)
		{
			// dispose();
			ProfileEdit edit_frame_name = new ProfileEdit(target_user_id);
			ProfileEdit edit_frame = null;
			try {
				edit_frame = new ProfileEdit();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			edit_frame.setVisible(true);
			System.out.println("edit");
		}
		
	}
}
