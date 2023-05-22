package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket mSocket;
    private BufferedReader mIn; // 들어오는 통로
    private PrintWriter mOut; // 나가는 통로

    public Client(String ip, int port) {
        try {
            // 서버에 요청 보내기
            mSocket = new Socket(ip, port);

            // 통로 뚫기
            mIn = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
            mOut = new PrintWriter(mSocket.getOutputStream());

            // 메세지 전달
            mOut.println("응답하라!!");
            mOut.flush();

            // 응답 출력
            System.out.println(mIn.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(mSocket != null) mSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Client("localhost", 5555);
    }
}
