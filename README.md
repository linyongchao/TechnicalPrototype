# TechnicalPrototype

本工程用于做技术验证及整合之前的技术验证工程

# RMI

1. 通过 ```Naming.bind(String name, Remote obj)``` 方法可知，需要发布的接口必须继承 ```java.rmi.Remote```
2. 需要发布的实现类必须继承 ```java.rmi.server.UnicastRemoteObject```，否则会报错：

	```
	Caused by: java.io.NotSerializableException: my.rmi.impl.MathImpl
	at java.io.ObjectOutputStream.writeObject0(ObjectOutputStream.java:1184)
	at java.io.ObjectOutputStream.writeObject(ObjectOutputStream.java:348)
	... 3 more
	```
3. 需要发布的方法必须抛异常 ```throws RemoteException```，否则会报错：

	```
	Caused by: java.lang.IllegalArgumentException: illegal remote method encountered: public abstract long my.rmi.inte.Math.add(int,int)
	at sun.rmi.server.Util.checkMethod(Util.java:267)
	at sun.rmi.server.Util.getRemoteInterfaces(Util.java:246)
	at sun.rmi.server.Util.getRemoteInterfaces(Util.java:216)
	at sun.rmi.server.Util.createProxy(Util.java:146)
	at sun.rmi.server.UnicastServerRef.exportObject(UnicastServerRef.java:223)
	... 6 more
	```
4. 需要发布的接口在服务端和客户端必须完全一直（包括路径），否则会报错：

	```
	Caused by: java.lang.ClassNotFoundException: my.rmi.service.inte.Time (no security manager: RMI class loader disabled)
	at sun.rmi.server.LoaderHandler.loadProxyClass(LoaderHandler.java:556)
	at java.rmi.server.RMIClassLoader$2.loadProxyClass(RMIClassLoader.java:646)
	at java.rmi.server.RMIClassLoader.loadProxyClass(RMIClassLoader.java:311)
	at sun.rmi.server.MarshalInputStream.resolveProxyClass(MarshalInputStream.java:265)
	at java.io.ObjectInputStream.readProxyDesc(ObjectInputStream.java:1758)
	at java.io.ObjectInputStream.readClassDesc(ObjectInputStream.java:1710)
	at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:1986)
	at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1535)
	at java.io.ObjectInputStream.readObject(ObjectInputStream.java:422)
	... 3 more
	```
	
