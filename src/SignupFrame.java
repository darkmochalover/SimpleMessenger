import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SignupFrame implements ActionListener {
    JTextField new_id, new_password, new_name, new_nickname;
    JFrame sign_jf;
    UserTable user1;
    ArticleTable article1;

    public SignupFrame(UserTable user1) {
        this.user1 = user1;
    }

    public void createSignupFrame() {
        sign_jf = new JFrame();
        sign_jf.setSize(300, 300);
        sign_jf.setLocation(400, 400);
        sign_jf.setTitle("Kakaotalk Sign Up");
        sign_jf.setLayout(null);

        String[] label_name = {"ID", "password"};

        JLabel jl = new JLabel(label_name[0]);
        jl.setSize(80, 30);
        jl.setLocation(30, 30);
        jl.setHorizontalAlignment(JLabel.LEFT);

        sign_jf.add(jl);

        jl = new JLabel(label_name[1]);
        jl.setSize(80, 30);
        jl.setLocation(30, 60);
        jl.setHorizontalAlignment(JLabel.LEFT);

        sign_jf.add(jl);

        jl = new JLabel("Name");
        jl.setSize(80, 30);
        jl.setLocation(30, 90);
        jl.setHorizontalAlignment(JLabel.LEFT);

        sign_jf.add(jl);

        jl = new JLabel("Phonenumber");
        jl.setSize(80, 30);
        jl.setLocation(30, 120);
        jl.setHorizontalAlignment(JLabel.LEFT);

        sign_jf.add(jl);

        new_id = new JTextField();
        new_id.setSize(130, 30);
        new_id.setLocation(110, 30);

        sign_jf.add(new_id);

        new_password = new JTextField();
        new_password.setSize(130, 30);
        new_password.setLocation(110, 60);

        sign_jf.add(new_password);

        new_name = new JTextField();
        new_name.setSize(130, 30);
        new_name.setLocation(110, 90);

        sign_jf.add(new_name);

        new_nickname = new JTextField();
        new_nickname.setSize(130, 30);
        new_nickname.setLocation(110, 120);

        sign_jf.add(new_nickname);

        String btn_make = "Sign Up";
        JButton jb = new JButton(btn_make);
        jb.setSize(100, 25);
        jb.addActionListener(this);
        jb.setLocation(100, 200);

        sign_jf.add(jb);

        String btn_cancel = "Cancel";
        jb = new JButton(btn_cancel);
        jb.setSize(100, 25);
        jb.addActionListener(this);
        jb.setLocation(100, 225);

        sign_jf.add(jb);

        sign_jf.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Sign Up") {
            String input_id = new String(new_id.getText());
            String input_password = new String(new_password.getText());
            String input_name = new String(new_name.getText());
            String phonenumber = new String(new_nickname.getText());

            if(user1.insertAUser(input_id, input_password, input_name, phonenumber))
                JOptionPane.showMessageDialog(null, "Sign Up Success");
            else
                JOptionPane.showMessageDialog(null, "already exist!");
            sign_jf.dispose(); // 회원가입 성공 후 회원가입 창 끄기

        }
        else if (e.getActionCommand() == "Cancel") {
            sign_jf.setVisible(false);
        }
    }
}
