package my.udt;

import my.udt.server.UdtServer;

/**
 * @author lin
 * @date 2022/2/3 01:00
 */
public class Main {

    public static void main(String[] args) throws Exception {
        new UdtServer("localhost", 12345, 3);
    }
}
