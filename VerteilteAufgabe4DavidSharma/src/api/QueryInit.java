package api;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class QueryInit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -60885014882581930L;
	String filename = "Umfrage.txt";
	

	public synchronized void speichern(String antwort) {
		Umfrage u = ausgeben();
		if(antwort.equalsIgnoreCase("Ja")){
			u.setZustimmung();
		}
		if(antwort.equalsIgnoreCase("Nein")){
			u.setAblehnung();
		}
		if(antwort.equalsIgnoreCase("Enthaltung")){
			u.setEnthaltung();
		}
		
		
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(u);
			oos.flush();
			fos.close();
			oos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public synchronized void reset(){
		Umfrage u = new Umfrage();
		
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(u);
			oos.flush();
			fos.close();
			oos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Umfrage ausgeben() {
		Umfrage u = null;
		FileInputStream fis = null;
		ObjectInputStream ois;

		try {
			fis = new FileInputStream(filename);
			ois = new ObjectInputStream(fis);
			 u = (Umfrage) ois.readObject();
			 fis.close();
			 ois.close();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u;

	}
}
