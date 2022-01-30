package my.rmi.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import my.rmi.inte.Time;

/**
 * @author lin
 * @date 2022/1/30 01:33
 */
public class TimeImpl extends UnicastRemoteObject implements Time {

    public TimeImpl() throws RemoteException {
    }

    @Override
    public String getNow() throws RemoteException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
