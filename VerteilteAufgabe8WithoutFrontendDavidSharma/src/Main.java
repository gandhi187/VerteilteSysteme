import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.naming.NamingException;

public class Main {

	public static void main(String[] args) throws JMSException, NamingException {
		// TODO Auto-generated method stub
		 List<String> moeglicheBefehle = new ArrayList<String>();
			moeglicheBefehle.add("Rückzug");
			moeglicheBefehle.add("Angriff");
			moeglicheBefehle.add("Stellung");
		 
		

		General g1 = new General(3,"Rückzug",moeglicheBefehle);
		Leutnant l1 = new Leutnant("David",0,moeglicheBefehle);
		Leutnant l2 = new Leutnant("Samet",0,moeglicheBefehle);
		Leutnant l3 = new Leutnant("Ricardo",0,moeglicheBefehle);
		
		List <String> queues = new ArrayList <String>();
		queues.add("queue/aufgabe8Queue1");
		queues.add("queue/aufgabe8Queue2");
		queues.add("queue/aufgabe8Queue3");
		
		
		g1.befehleSenden();
		l3.rundeEins(queues.get(0));
		l1.rundeEins(queues.get(1));
		l2.rundeEins(queues.get(2));

		
		l1.rundeZwei("queue/aufgabe8Queue2", "queue/aufgabe8Queue3");
		l2.rundeZwei("queue/aufgabe8Queue1", "queue/aufgabe8Queue3");
		l3.rundeZwei("queue/aufgabe8Queue1", "queue/aufgabe8Queue2");
		
		l1.rundeZweiEmpfangen("queue/aufgabe8Queue1");
		l2.rundeZweiEmpfangen("queue/aufgabe8Queue2");
		l3.rundeZweiEmpfangen("queue/aufgabe8Queue3");

		l1.vectorAusgeben();
		l2.vectorAusgeben();
		l3.vectorAusgeben();

		System.out.println(l1.getName() + " Übereinstimmungswert: " + l1.uebereinstimmung());
		System.out.println(l2.getName() + " Übereinstimmungswert: " +l2.uebereinstimmung());
		System.out.println(l3.getName() + " Übereinstimmungswert: " +l3.uebereinstimmung());
		
		System.out.println("Übereinstimmung aller "
				+ "drei Leutnants:  " + Leutnant.uebereinstimmung(l1.uebereinstimmung(), l2.uebereinstimmung(), l3.uebereinstimmung()));
		
	}

}
