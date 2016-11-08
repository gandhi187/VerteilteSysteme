package clientAbstimmung;

import server.Reply;

public class ClientAbstimmung {
	
	private ClientCommunicator communicator;

	public ClientAbstimmung(String server, String wert) {
		communicator = new ClientCommunicator(server);
		int i = communicator.communicate(wert);
		if( i == 0){
			System.out.println("ERROR");
		}
		else {
		System.out.println("OK");
		}
	}

	public static void main(String[] args) {
		ClientAbstimmung clientAbstimmung= new ClientAbstimmung(args[0],args[1]);
	}

}