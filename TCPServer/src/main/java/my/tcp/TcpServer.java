package my.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import my.tcp.thread.ServerThread;

/**
 * @author lin
 * @date 2022/3/11 15:49
 */
public class TcpServer {

    public static void main(String[] args) {
        try {
            //创建socket监听服务器端口
            ServerSocket serverSocket = new ServerSocket(6060);
            //记录客户端数量
            int count = 0;
            System.out.println("服务器启动");
            //定义一个死循环，不停的接收客户端连接
            while (true) {
                //阻塞，直到有客户端链接
                Socket socket = serverSocket.accept();
                //客户端唯一标识：ip + 端口
                String ip = socket.getInetAddress().getHostAddress();
                int port = socket.getPort();
                String clientUnique = ip + ":" + port;
                // 每个客户端使用一个线程监听
                ServerThread thread = new ServerThread(socket, clientUnique);
                thread.start();
                //客户端计数
                count++;
                System.out.println("客户端数量：" + count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
