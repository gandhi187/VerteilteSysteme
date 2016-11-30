package api;
import java.io.Serializable;

public class Umfrage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1376555815293860700L;
	private int zustimmung = 0;
	private int ablehnung = 0;
	private int enthaltung = 0;

	public Umfrage() {

	}

	public int getZustimmung() {
		return zustimmung;
	}

	public void setZustimmung() {
		zustimmung++;
	}

	public int getAblehnung() {
		return ablehnung;
	}

	public void setAblehnung() {
		ablehnung++;
		}

	public int getEnthaltung() {
		return enthaltung;
	}

	public void setEnthaltung() {
		enthaltung++;
	}

	@Override
	public String toString() {
		return "Umfrage [zustimmung=" + zustimmung + ", ablehnung=" + ablehnung + ", enthaltung=" + enthaltung + "]";
	}
	
	

}
