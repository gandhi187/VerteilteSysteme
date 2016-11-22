package client;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class InformationsClientImpl extends UnicastRemoteObject implements InformationsClient {
	private String name;

public InformationsClientImpl(String n) throws RemoteException
{
name = n;
}

	public String getName() throws RemoteException {
		return name;
	}

	public void print(String msg) throws RemoteException {
		System.out.println(msg);
	}
}