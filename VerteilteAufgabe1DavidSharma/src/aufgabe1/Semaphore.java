package aufgabe1;

public class Semaphore {
	private int value;

	public Semaphore(int init) {
		if (init < 0)
			init = 0;
		value = init;
	}

	//betreten
	public synchronized void p() {
		
		// warten bis Variable 0 == 0 ist (Abschnitt betreten)
		while (value == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		value--;
	}

	//verlassen
	public synchronized void v() {
		//Variable erhöhren 
		value++;
		
		//Thread benarichtigen, dass freigegeben
		notify();
	}
}