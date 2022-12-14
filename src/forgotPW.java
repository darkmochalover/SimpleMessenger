import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class forgotPW implements ActionListener {
    JFrame forgot_pw;

    JTextField phoneNumber, name, ID;

    UserTable user1;
    public forgotPW(UserTable user1) {

        this.user1 = user1;
    }
    public void createFindPWframe(){
        forgot_pw = new JFrame();
        forgot_pw.setSize(300, 300);
        forgot_pw.setLocation(400, 400);
        forgot_pw.setTitle("Kakaotalk forgot PW");
        forgot_pw.setLayout(null);

        String[] label_name = {"Phonenumber", "name"};

        JLabel jl = new JLabel(label_name[0]);
        jl.setSize(80, 30);
        jl.setLocation(30, 60);
        jl.setHorizontalAlignment(JLabel.LEFT);

        forgot_pw.add(jl);

        forgot_pw.add(jl);

        jl = new JLabel(label_name[1]);
        jl.setSize(80, 30);
        jl.setLocation(30, 90);
        jl.setHorizontalAlignment(JLabel.LEFT);

        forgot_pw.add(jl);

        jl = new JLabel("ID");
        jl.setSize(80, 30);
        jl.setLocation(30, 120);
        jl.setHorizontalAlignment(JLabel.LEFT);

        forgot_pw.add(jl);

        phoneNumber = new JTextField();
        phoneNumber.setSize(130, 30);
        phoneNumber.setLocation(115, 60);
        forgot_pw.add(phoneNumber);

        name = new JTextField();
        name.setSize(130, 30);
        name.setLocation(115,90);
        forgot_pw.add(name);

        ID = new JTextField();
        ID.setSize(130, 30);
        ID.setLocation(115,120);
        forgot_pw.add(ID);

        JButton look_for = new JButton("look for");
        look_for.setSize(100, 25);
        look_for.addActionListener(this);
        look_for.setLocation(100, 200);
        forgot_pw.add(look_for);

        String btn_cancel = "Cancel";
        JButton jb = new JButton(btn_cancel);
        jb.setSize(100, 25);
        jb.addActionListener(this);
        jb.setLocation(100, 225);

        forgot_pw.add(jb);

        forgot_pw.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "look for") {
            String input_phoneNumber = new String(phoneNumber.getText());
            String input_name = new String(name.getText());
            String input_user_id = new String(ID.getText());
            String pw = "";

            pw = user1.getUserPW(input_phoneNumber,input_name, input_user_id);
            JOptionPane.showMessageDialog(null, " your PW is : "+pw);
        }

        else if (e.getActionCommand() == "Cancel") {
            forgot_pw.setVisible(false);
        }
    }
}