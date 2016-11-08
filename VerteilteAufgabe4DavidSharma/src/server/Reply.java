package server;

import java.io.Serializable;

public class Reply implements Serializable
{
  public final static int ERROR = 0;
  public final static int OK     = 1;  
  
  public static enum status {ERROR, OK};	 


  public int zustimmung;
  public int ablehnung;
  public int enthaltung;
  
  

  public Reply(int zustimmung, int ablehnung, int enthaltung) {
	super();
	this.zustimmung = zustimmung;
	this.ablehnung = ablehnung;
	this.enthaltung = enthaltung;
}



@Override
public String toString() {
	return "Reply [zustimmung=" + zustimmung + ", ablehnung=" + ablehnung + ", enthaltung=" + enthaltung + "]";
}



public Reply() { } 

} // Reply
