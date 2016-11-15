package Server;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 
 * @author DavidPC
 *	Starten des Servers
 *
 */

public class Intro_Server {
	public static void main(String args[]) {
		try {
			// RMI auf PORT registrieren 
			Registry registry	= LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// Neuer Puffer erstellen (Größe = Übergabeparameter)
			FIFOPuffer fifoPuffer = new FIFOPuffer(4);
			//Objekt registrieren
			Naming.rebind("FIFOPuffer", fifoPuffer);
			System.out.println("Server gestartet warte auf Anfragen...");
			System.out.println("Port+ " + Registry.REGISTRY_PORT);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
}