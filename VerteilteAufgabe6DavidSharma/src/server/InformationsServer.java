package server;
import java.rmi.Remote;
import java.rmi.RemoteException;

import client.InformationsClient;

public interface InformationsServer extends Remote {
	
	
	/** 
	 * 
	 * @author DavidPC
	 * Schnittstellenbeschreibung Server / entfernte Methoden
	 *
	 */
	public boolean addClient(InformationsClient objRef) throws RemoteException;

	public void removeClient(InformationsClient objRef) throws RemoteException;

	public void sendMessage() throws RemoteException;
}
