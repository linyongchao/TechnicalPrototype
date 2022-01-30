package my.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import my.rmi.impl.MathImpl;
import my.rmi.impl.TimeImpl;

/**
 * @author lin
 * @date 2022/1/30 01:36
 */
public class Main {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
        int port = 54321;
        LocateRegistry.createRegistry(port);
        String timeUrl = "rmi://localhost:" + port + "/time";
        Naming.bind(timeUrl, new TimeImpl());
        String mathUrl = "rmi://localhost:" + port + "/math";
        Naming.bind(mathUrl, new MathImpl());
    }

}
