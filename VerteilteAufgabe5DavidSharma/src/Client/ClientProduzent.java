package Client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import Server.MethodeInterface;
import Server.Produkt;
/**
 * 
 * @author DavidPC
 *	Produzenten-Client
 *
 */
public class ClientProduzent {
	
	private static final String SERVER = "localhost";
	static MethodeInterface puffer = null;
	public static void main(String args[]) {
		
		init(SERVER);
		
		Produkt p = new Produkt (12,"Test");
		einfuegen(p);
	
	}
	
	public static void init(String server){
		 puffer = null;
		try {
			//Initalisieren mit Server
			 puffer = (MethodeInterface) Naming.lookup("rmi://" + server + "/FIFOPuffer");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Client ist verbunden");
	}
	
	public static void einfuegen(Produkt p){
		try {
			//entfernter Methodenaufruf
			puffer.einfuegen(p);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static int aktuellerStand(){
		try {
			return puffer.AktuellerStand();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static int pufferGroese(){
		try {
			return puffer.anzahlElemente();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
		
	}

}
