package clientAbfrage;

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
      socket = new Socket(server, PORT);
      out = new ObjectOutputStream(socket.getOutputStream());
      in = new ObjectInputStream(socket.getInputStream());
    } 
    catch (Exception e) { e.printStackTrace(); }
  } 

  public Reply communicate(String wert) {  
    Reply reply = null;

    try {
        out.writeObject(wert);
      reply = (Reply) in.readObject();
     System.out.println("hat in gelesen");
    }
    catch (Exception e) { e.printStackTrace(); }

    return reply;
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