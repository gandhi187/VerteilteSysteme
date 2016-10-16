package aufgabe1;

class Thread_3 extends Thread {
	private Semaphore[] sems;

	public Thread_3(Semaphore[] sems, String name) {
		super(name);
		this.sems = sems;
		start();
	}

	private void Activity_3() {
		System.out.println("Activity_3 running; Active Threads: "+ 	currentThread().getThreadGroup().activeCount());
	}

	public void run() {
		sems[1].p();
		Activity_3();
		sems[4].v();
		sems[5].v();
	}
}