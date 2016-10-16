package aufgabe1;

public class Thread_7 extends Thread {
	private Semaphore[] sems;

	public Thread_7(Semaphore[] sems, String name) {
		super(name);
		this.sems = sems;
		start();
	}

	private void Activity_7() {
		System.out.println("Activity_7 running; Active Threads: "+ 	currentThread().getThreadGroup().activeCount());
	}

	public void run() {
		sems[6].p();
		sems[7].p();
		sems[8].p();
		Activity_7();
		
	}
}
