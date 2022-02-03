package my.udt;

import my.udt.thread.UdtServerThread;

/**
 * @author lin
 * @date 2022/2/3 01:00
 */
public class Main {

    public static void main(String[] args) throws Exception {
        UdtServerThread thread = new UdtServerThread("localhost", 12345, 10);
        thread.start();
    }
}
