import java.util.ArrayList;
import java.util.List;

public class WorkerThread extends Thread {

	private int n;
	private int k;
	public double ergebnis;
	public int threadcounter; 

	public WorkerThread(int n, int k, int counter) {
		this.n = n;
		this.k = k;
		threadcounter=counter;
	}

	public void run() {

		try {
			ergebnis = binCoeff(n,k);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public double getErgebnis(){
		return ergebnis;
	}
	
	double binCoeff(int n, int k) throws InterruptedException {
		
//		 System.out.println("Thread: " + threadcounter + "rechnet " + n  + " " + k);
		if (k == 0) return 1;
		else if (k > n) return 0;
		else {
			Main.counter++;
			
			WorkerThread wk = new WorkerThread(n-1,k-1,Main.counter);
			
			wk.start(); // Rekursion 
			System.out.println("Anzahl Threads : " + Thread.currentThread().getThreadGroup().activeCount());
			System.out.println("Thread: " + threadcounter + " rechnet");
			wk.join();
			return (((double)n/k) * wk.getErgebnis()); 
			
		}
	}
}
