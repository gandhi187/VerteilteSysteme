import java.util.Scanner;

public class Main {
	public static int counter = 0;

	public static void main(String[] args) {

		int a = 30;
		int b = 10;

		calculate(30, 10, counter);

	}

	public static long calculate(int a, int b, int counter) {
		WorkerThread thread = new WorkerThread(a, b, Main.counter);
		thread.start();

		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(thread.ergebnis);
		// System.out.println(thread.threadcounter);
		System.out.println(counter);
		// System.out.println("Anzahl der Threads am Ende : " +
		// Thread.currentThread().getThreadGroup().activeCount());

		return (long) thread.ergebnis;

	}

}
