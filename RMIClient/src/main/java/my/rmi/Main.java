package my.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import my.rmi.inte.Math;
import my.rmi.inte.Time;

/**
 * @author lin
 * @date 2022/1/30 01:46
 */
public class Main {

    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        int port = 54321;
        String timeUrl = "rmi://localhost:" + port + "/time";
        Time time = (Time) Naming.lookup(timeUrl);
        System.out.println(time.getNow());

        String mathUrl = "rmi://localhost:" + port + "/math";
        Math math = (Math) Naming.lookup(mathUrl);
        System.out.println(math.add(3, 4));
    }
}
