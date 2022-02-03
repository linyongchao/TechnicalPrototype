package my.udt.thread;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import udt.UDTServerSocket;
import udt.UDTSocket;

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

    static String readLine(InputStream r) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while (true) {
            int c = r.read();
            if (c < 0 && bos.size() == 0) {
                return null;
            }
            if (c < 0 || c == 10) {
                break;
            } else {
                bos.write(c);
            }
        }
        return bos.toString();
    }


    public static class Request implements Runnable {

        final UDTSocket socket;

        public Request(UDTSocket socket) {
            this.socket = socket;
        }

        public void run() {
            while (true) {
                try {
                    InputStream in = socket.getInputStream();
                    String line = readLine(in);
                    if (line != null) {
                        System.out.println("接收消息: " + line);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
