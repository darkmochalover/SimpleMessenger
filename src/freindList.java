import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
public class freindList extends JFrame{
    private JPanel mainPanel;
    private JPanel leftTab;
    private JButton settingButton;
    private JButton friendButton;
    private JButton chatButton;
    private JButton weather;
    private JPanel RightTab;
    private JPanel searchTab;
    private JButton searchButton;
    private JLabel status;
    private JPanel rMainTab;
    String names;
    public freindList(String name){
        names = name;
        setContentPane(mainPanel);
        setSize(535, 840);

        setTitle("Chat Service Main Page");
        setVisible(true);

        status.setText("<HTML><font size='5em'><text-align:right><b>친구</b></HTML>");

        chatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage fl = new MainPage(names);
                dispose();
            }
        });

        weather.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    weather myweather = new weather();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
