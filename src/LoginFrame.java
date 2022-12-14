import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

class LoginFrame extends JFrame implements ActionListener {
    JTextField id,password;
    UserTable user1;
    //ArticleTable article1;
    int userNumID;



    public LoginFrame(UserTable user1) {
        this.user1 = user1;
    }
    public void createLoginFrame() {
        //JFrame jf = new JFrame();
        setSize(400, 600);
        setLocation(50, 50);
        setTitle("AIKAO");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        String[] label_name = {"ID", "password"};


        ImageIcon logo = new ImageIcon("src/image/kakao_logo.jpg");
        Image relogo = logo.getImage();
        Image updatelogo = relogo.getScaledInstance(320,300,Image.SCALE_SMOOTH);
        ImageIcon resizelogo = new ImageIcon(updatelogo);
        JLabel logolabel = new JLabel(resizelogo);
        logolabel.setSize(320,300);
        logolabel.setLocation(40,50);

        add(logolabel);

        JLabel jl = new JLabel(label_name[0]);
        jl.setSize(80, 30);
        jl.setLocation(70, 420);
        jl.setHorizontalAlignment(JLabel.LEFT);

        add(jl);

        jl = new JLabel(label_name[1]);
        jl.setSize(80, 30);
        jl.setLocation(70, 450);
        jl.setHorizontalAlignment(JLabel.LEFT);

        add(jl);

        id = new JTextField();
        id.setSize(130, 30);
        id.setLocation(150, 420);

        add(id);

        JButton forgot_ID = new JButton("forgotID");
        forgot_ID.setSize(95,25);
        forgot_ID.addActionListener(this);
        forgot_ID.setLocation(285,420);

        add(forgot_ID);

        password = new JTextField();
        password.setSize(130, 30);
        password.setLocation(150, 450);
        forgot_ID.setBackground(Color.WHITE);
        add(password);

        JButton forgot_PW = new JButton("forgotPW");
        forgot_PW.setSize(95,25);
        forgot_PW.addActionListener(this);
        forgot_PW.setLocation(285,450);
        forgot_PW.setBackground(Color.WHITE);

        add(forgot_PW);

        String btn_name = "Login";
        JButton jb = new JButton(btn_name);
        jb.setSize(100, 25);
        jb.addActionListener(this);
        jb.setLocation(150, 500);
        jb.setBackground(Color.WHITE);

        add(jb);


        String btn_sign = "Sign Up";
        jb = new JButton(btn_sign);
        jb.setSize(100, 25);
        jb.addActionListener(this);
        jb.setLocation(150, 525);
        jb.setBackground(Color.WHITE);
        add(jb);

        String delete = "Delete";
        jb = new JButton(delete);
        jb.setSize(100, 25);
        jb.addActionListener(this);
        jb.setLocation(300, 525);
        jb.setBackground(Color.WHITE);
        add(jb);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Login") {
            String input_id = id.getText();
            String input_password = password.getText();
            String name = "";
            try {
                userNumID = user1.loginReturnNum(input_id, input_password);
                if(userNumID == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid input!!");
                }
                else { //로그인 성공!

                    name = user1.getUserName(input_id);
                    MainPage MainPage= new MainPage(name);
                    MainPage.name();
                    //MainPage mainPage = new MainPage(user1, article1, image1, userNumID);
                    //mainPage.createMainPage();
                    setVisible(false);
                    //jf.dispose();
                    //여기에 찬우가 만든 메인 패널 등장시킨다
                    // 찬우패널 ㅊㅇ = 찬우패널 (name); 이름을 전송해준다
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
        else if (e.getActionCommand() == "Sign Up") {
            SignupFrame usr = new SignupFrame(user1);
            usr.createSignupFrame();
        }

        else if(e.getActionCommand() == "forgotID"){
            forgotID f_id = new forgotID(user1);
            f_id.createFindIDframe();
        }

        else if(e.getActionCommand() == "forgotPW"){
            forgotPW f_pw = new forgotPW(user1);
            f_pw.createFindPWframe();
        }
        else if(e.getActionCommand() == "Delete"){
            String input_id = id.getText();
            String input_password = password.getText();
            String s="";
            s = user1.delete(input_id,input_password);
            //JOptionPane.showMessageDialog(null, s);
        }
    }
}