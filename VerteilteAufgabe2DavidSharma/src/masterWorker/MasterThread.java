package masterWorker;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class MasterThread {

	int[][] matrix1 = { { 1, -2, 3, 4, -1 }, { -2, 3, 0, 1, 2 }, { 4, -1, 2, 1, -2 }, { -2, 1, 3, -1, 3 },
			{ 0, 2, -1, 2, 4 } };
	int[][] matrix2 = { { 2, -4, -1, 1, -2 }, { -1, 1, -2, 2, 1 }, { 5, 0, 3, -2, -4 }, { 1, -2, 1, 0, 2 },
			{ 2, 3, -3, 0, 0 } };
	int[][] ergMatrix = new int[matrix1.length][matrix2.length];
	int threads;
	Queue<Aufgabe> aufgabenListe = new LinkedList<Aufgabe>();
	private Semaphore sem = new Semaphore(1);

	public MasterThread(int[][] matrix1, int[][] matrix2, int[][] ergMatrix, int threads) {
		super();
		this.matrix1 = matrix1;
		this.matrix2 = matrix2;
		this.ergMatrix = ergMatrix;
		this.threads = threads;

	}

	public void setListe() {

		for (int i = 0; i < 5; i++) { // aRow
			for (int j = 0; j < 5; j++) { // bColumn

				aufgabenListe.add(new Aufgabe(i, j));

			}
		}
	}

	public void calculate() {

		WorkerThread[] workerThread = new WorkerThread[threads];

		for (int a = 0; a < threads; a++) {
			workerThread[a] = new WorkerThread(matrix1, matrix2, ergMatrix, aufgabenListe, sem, a);
			workerThread[a].start();
			
		}
		
		for (int a = 0; a < threads; a++) {
			try {
				workerThread[a].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		System.out.println(Arrays.deepToString(ergMatrix));

	}

}
