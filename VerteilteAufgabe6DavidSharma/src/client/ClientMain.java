package client;

import java.rmi.*;

import server.InformationsServer;

import java.io.*;

public class ClientMain {
	
	static InformationsClientImpl client ;
	static InformationsServer server;
	
	public static void main(String[] args) {
		
	
//		System.exit(0);
	}

	public static void abonnieren(String serverName, String name){
		try {
			server = (InformationsServer) Naming.lookup("rmi://" + serverName + "/Server");
			client = new InformationsClientImpl(name);
			if (server.addClient(client)) {
				server.sendMessage();
			} else {
				System.out.println("Name exists already");
			}
		} catch (Exception e) {
		}
	}
	public static void entfernen(){
		try {
			server.removeClient(client);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}