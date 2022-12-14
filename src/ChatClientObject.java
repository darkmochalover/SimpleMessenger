import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.io.*;

class  ChatClientObject extends JFrame implements ActionListener,Runnable
{
	private JTextArea output;
	private JTextField input;
	private JButton sendBtn;
	private Socket socket;
	private ObjectInputStream reader=null;
	private ObjectOutputStream writer=null;
	//private String msg;
	//private InfoDTO dto;
	private String nickName;
	static String names;

	public ChatClientObject(String name) {
		names = name;
		//���Ϳ� TextArea�����
		output = new JTextArea();
		output.setFont(new Font("Tahoma",Font.BOLD,15));
		output.setEditable(false);
		JScrollPane scroll = new JScrollPane(output);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  //�׻� ��ũ�ѹٰ� ���η� ������

		//�ϴܿ� ��ư�� TextArea�ֱ�
		JPanel bottom = new JPanel();
		bottom.setLayout(new BorderLayout());
		input = new JTextField();

		sendBtn = new JButton("전송");

		bottom.add("Center",input);  //���Ϳ� ���̱�
		bottom.add("East",sendBtn);  //���ʿ� ���̱�
		//container�� ���̱�
		Container c = this.getContentPane();
		c.add("Center", scroll);  //���Ϳ� ���̱�
		c.add("South", bottom);  //���ʿ� ���̱�
		//������ â ����
		setBounds(300,300,300,300);
		setVisible(true);


		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				//System.exit(0);
				try{
					//InfoDTO dto = new InfoDTO(nickName,Info.EXIT);
					InfoDTO dto = new InfoDTO();
					dto.setNickName(nickName);
					dto.setCommand(Info.EXIT);
					writer.writeObject(dto);  //���������� �ʿ䰡 ����
					writer.flush();
				}catch(IOException io){
					io.printStackTrace();
				}
			}
		});
	}

	public void service(){
		//���� IP �Է¹ޱ�
		//String serverIP = JOptionPane.showInputDialog(this, "����IP�� �Է��ϼ���","����IP",JOptionPane.INFORMATION_MESSAGE);
		String serverIP= "127.0.0.1";  //�⺻������ ������ ���� �ԷµǾ� ���� ��
		nickName= "상대방1";

		try{
			socket = new Socket(serverIP,9500);
			//���� �߻�
			reader= new ObjectInputStream(socket.getInputStream());
			writer = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("전송 준비 완료!!");

		} catch(UnknownHostException e ){
			System.out.println("서버를 찾을 수 없습니다.");
			e.printStackTrace();
			System.exit(0);
		} catch(IOException e){
			System.out.println("서버와 연결이 안되었습니다.");
			e.printStackTrace();
			System.exit(0);
		}
		try{
			//������ �г��� ������

			InfoDTO dto = new InfoDTO();
			dto.setCommand(Info.JOIN);
			dto.setNickName(nickName);
			writer.writeObject(dto);  //���������� �ʿ䰡 ����
			writer.flush();
		}catch(IOException e){
			e.printStackTrace();
		}

		//������ ����

		Thread t = new Thread(this);
		t.start();
		input.addActionListener(this);
		sendBtn.addActionListener(this);  //�߼� �̺�Ʈ �߰�
	}
	//������ �������̵�
	@Override
	public void run(){
		//�����κ��� ������ �ޱ�
		InfoDTO dto= null;
		while(true){
			try{
				dto = (InfoDTO) reader.readObject();
				if(dto.getCommand()==Info.EXIT){  //�����κ��� �� �ڽ��� exit�� ������ �����
					reader.close();
					writer.close();
					socket.close();
					System.exit(0);
				} else if(dto.getCommand()==Info.SEND){
					output.append(dto.getMessage()+"\n");

					int pos=output.getText().length();
					output.setCaretPosition(pos);
				}
			}catch(IOException e){
				e.printStackTrace();
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}
		}
	}
	//ActionPerformed
	@Override
	public void actionPerformed(ActionEvent e){
		try{
			//������ ����
			//JTextField���� �����κ�����
			//���� ����
			String msg=input.getText();
			InfoDTO dto = new InfoDTO();
			//dto.setNickName(nickName);
			if(msg.equals("exit")){
				dto.setCommand(Info.EXIT);
			} else {
				dto.setCommand(Info.SEND);
				dto.setMessage(msg);
				dto.setNickName(nickName);
			}
			writer.writeObject(dto);
			writer.flush();
			input.setText("");

		}catch(IOException io){
			io.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		new ChatClientObject(names).service();
	}
}
//���� ä���� ���� �����带 �������־�� ��