package aufgabe1;

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
		sems[5].p();
		Activity_6();
		sems[8].v();
	}
}
