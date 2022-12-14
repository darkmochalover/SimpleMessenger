package UI;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import domain.util.DButils;
import UI.ProfilePage;
import UI.ProfileEdit;
import domain.FollowInfo;
import domain.UserProfile;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchBox extends JFrame {

    private String self_id;

    public JTextField notice = new JTextField("아이디 혹은 이름 혹은 휴대폰 번호로 검색 가능합니다.");


    private JTextField searchable = new JTextField(30);
    private JButton searchB = new JButton("Search");
    private JTable result = new JTable();
    private JScrollPane scrollPane = new JScrollPane(result);

    /* Pop-up Menu */
    public static String option[] = {"프로필 수정", "상태 변경", "프로필 보기", "1:1 채팅하기", "1:1 게임하기"};
    PopupMenu profile_menu = new PopupMenu();
    MenuItem profile_item1 = new MenuItem(option[0]);
    MenuItem profile_item2 = new MenuItem(option[1]);
    MenuItem profile_item3 = new MenuItem(option[2]);
    MenuItem profile_item4 = new MenuItem(option[3]);
    MenuItem profile_item5 = new MenuItem(option[4]);
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
    private JPanel panel = new JPanel();
    private JPanel adc;
    /* --- */

    public static void main(String[] args) {
        new SearchBox("Search");
    }

    public SearchBox(String title) throws HeadlessException {
        super(title);
        setSize(530, 830);
        setResizable(true);
        addComponents();
        setTable();
        setVisible(true);
    }

    private void addComponents() {

        notice.setFont(new Font("Tahoma", Font.ITALIC, 13));
        notice.setEditable(false);
        notice.setHorizontalAlignment(JLabel.CENTER);
        notice.setBackground(new Color(255, 0, 0, 0));
        notice.setBounds(180, 480, 150, 31);

        panel.add(notice);
        panel.add(searchable);
        panel.add(searchB);
        panel.add(scrollPane);

        add(panel);
    }

    private void setTable()
    {
        searchB.addActionListener(e -> {
            try {
                ResultSet rs = new UserProfile().search(searchable.getText(), panel);
                result.setModel(DButils.buildPublicProfileTable(rs));

                ArrayList<UserProfile> info_list = null;
                try {
                    info_list = new UserProfile().ResultSetToList(rs);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            catch (ClassNotFoundException | SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });


        result.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3)
                {

                    // TODO Auto-generated method stub
                    int rowNum = result.getSelectedRow(); // 선택된 row 구하기.

                    // UserProfile info = info_list.get(rowNum);

                    System.out.println(rowNum); // for check...

                    // 팝업 메뉴에 아이템 추가하기.
                    // is self, "프로필 수정", "상태 변경" 버튼 추가
                    //                if(self_id.equals(info.getId()))
                    //                {
                    //                    profile_menu.add(profile_item1);
                    //                    profile_menu.add(profile_item2);
                    //                    profile_menu.addSeparator();
                    //                }
                    profile_menu.add(profile_item3);
                    profile_menu.add(profile_item4);
                    profile_menu.add(profile_item5);

                    add(profile_menu);

                    setSize(600, 600);
                    setVisible(true);

                    if (e.getButton() == MouseEvent.BUTTON3)
                    {
                        JTable table = (JTable) e.getSource();
                        int row = table.getSelectedRow();

                        if(row != -1) // 헤더 row가 아닐 시,
                        {
                            // 팝업 메뉴 보여주기.
                            //                		profile_menu.show(table, e.getX(), e.getY());
                            profile_menu.show(SearchBox.this, e.getX(), e.getY());
                        }
                    }
                }

                // 팝업 메뉴 보여주기 이벤트
                result.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mousePressed(MouseEvent me) {
                        // 오른쪽 마우스 버튼을 누르면 PopupMenu를 화면에 보여준다.
                        if (me.getButton() == MouseEvent.BUTTON3)
                        {
                            JTable table = (JTable) e.getSource();
                            int row = table.getSelectedRow();

                            if(row != -1) // 헤더 row가 아닐 시,
                            {
                                // 팝업 메뉴 보여주기.
//                        		profile_menu.show(table, me.getX(), me.getY());
                                profile_menu.show(SearchBox.this, me.getX(), me.getY());
                            }
                        }
                    }
                });
            }
        });


    }

}
