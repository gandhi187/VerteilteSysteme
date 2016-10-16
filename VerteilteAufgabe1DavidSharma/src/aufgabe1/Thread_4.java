package aufgabe1;

public class Thread_4  extends Thread{
	private Semaphore[] sems;

	public Thread_4(Semaphore[] sems, String name) {
		super(name);
		this.sems = sems;
		start();
	}

	private void Activity_4() {
		System.out.println("Activity_4 running; Active Threads: "+ 	currentThread().getThreadGroup().activeCount());

	}

	public void run() {
		sems[2].p();
		Activity_4();
		sems[6].v();
		
	}
}
