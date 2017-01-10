import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class General {
	
	private static final String USER = "guest";
	private static final String PASSWORD = "guest";

	private QueueConnection connection;
	private QueueSession session;
	private Context ctx;
	
	private Map <String,String> queues = new HashMap <String,String>();
	private List <String> moeglicheBefehle = new ArrayList<>() ;
	
	private int fehlerhaft;
	private String wert;

	/**
	 * 
	 * @param fehlerhaft
	 * @param wert  
	 * Wert: 1 - General verhält sich fehlerhaft und schickt in ersten Runde, gleichen und anderen Wert
	 * an den dritten Leutnant
	 * Wert: 2 - General verhält sich fehlerhaftg und schickt in ersten Runde, gleichen Wert an zwei
	 * Generäle und keinen Wert an den Dritten
	 * Wert : 3 - General verhält sich fehlerhaft und schickt in ersten Runde verschiedenen Wert an zwei Generäle
	 * und keinen Wert an dritten General
	 * @param moeglicheBefehle
	 * @throws NamingException
	 * @throws JMSException
	 */
	public General(int fehlerhaft, String wert, List<String> moeglicheBefehle) throws NamingException, JMSException {
		this.fehlerhaft = fehlerhaft;
		this.wert = wert;
		getProperties();
		this.moeglicheBefehle=moeglicheBefehle;

		initMaps(fehlerhaft, wert);

	}

	
	public void befehleSenden() throws NamingException, JMSException{
		for (Map.Entry<String, String> entry : queues.entrySet())
		{
			TextMessage message = session.createTextMessage();

			Queue queue = (Queue) ctx.lookup(entry.getKey());
			QueueSender sender = session.createSender(queue);
			
			message.setText(entry.getValue());
		
			sender.send(message);
		}
		
		
		
	}
	
	
	public void getProperties() throws NamingException, JMSException {

		// JNDI-Kontext erzeugen
		ctx = new InitialContext(JNDIProperties.getProperties());

		// ConnectionFactory über Namensdienst auslesen
		QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
		connection = factory.createQueueConnection(USER, PASSWORD);
		session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

	}
	
	public void initMaps (int fehlerhaft,String wert){
		
		switch (fehlerhaft) {
		case 0:
			queues.put("queue/aufgabe8Queue1", wert);
			queues.put("queue/aufgabe8Queue2",wert);
			queues.put("queue/aufgabe8Queue3",wert);
			break;
		case 1:
			queues.put("queue/aufgabe8Queue1", wert);
			queues.put("queue/aufgabe8Queue2",wert);
			queues.put("queue/aufgabe8Queue3",getZufaelligerBefehl(wert));
			break;
		case 2:
			queues.put("queue/aufgabe8Queue1", wert);
			queues.put("queue/aufgabe8Queue2",wert);
			queues.put("queue/aufgabe8Queue3","");
			break;
			
		case 3:
			queues.put("queue/aufgabe8Queue1", getZufaelligerBefehl(wert));
			queues.put("queue/aufgabe8Queue2",getZufaelligerBefehl(wert));
			queues.put("queue/aufgabe8Queue3","");
			break;
		default:
			break;
		}
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
