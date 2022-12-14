import UI.SearchBox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class MainPage extends JFrame{
    private JPanel leftTab;
    private JPanel RightTab;
    private JPanel searchTab;
    private JPanel rMainTab;
    private JPanel mainPanel;
    private JButton searchButton;
    private JButton settingButton;
    private JButton friendButton;
    private JButton chatButton;
    private JLabel status;
    private JButton weather;
    private JButton cB;
    private String title;

    private Socket socket;
    //private InfoDTO dto;
    ///private Info command;
    private List<ChatHandlerObject> list;
    String names;

    public MainPage(String name){
        setContentPane(mainPanel);
        setSize(535, 840);

        setTitle("Chat Service Main Page");
        setVisible(true);
        names = name;
        status.setText("<HTML><font size='5em'><text-align:right><b>채팅</b></HTML>");
        float rightAlignment = JScrollBar.RIGHT_ALIGNMENT;

        cB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ChatClientObject cc = new ChatClientObject(names);
                //ChatClientObject2 cc2 = new ChatClientObject2(names);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchBox sb = new SearchBox(title);
            }
        });

        friendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                freindList fl = new freindList(names);
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

    public void name() {
    }
}
