package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;


public class profileBoxDemo extends JFrame implements ActionListener {

	/* Self id & target id */
    private String target_user_id;
    private String self_id;
    
    /* Main Panel */
	Frame f;
	private JPanel contentPane;
	
	/* Button & TextField */
	Image profile_pic = null;
	JTextField field_user_name; 		/* User Name */
	JTextField field_user_id; 			/* User id */
	JTextField field_desc; 				/* 한 줄 소개 */
	
	
    /* Pop-up Menu */
    public static String option[] = {"프로필 수정", "상태 변경", "프로필 보기", "1:1 채팅하기", "1:1 게임하기"};
    PopupMenu profile_menu = new PopupMenu();
    MenuItem profile_item1 = new MenuItem(option[0]);
    MenuItem profile_item2 = new MenuItem(option[1]);
    MenuItem profile_item3 = new MenuItem(option[2]);
    MenuItem profile_item4 = new MenuItem(option[3]);
    MenuItem profile_item5 = new MenuItem(option[4]);
    /* --- */
    
    public void setTarget(String target_id)
    {
    	this.target_user_id = target_id;
    }
    
    public void setSelf(String self_id)
    {
    	this.self_id = self_id;
    }
    
    public profileBoxDemo(String self_id, String target_id) throws IOException
    {	
        /* Set ids */
        this.self_id = self_id;
    	this.target_user_id = target_id;

    	init(this.self_id, this.target_user_id);
    }
    
    public void init(String self_id, String target_id) throws IOException
    {
    	/* Set Main Pane */
    	setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(500, 200);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/* Set main block */

		/* Profile picture */
		String pic_path = "src/image/kitty.jpeg";
		File sourceimage = new File(pic_path);
		profile_pic = ImageIO.read(sourceimage);
		profile_pic = profile_pic.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
		JLabel label_profile_pic = new JLabel(new ImageIcon(profile_pic));
		label_profile_pic.setBounds(10, 30, 100, 100);
		contentPane.add(label_profile_pic);
		
		/* User Name */
		field_user_name = new JTextField("관리자");
		field_user_name.setFont(new Font("Tahoma", Font.PLAIN, 14));
		field_user_name.setBackground(new Color(255, 0, 0, 0));
		field_user_name.setEditable(false);
		field_user_name.setBounds(160, 30, 200, 31);
		contentPane.add(field_user_name);
		
		/* User id */
		field_user_id = new JTextField("@admin");
		field_user_id.setFont(new Font("Tahoma", Font.PLAIN, 10));
		field_user_id.setBounds(160, 60, 200, 20);
		field_user_id.setBackground(new Color(255, 0, 0, 0));
		field_user_id.setEditable(false);
		contentPane.add(field_user_id);
		field_user_id.setColumns(10);
		
		
		/* 한 줄 소개란 */
		field_desc = new JTextField("안녕하세요");
		field_desc.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		field_desc.setHorizontalAlignment(JLabel.CENTER);
		field_desc.setBackground(new Color(255, 0, 0, 0));
		field_desc.setEditable(false);
		field_desc.setBounds(160, 80, 300, 50);
		field_desc.setColumns(10);
		contentPane.add(field_desc);
		

        // 팝업 메뉴에 아이템 추가하기.
        // is self, "프로필 수정", "상태 변경" 버튼 추가
        if(this.self_id.equals(this.target_user_id))
        {
            profile_menu.add(profile_item1);
            profile_menu.add(profile_item2);
            profile_menu.addSeparator();
        }
        profile_menu.add(profile_item3);
        profile_menu.add(profile_item4);
        profile_menu.add(profile_item5);

        add(profile_menu);

        setSize(300,400);
        setVisible(true);
        
        // 종료 버튼  
        this.addWindowListener(new WindowAdapter()
        {
       			@Override
       			public void windowClosing(WindowEvent e)
       			{
       				System.exit(0);       		
       			}
        });
        
        // 팝업 메뉴 보여주기 이벤트
        this.addMouseListener(new MouseAdapter() {
        	 
            @Override
            public void mousePressed(MouseEvent me) {
                // 오른쪽 마우스 버튼을 누르면 PopupMenu를 화면에 보여준다.
                if (me.getButton() == MouseEvent.BUTTON3)
                	profile_menu.show(profileBoxDemo.this, me.getX(), me.getY());
            }
        });
    }
    public static void main(String[] args) throws IOException {
    	profileBoxDemo pb = new profileBoxDemo("admin", "admin");
    	pb.setBounds(40, 40, 500, 200);
    	pb.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
