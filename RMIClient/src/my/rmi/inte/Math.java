package my.rmi.inte;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author lin
 * @date 2022/1/30 02:05
 */
public interface Math extends Remote {

    public long add(int a, int b) throws RemoteException;

}
