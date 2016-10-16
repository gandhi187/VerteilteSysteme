package aufgabe1;

class Thread_2 extends Thread {
	private Semaphore[] sems;

	public Thread_2(Semaphore[] sems, String name) {
		super(name);
		this.sems = sems;
		start();
	}

	private void Activity_2() {
		System.out
				.println("Activity_2 running; Active Threads: "+ 	currentThread().getThreadGroup().activeCount());

	}

	public void run() {
		sems[0].p();
		Activity_2();
		sems[2].v();
		sems[3].v();
	}

}
