import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class forgotID implements ActionListener {
    JFrame forgot_id;

    JTextField phoneNumber, name;

    UserTable user1;
    public forgotID(UserTable user1) {
        this.user1 = user1;
    }

    public void createFindIDframe(){
        forgot_id = new JFrame();
        forgot_id.setSize(300, 300);
        forgot_id.setLocation(400, 400);
        forgot_id.setTitle("Kakaotalk forgot ID");
        forgot_id.setLayout(null);

        String[] label_name = {"Phonenumber", "name"};

        JLabel jl = new JLabel(label_name[0]);
        jl.setSize(80, 30);
        jl.setLocation(30, 60);
        jl.setHorizontalAlignment(JLabel.LEFT);

        forgot_id.add(jl);

        forgot_id.add(jl);

        jl = new JLabel(label_name[1]);
        jl.setSize(80, 30);
        jl.setLocation(30, 90);
        jl.setHorizontalAlignment(JLabel.LEFT);

        forgot_id.add(jl);

        phoneNumber = new JTextField();
        phoneNumber.setSize(130, 30);
        phoneNumber.setLocation(115, 60);
        forgot_id.add(phoneNumber);

        name = new JTextField();
        name.setSize(130, 30);
        name.setLocation(115,90);
        forgot_id.add(name);

        JButton look_for = new JButton("look for");
        look_for.setSize(100, 25);
        look_for.addActionListener(this);
        look_for.setLocation(100, 200);
        forgot_id.add(look_for);

        String btn_cancel = "Cancel";
        JButton jb = new JButton(btn_cancel);
        jb.setSize(100, 25);
        jb.addActionListener(this);
        jb.setLocation(100, 225);

        forgot_id.add(jb);

        forgot_id.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "look for") {
            String input_phoneNumber = new String(phoneNumber.getText());
            String input_name = new String(name.getText());
            String id = "";

            id = user1.getUserID(input_phoneNumber,input_name);
            JOptionPane.showMessageDialog(null, " your ID is : "+id);
        }

        else if (e.getActionCommand() == "Cancel") {
            forgot_id.setVisible(false);
        }
    }
}