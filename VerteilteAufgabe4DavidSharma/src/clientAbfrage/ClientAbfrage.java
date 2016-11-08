package clientAbfrage;

import javax.naming.CommunicationException;

import server.Reply;

public class ClientAbfrage {

	private ClientCommunicator communicator;
	
	public ClientAbfrage(String server){
		communicator = new ClientCommunicator(server);
	}
	
	public static void main (String[] args){
		ClientAbfrage clientAbfrage = new ClientAbfrage(args[0]);
//		abfragen();

	}
	
	public Reply abfragen(){
		Reply reply = communicator.communicate("Abfrage");
		System.out.println(reply);

		return reply;
	}
	
}
