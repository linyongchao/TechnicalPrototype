package my.udt;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import udt.UDTClient;

/**
 * @author lin
 * @date 2022/2/3 23:38
 */
public class Main {

    public static void main(String[] args) throws Exception {
        oneTest();
        multiThreadTest(5);
    }

    static void oneTest() throws Exception {
        UDTClient client = getClient(12346);
        sendMessage(client, "Message send:one test");
    }

    static void multiThreadTest(int num) throws Exception {
        newThread(12347, "Message send:thread 1 test ", num);
        newThread(12348, "Message send:thread 2 test ", num);
    }

    private static void newThread(int clientPort, String context, int num) {
        new Thread(() -> {
            try {
                UDTClient client = getClient(clientPort);
                for (int i = 0; i < num; i++) {
                    sendMessage(client, context + i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static UDTClient getClient(int clientPort) throws Exception {
        return getClient("localhost", clientPort, "localhost", 12345);
    }

    private static UDTClient getClient(String clientHost, int clientPort, String serverHost, int serverPort) throws Exception {
        UDTClient client = new UDTClient(InetAddress.getByName(clientHost), clientPort);
        client.connect(serverHost, serverPort);
        return client;
    }

    private static void sendMessage(UDTClient client, String context) throws Exception {
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8"));
        pw.println(context);
        pw.flush();
    }
}
