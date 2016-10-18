package masterWorker;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class WorkerThread extends Thread {

	int[][] matrix1;
	int[][] matrix2;
	int[][] ergMatrix;
	Queue<Aufgabe> aufgabenListe;
	private Semaphore sem;
	private int name;

	public WorkerThread(int[][] matrix1, int[][] matrix2, int[][] ergMatrix, Queue<Aufgabe> aufgabenListe,
			Semaphore sem, int a) {
		super();
		this.matrix1 = matrix1;
		this.matrix2 = matrix2;
		this.ergMatrix = ergMatrix;
		this.aufgabenListe = aufgabenListe;
		this.sem = sem;
		this.name=a;
	}

	public void run() {

		while (true) {
			try {
				sem.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (!aufgabenListe.isEmpty()) {

				Aufgabe a = aufgabenListe.poll();
				sem.release();

				for (int b = 0; b < 5; b++) {

					ergMatrix[a.getReihe2()][a.getReihe1()] += (matrix1[a.getReihe2()][b] * matrix2[b][a.getReihe1()]);
					System.out.println("Thread: "+ name + " ; " + matrix1[a.getReihe2()][b] + "  *  " + matrix2[b][a.getReihe2()]);
				}
			}

			else {
				sem.release();
				break;
			}

		}
	}

}
