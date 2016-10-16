package alternativeAufgabe1;

import java.util.concurrent.Semaphore;

public class Thread_4  extends Thread{
	private Semaphore[] sems;

	public Thread_4(Semaphore[] sems, String name) {
		super(name);
		this.sems = sems;
		start();
	}

	private void Activity_4() {
		System.out.println("Activity_4 running; Aktive Threads: "+ 	currentThread().getThreadGroup().activeCount());
	}

	public void run() {
		try {
			sems[2].acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		Activity_4();
		sems[6].release();
		
	}
}
