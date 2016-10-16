package aufgabe1;

class Thread_1 extends Thread {
	private Semaphore[] sems;

	public Thread_1(Semaphore[] sems, String name) {
		super(name);
		this.sems = sems;
		start();
	}

	private void Activity_1() {
		System.out.println("Activity_1 running; Aktive Threads: "+ 	currentThread().getThreadGroup().activeCount());

	}

	public void run() {
		Activity_1();
		sems[0].v();
		sems[1].v();
	}
	
	public  Integer getThreads(){
		 ThreadGroup tg = Thread.currentThread().getThreadGroup();
		 return tg.activeCount();
		 
	}
}
