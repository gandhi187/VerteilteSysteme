package Server;

import java.io.Serializable;

/**
 * 
 * 
 * @author DavidPC
 *	Model-Klasse (Antwortobjekt)
 *
 */

public class Produkt implements Serializable{

	private int nummer;
	private String beschreibung;

	public Produkt(int nummer, String beschreibung) {
		super();
		this.nummer = nummer;
		this.beschreibung = beschreibung;
	}

	@Override
	public String toString() {
		return "Produkt [nummer=" + nummer + ", beschreibung=" + beschreibung + "]";
	}

}
