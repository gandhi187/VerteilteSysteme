package server;

import javax.jms.*;
import javax.naming.*;

import api.JNDIProperties;
import api.Umfrage;

public class JMSServerInit {
	private static final String DESTINATION = "queue/myQueue1";
	private static final String USER = "guest";
	private static final String PASSWORD = "guest";

	private QueueConnection connection;
	private QueueSession session;
	private QueueReceiver receiver;
	private long timeout;
	private Server server;

	public JMSServerInit() throws NamingException, JMSException {

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

		// Empfänger erzeugen
		receiver = session.createReceiver(queue);

		// Empfang von Nachrichten starten
		connection.start();
	}

	// Aktives Warten auf Nachrichten (Pull-Prinzip)
	public void receiveMessage() throws JMSException {
		Message message;
		
		//Endlosschleife
		while (true) {

			System.out.println("Warte auf Nachrichten");
			message = receiver.receive(timeout);
			
			//Abfrage ob Client abstimmen will (ObjectMessage)
			if (message instanceof ObjectMessage) {
				
				server = new Server();
				ObjectMessage textMessage = (ObjectMessage) message;
				Object object = ((ObjectMessage) message).getObject();
				Umfrage u = (Umfrage) object;

				server.abstimmen(u);
				System.out.println("Client hat abgestimmt");

			//Abfrage ob Client abfragen will (TextMessage)
			} else if (message instanceof TextMessage) {
				server = new Server();
				TextMessage textMessage = (TextMessage) message;
				System.out.println(textMessage.getText());

				System.out.println(server.umfrageWerte());
				System.out.println("Client hat abgefragt");

			}
		}
	}

	// Ressourcen freigeben
	public void close() throws JMSException {
		receiver.close();
		session.close();
		connection.close();
	}

	public static void main(String[] args) throws Exception {

		JMSServerInit consumer = new JMSServerInit();
		consumer.receiveMessage();
		consumer.close();
	}
}
