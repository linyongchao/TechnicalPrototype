package my.rmi.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import my.rmi.inte.Math;

/**
 * @author lin
 * @date 2022/1/30 01:33
 */
public class MathImpl extends UnicastRemoteObject implements Math {

    public MathImpl() throws RemoteException {
    }

    @Override
    public long add(int a, int b) throws RemoteException {
        return a + b;
    }
}
