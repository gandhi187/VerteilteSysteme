package aufgabe1;

public class EventSynchronization extends Thread {

	public static void main(String[] args) throws InterruptedException {
		Semaphore[] sems = new Semaphore[9];
		for (int i = 0; i < sems.length; i++) {
			sems[i] = new Semaphore(0);
		}
		new Thread_5(sems, "Thread_5");
		sleep(600);
		new Thread_6(sems, "Thread_6");
		sleep(600);
		new Thread_7(sems, "Thread_7");
		sleep(600);
		new Thread_1(sems, "Thread_1");
		sleep(600);
		new Thread_2(sems, "Thread_2");
		sleep(600);
		new Thread_3(sems, "Thread_3");
		sleep(600);
		new Thread_4(sems, "Thread_4");

	}
	
	public static void getThreads(){
		 ThreadGroup tg = Thread.currentThread().getThreadGroup();
		    System.out.println(tg.activeCount());
		 
	}
}
