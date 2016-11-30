package clientAbstimmung;

import javax.jms.*;
import javax.naming.*;

import api.JNDIProperties;
import api.Umfrage;

public class ClientAbstimmungJMS {
	private static final String DESTINATION = "queue/myQueue1";
	private static final String USER = "guest";
	private static final String PASSWORD = "guest";

	private QueueConnection connection;
	private static QueueSession session;
	private static QueueSender sender;
	private String text;
	private static long expiration;

	public ClientAbstimmungJMS( long expiration) throws NamingException, JMSException {

		this.text = text;
		this.expiration = expiration;

		// JNDI-Kontext erzeugen
		Context ctx = new InitialContext(JNDIProperties.getProperties());

		// ConnectionFactory über Namensdienst auslesen
		QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");

		// Zieladresse über Namensdienst auslesen
		Queue queue = (Queue) ctx.lookup(DESTINATION);

		// Verbindung aufbauen
		connection = factory.createQueueConnection(USER, PASSWORD);

		// Session erzeugen
		session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

		// Sender erzeugen
		sender = session.createSender(queue);
	}

	// Nachricht erzeugen und senden
	public static void sendMessage(Umfrage u) throws JMSException {

		
		ObjectMessage messageO = session.createObjectMessage();
		messageO.setObject(u);
		
		sender.setTimeToLive(expiration);
		sender.send(messageO);
	}

	// Ressourcen freigeben
	public void close() throws JMSException {
		sender.close();
		session.close();
		connection.close();
	}

	public static void main(String[] args) throws Exception {

		Umfrage u = new Umfrage ();
		u.setAblehnung();
		
		ClientAbstimmungJMS producer = new ClientAbstimmungJMS( 0);
		sendMessage(u);
		long expiration = (args.length == 2) ? Long.parseLong(args[1]) : 0;

		

		producer.close();
	}
}
