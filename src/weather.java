import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class weather {

    public weather() throws IOException{

        ApiExplorer API = new ApiExplorer();
        String t = API.accessOpenAPI();

        try{
            System.out.println(t + " in weather");
        }catch (Exception e){
            e.printStackTrace();
        }
        // 새로운 JFrame 생성
        JFrame weather = new JFrame();
        //Color background = new Color(0, 191, 155);
        JPanel c=new JPanel();
        weather.setBounds(800, 200, 450, 700);
        weather.setTitle("오늘의 기온");

        JScrollPane p = new JScrollPane();
        p.getViewport().setBackground(new Color(29, 111, 234));

        JLabel title = new JLabel("현재 기온");
        title.setFont(new Font("Gotham", Font.BOLD, 70));
        title.setForeground(Color.WHITE);
        // title.setSize(400, 250);
        title.setBounds(5,10,450,200);
        title.setHorizontalAlignment(title.CENTER);

        JLabel now = new JLabel(t + "℃");
        now.setFont(new Font("Gotham", Font.BOLD, 70));
        now.setForeground(Color.WHITE);
        now.setBounds(5,200,450,200);
        now.setHorizontalAlignment(now.CENTER);


        weather.add(c);
        weather.add(title);
        weather.add(now);


        weather.add(p);
        weather.setVisible(true);
    }
}
