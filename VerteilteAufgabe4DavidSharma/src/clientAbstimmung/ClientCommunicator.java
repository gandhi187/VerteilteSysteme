package clientAbstimmung;

import java.net.*;

import server.Reply;

import java.io.*;

public class ClientCommunicator {

  private final static int PORT = 7825;

  private Socket socket;
  private ObjectInputStream in;
  private ObjectOutputStream out;

  public ClientCommunicator(String server) {
    try { 
      socket = new Socket(server, PORT);		// Server  + Port
      out = new ObjectOutputStream(socket.getOutputStream()); // Out-Object (Abstimmungswert)
      in = new ObjectInputStream(socket.getInputStream());		//In-Object (Rückgabeparameter OK/ERROR)
    } 
    catch (Exception e) { e.printStackTrace(); }
  } 

  public int communicate(String wert) {   // Aufruf des Servers
	  int i = 0;
    try {
    	out.writeObject(wert);
    	i = (int) in.readObject();
    	
    }
    catch (Exception e) { e.printStackTrace(); }

    return i;
  } 

  public void stop() {
    try {
      out.close();
      in.close();
      socket.close();
    }
    catch (Exception e) { } 

    System.exit(0);
  }

} // ClientCommunicator