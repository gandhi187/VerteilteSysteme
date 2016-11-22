package server;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

import client.InformationsClient;

public class InformationsServerImpl extends UnicastRemoteObject implements InformationsServer {
	private ArrayList<InformationsClient> allClients;

	public InformationsServerImpl() throws RemoteException {
		allClients = new ArrayList<InformationsClient>();
	}

		//Client hinzufügen
	public synchronized boolean addClient(InformationsClient objRef) throws RemoteException {
		String name = objRef.getName();
		for (Iterator<InformationsClient> iter = allClients.iterator(); iter.hasNext();) {
			InformationsClient cc = iter.next();
			try {
				if (cc.getName().equals(name)) {
					System.out.println("Client bereits registriert");
					return false;
				}
			} catch (RemoteException exc) {
				iter.remove();
			}
		}
		System.out.println("Client: " + objRef.getName() + " hinzugefügt");
		allClients.add(objRef);
		System.out.println("Verbundene Clients: " + allClients.size());

		return true;
	}

	public synchronized void removeClient(InformationsClient objRef) throws RemoteException {
		System.out.println("Client: " + objRef.getName() + " entfernt");
		allClients.remove(objRef);
	}

	
	//Nachricht verschicken
	public  void sendMessage() throws RemoteException {
		double zufallszahl = 0;

		while(true){
			//Zufallszahl generieren
			   zufallszahl = Math.random()*30;
			   
		for (Iterator<InformationsClient> iter = allClients.iterator(); iter.hasNext();) {
			InformationsClient cc = iter.next();
			try {
//				System.out.println("Nachricht an " + cc.getName());
				cc.print("Aktienwert: " + zufallszahl);
				 	
				
				try {
					Thread.sleep(9000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (RemoteException exc) {
				iter.remove();
			}
		}
		}
	}
}