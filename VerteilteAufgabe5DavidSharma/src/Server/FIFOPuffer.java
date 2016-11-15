package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/**
 * 
 * @author DavidPC
 *	Puffer-Klasse 
 *
 */
public class FIFOPuffer extends UnicastRemoteObject implements MethodeInterface {
	private int in_index;
	private int out_index;
	private int groesse;
	private Produkt[] fifo;

	// Konstruktormethode dieser Klasse
	FIFOPuffer(int groesse)  throws RemoteException{
		if (groesse < 2)
			groesse = 2;
		this.groesse = groesse;
		fifo = new Produkt[groesse];
		in_index = out_index = 0;
	}

	// 'synchronized' Methoden
	// Element aus Puffer holen
	public synchronized Produkt auslesen() {
		Produkt temp;
		while (in_index == out_index) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		temp = fifo[out_index];
		if (out_index == groesse - 1)
			out_index = 0;
		else
			out_index++;
		notifyAll();
		System.out.println("Produkt ausgelesen");
		return temp;
	}

	// Element in Puffer stellen
	public synchronized void einfuegen(Produkt produkt) {
		while ((in_index + 1) % groesse == out_index) {
			try {
				System.out.println("warten..");
				wait();
			} catch (InterruptedException e) {
			}
		}
		fifo[in_index] = produkt;
		System.out.println("Produkt: " + produkt.toString() + " eingefügt");
		if (in_index == groesse - 1)
			in_index = 0;
		else
			in_index++;
		notifyAll();
	}
	
	public int getGroesse(){
		return groesse;
	}
	
	public int anzahlElemente(){
		int zaehler = 0; 
		for (int a = 0; a<fifo.length; a++){
			if (fifo[a]!=null){
				zaehler++;
			}
		}
		return zaehler;

	}
	
    public int AktuellerStand() {
        int temp = (in_index - out_index) % groesse;
        return temp;
}
	
}
