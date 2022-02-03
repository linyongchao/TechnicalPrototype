package my.udt.thread;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import udt.UDTServerSocket;
import udt.UDTSocket;
import udt.util.Util;

/**
 * @author lin
 * @date 2022/2/4 00:22
 */
public class UdtServerThread extends Thread {

    ExecutorService pool;
    UDTServerSocket udtServerSocket;

    public UdtServerThread(String serverHost, int serverPort, int threadNum) throws UnknownHostException, SocketException {
        pool = Executors.newFixedThreadPool(threadNum);
        udtServerSocket = new UDTServerSocket(InetAddress.getByName(serverHost), serverPort);
        while (true) {
            try {
                UDTSocket udtSocket = udtServerSocket.accept();
                pool.execute(new Request(udtSocket));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class Request implements Runnable {

        final UDTSocket socket;

        public Request(UDTSocket socket) {
            this.socket = socket;
        }

        public void run() {
            while (true) {
                try {
                    String line = Util.readLine(socket.getInputStream());
                    if (line != null) {
                        System.out.println("接收消息: " + line);
                    }
                    if (line == null) {
                        break;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
