package server;

import java.io.*;

import api.QueryInit;
import api.Umfrage;

public class Server {
	Umfrage u;

	public Server() {
	}

	public Reply umfrageWerte() {


		Reply reply;
		QueryInit qI = new QueryInit(); 

		System.out.println("Rufe Ergebnisse ab");
		try {

			u = qI.ausgeben(); // Aktueller Wert aus Datei abrufen

			reply = new Reply(u.getZustimmung(), u.getAblehnung(), u.getEnthaltung());

		} catch (Exception e) {

			reply = new Reply();
		}

		System.out.println();
		return reply;
	}
	
	public void abstimmen(String wert){
		
		QueryInit qI = new QueryInit();
		if (wert.equals("reset")){
			qI.reset();
			return;
		}
		qI.speichern(wert);
	}

} // Server
