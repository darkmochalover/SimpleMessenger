import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class ChatHandlerObject extends Thread
{
	private ObjectInputStream reader;
	private ObjectOutputStream writer;
	private Socket socket;
	//private InfoDTO dto;
	///private Info command;
	private List <ChatHandlerObject> list;
	public ChatHandlerObject(Socket socket, List <ChatHandlerObject> list) throws IOException {
		
		this.socket = socket;
		this.list = list;
		writer = new ObjectOutputStream(socket.getOutputStream());
		reader = new ObjectInputStream(socket.getInputStream());
		
	}
	public void run(){
		InfoDTO dto = null;
		String nickName;
		try{
			while(true){
				dto=(InfoDTO)reader.readObject();
				nickName=dto.getNickName();
				
				//System.out.println("�迭 ũ��:"+ar.length);

				if(dto.getCommand()==Info.EXIT){
					InfoDTO sendDto = new InfoDTO();

					sendDto.setCommand(Info.EXIT);
					writer.writeObject(sendDto);
					writer.flush();

					reader.close();
					writer.close();
					socket.close();

					list.remove(this);

					sendDto.setCommand(Info.SEND);
					sendDto.setMessage(nickName+"님 퇴장하였습니다");
					broadcast(sendDto);
					break;
				} else if(dto.getCommand()==Info.JOIN){

					//nickName = dto.getNickName();

					InfoDTO sendDto = new InfoDTO();
					sendDto.setCommand(Info.SEND);
					sendDto.setMessage(nickName+"님 입장하였습니다");
					broadcast(sendDto);
				} else if(dto.getCommand()==Info.SEND){
					InfoDTO sendDto = new InfoDTO();
					sendDto.setCommand(Info.SEND);
					sendDto.setMessage("["+nickName+"]"+ dto.getMessage());
					broadcast(sendDto);
				}
			}//while

		} catch(IOException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	
		
	}

	public void broadcast(InfoDTO sendDto) throws IOException {
		for(ChatHandlerObject handler: list){
			handler.writer.writeObject(sendDto);
			handler.writer.flush();
		}
	}
}
