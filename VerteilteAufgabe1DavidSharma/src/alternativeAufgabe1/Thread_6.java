package alternativeAufgabe1;

import java.util.concurrent.Semaphore;

public class Thread_6 extends Thread {
	private Semaphore[] sems;

	public Thread_6(Semaphore[] sems, String name) {
		super(name);
		this.sems = sems;
		start();
	}

	private void Activity_6() {
		System.out.println("Activity_6 running; Aktive Threads: "+ 	currentThread().getThreadGroup().activeCount());
	}

	public void run() {
		try {
			sems[5].acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		Activity_6();
		sems[8].release();
	}
}
