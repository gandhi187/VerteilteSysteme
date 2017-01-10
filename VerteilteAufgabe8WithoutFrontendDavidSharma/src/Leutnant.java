import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.ssl.SslRMIClientSocketFactory;

public class Leutnant {

	private static final String DESTINATION = "queue/myQueue1";
	private static final String USER = "guest";
	private static final String PASSWORD = "guest";

	private QueueConnection connection;
	private QueueSession session1;
	private QueueSession session2;
	private QueueSender sender;
	private Context ctx;

	private Vector<String> befehle = new Vector<String>(4);
	private List<String> moeglicheBefehle = new ArrayList<String>();
	private String name;
	private int fehlerhaft;

	/**
	 * 
	 * @param name
	 * @param fehlerhaft
	 * @param moeglicheBefehle 
	 * 1: Leutnant verhält sich fehlerhaft und schickt verschiedene Werte an andere Generäle
	 * 2: Leutnant verhält sich fehlerhaft und schickt den Korrekten Wert an einen und keinen Wert
	 * an den anderen
	 */
	public Leutnant(String name, int fehlerhaft, List<String> moeglicheBefehle) {
		this.name = name;
		this.fehlerhaft = fehlerhaft;
		this.moeglicheBefehle=moeglicheBefehle;
		try {
			getProperties();
		} catch (NamingException | JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void rundeEins(String QueueDestination) throws JMSException, NamingException {
		connection.start();
		
		Queue queue = (Queue) ctx.lookup(QueueDestination);
		QueueReceiver receiver = session1.createReceiver(queue);

		TextMessage messageIn = (TextMessage) receiver.receive();
		System.out.println("In der erste Runde hat " + name + " den Wert: " + messageIn.getText() + " erhalten");
		befehlErhalten(messageIn.getText());

	}

	public void rundeZwei(String QueueDestination1, String QueueDestination2) throws JMSException, NamingException {
		
		
		TextMessage messageOut = session1.createTextMessage();
		TextMessage messageOut2 = session1.createTextMessage();

		switch (fehlerhaft) {
		case 0:
			messageOut.setText(befehle.get(0));
			messageOut2.setText(befehle.get(0));

			break;
		case 1:
			messageOut.setText(getZufaelligerBefehl(befehle.get(0)));
			messageOut2.setText(getZufaelligerBefehl(befehle.get(0)));
			break;
			
		case 2:
			messageOut.setText(befehle.get(0));
			messageOut2.setText("");

			break;
		default:
			break;
		}
		
		Queue queue1 = (Queue) ctx.lookup(QueueDestination1);

		QueueSender sender = session1.createSender(queue1);
		sender.send(messageOut);
		System.out.println(name + " schickt den wert " + messageOut.getText());

		Queue queue2 = (Queue) ctx.lookup(QueueDestination2);
		sender = session1.createSender(queue2);
		sender.send(messageOut2);
		System.out.println(name + " schickt den wert " + messageOut2.getText());

		// befehlErhaltenQueue(QueueDestination1,QueueDestination2);

	}



	public void befehlErhalten(String wert) {
		befehle.add(wert);
	}

	public void rundeZweiEmpfangen(String QueueDestination1) throws JMSException, NamingException {
		Message message;
		Queue queue = (Queue) ctx.lookup(QueueDestination1);
		QueueReceiver receiver = session1.createReceiver(queue);

		TextMessage messageIn = (TextMessage) receiver.receive();
		System.out.println("In der zweiten Runde hat " + name + " den Wert: " + messageIn.getText() + " erhalten");
		TextMessage messageIn2 = (TextMessage) receiver.receive();
		System.out.println("In der zweiten Runde hat " + name + " den Wert: " + messageIn2.getText() + " erhalten");

		befehlErhalten(messageIn.getText());
		befehlErhalten(messageIn2.getText());
		session1.close();
	}

	public void vectorAusgeben() {
		System.out.println("Leutnant " + name + " hat folg. Befehle erh. " + befehle.toString());
	}

	public String uebereinstimmung() {
		Map.Entry<String, Long> maxEntry = null;

		Map<String, Long> counts = befehle.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

		for (Map.Entry<String, Long> entry : counts.entrySet()) {
			if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
				maxEntry = entry;
			}
		}
		int occurrences = Collections.frequency(befehle, maxEntry.getKey());
		if (occurrences <= 1) {
			return "Keine Übereinstimmung";
		}
		return maxEntry.getKey();
	}
	
	public static String uebereinstimmung(String wert1,String wert2, String wert3) {
		Map.Entry<String, Long> maxEntry = null;
		
		 Vector<String> befehle = new Vector<String>(4);
		befehle.add(wert1);
		befehle.add(wert2);
		befehle.add(wert3);

		Map<String, Long> counts = befehle.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

		for (Map.Entry<String, Long> entry : counts.entrySet()) {
			if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
				maxEntry = entry;
			}
		}
		int occurrences = Collections.frequency(befehle, maxEntry.getKey());
		if (occurrences <= 1) {
			return "Keine Übereinstimmung";
		}
		return maxEntry.getKey();
	}

	public void getProperties() throws NamingException, JMSException {

		// JNDI-Kontext erzeugen
		ctx = new InitialContext(JNDIProperties.getProperties());

		// ConnectionFactory über Namensdienst auslesen
		QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
		connection = factory.createQueueConnection(USER, PASSWORD);
		session1 = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		session2 = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

	}
	
	public String getName(){
		return this.name;
	}
	
	public String getZufaelligerBefehl(String befehl){
	 String befehlNeu = null;
		for (String s : moeglicheBefehle){
		 if (!befehl.equals(s)){
			 befehlNeu = s;
		 }
	 }
		moeglicheBefehle.remove(befehlNeu);
		return befehlNeu;
	}
	
}
