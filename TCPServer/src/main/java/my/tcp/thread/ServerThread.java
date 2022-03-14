package my.tcp.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author lin
 * @date 2022/3/11 15:50
 */
public class ServerThread extends Thread {

    private Socket socket;
    private String client;

    public ServerThread(Socket socket, String client) {
        this.socket = socket;
        this.client = client;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8")) {
            // 客户端输入
            String info;
            while ((info = bufferedReader.readLine()) != null) {
                System.out.println("服务端接收：{'from_client':'" + client + "','data':'" + info + "'}");
            }
            socket.shutdownInput();

            // 返回客户端
            writer.write("返回客户端：{'to_client':'" + client + "','data':'服务器返回数据'}");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if (socket != null) {
                try {
                    socket.close();
                    System.out.println("socket 关闭");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
