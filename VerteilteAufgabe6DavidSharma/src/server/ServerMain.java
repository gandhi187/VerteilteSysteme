package server;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {
	public static void main(String[] args) {
		try {
			InformationsServerImpl server = new InformationsServerImpl();
			try {
				// RMI auf PORT registrieren 
				Registry registry	= LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Naming.rebind("Server", server);
			System.out.println("Waiting for Clients..");
			
		} catch (Exception e) {
		}
	}
}