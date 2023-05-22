package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket mServerSocket;
    private Socket mSocket;
    private BufferedReader mIn; // 들어오는 통로
    private PrintWriter mOut; // 나가는 통로

    public Server() {
        try {
            mServerSocket = new ServerSocket(5555);
            mSocket = mServerSocket.accept();
            System.out.println("클라이언트와 연결 됨");

            mIn = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
            mOut = new PrintWriter(mSocket.getOutputStream());

            // 클라이언트에서 보낸 문자열 출력
            System.out.println(mIn.readLine());

            // 클라이언트에 문자열 전송
            mOut.println("전송 잘 되었음"); // 계속 쌓임
            mOut.flush();   // 쌓인 문자열을 이 때 쏘게 됨
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 소켓 닫기
                if(mSocket != null) mSocket.close();
                if(mServerSocket != null) mServerSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
    }
}
