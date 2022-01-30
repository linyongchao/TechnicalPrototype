package my.rmi.inte;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author lin
 * @date 2022/1/30 01:45
 */
public interface Time extends Remote {

    public String getNow() throws RemoteException;

}
