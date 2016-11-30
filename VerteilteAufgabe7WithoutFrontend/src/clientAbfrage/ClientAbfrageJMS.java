package clientAbfrage;

import javax.jms.*;
import javax.naming.*;

import api.JNDIProperties;
import api.Umfrage;

public class ClientAbfrageJMS {
	private static final String DESTINATION = "queue/myQueue1";
	private static final String DESTINATION2 = "queue/aufgabe7Queue2";
	private static final String USER = "guest";
	private static final String PASSWORD = "guest";

	private QueueConnection connection;
	private static QueueSession session;
	private static QueueSender sender;
	private String text;
	private static long expiration;
	private static QueueReceiver receiver;

	public ClientAbfrageJMS(long expiration) throws NamingException, JMSException {

		this.text = text;
		this.expiration = expiration;

		// JNDI-Kontext erzeugen
		Context ctx = new InitialContext(JNDIProperties.getProperties());

		// ConnectionFactory über Namensdienst auslesen
		QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");

		// Zieladresse über Namensdienst auslesen
		Queue queue = (Queue) ctx.lookup(DESTINATION);
		Queue queue2 = (Queue) ctx.lookup(DESTINATION2);

		// Verbindung aufbauen
		connection = factory.createQueueConnection(USER, PASSWORD);
		// Session erzeugen
		session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

		receiver = session.createReceiver(queue2);

		// Sender erzeugen
		sender = session.createSender(queue);
		// Empfang der AntwortNachrichten starten
		connection.start();
	}

	// Nachricht erzeugen und senden
	public static void sendMessage() throws JMSException {

		//
		TextMessage message = session.createTextMessage();
		message.setText("Abfrage");

		sender.setTimeToLive(expiration);
		sender.send(message);
		sender.close();

		// Antwort-Nachricht empfangen
		Message answerMessage;
		answerMessage = receiver.receive(0);

		TextMessage answerTextMessage = (TextMessage) answerMessage;
		System.out.println(answerTextMessage.getText());

	}

	// Ressourcen freigeben
	public void close() throws JMSException {
		sender.close();
		session.close();
		connection.close();
	}

	public static void main(String[] args) throws Exception {

		ClientAbfrageJMS producer = new ClientAbfrageJMS(0);
		sendMessage();
		producer.close();

	}
}
