package Client;
/**
 * @author DavidPC

	Konsumenten-Client

 */
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import Server.MethodeInterface;
import Server.Produkt;

public class ClientKonsument {
	static MethodeInterface puffer = null;

	public static void main(String args[]) {

		Produkt p = null;

		try {
			// System.out.println("Lese Produkt aus");

			System.out.println(puffer.anzahlElemente());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(p);
	}

	public static void init(String server) {
		try {
			
			
			//Initialisieren mit Server
			puffer = (MethodeInterface) Naming.lookup("rmi://" + server + "/FIFOPuffer");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Client ist verbunden");
	}

	public static Produkt auslesen() {
		Produkt p = null;

		try {
			//entfernter Methodenaufruf
			p = puffer.auslesen();
			System.out.println();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return p;
	}

	public static int aktuellerStand() {
		try {
			return puffer.anzahlElemente();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static int pufferGroese() {
		try {
			return puffer.getGroesse();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

}
