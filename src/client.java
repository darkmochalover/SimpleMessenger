import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class client {

    public client(String[] args) {
        try {
            Socket socket = new Socket("192.168.219.104", 1234); // 소켓 서버에 접속
            String test = ("insert into userTables values ("+ "2" +", \"whdqls09\", \"092719\", \"parkjonghyun\", \"01077400935\")");
            System.out.println("socket 서버에 접속 성공!");
            PrintWriter pw = new PrintWriter(socket.getOutputStream());

            pw.println(test);
            pw.flush();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}