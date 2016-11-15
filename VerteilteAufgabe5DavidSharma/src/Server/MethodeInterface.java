package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/** 
 * 
 * @author DavidPC
 * Schnittstellenbeschreibung / entfernte Methoden
 *
 */
public interface MethodeInterface extends Remote {
	public Produkt auslesen() throws RemoteException;
	public void einfuegen(Produkt produkt) throws RemoteException;
	public int getGroesse()throws RemoteException;
	public int anzahlElemente() throws RemoteException;
	public int AktuellerStand() throws RemoteException;
}
