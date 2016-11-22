package client;
import java.rmi.RemoteException;
/** 
 * 
 * @author DavidPC
 * Schnittstellenbeschreibung Client / entfernte Methoden
 *
 */
public interface InformationsClient extends java.rmi.Remote {
	public String getName() throws RemoteException;

	public void print(String msg) throws RemoteException;
}