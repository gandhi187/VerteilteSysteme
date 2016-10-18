package aufgabe1;

public class Thread_5  extends Thread{
	private Semaphore[] sems;

	public Thread_5(Semaphore[] sems, String name) {
		super(name);
		this.sems = sems;
		start();
	}

	private void Activity_5() {
		System.out.println("Activity_5 running; Aktive Threads: "+ 	currentThread().getThreadGroup().activeCount());
	}

	public void run(){
		sems[3].p();
		sems[4].p();
		Activity_5();
		sems[7].v();
	}
	
}
