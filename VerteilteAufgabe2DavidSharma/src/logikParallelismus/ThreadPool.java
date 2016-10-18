package logikParallelismus;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class ThreadPool {

	private int[][] matrixA;
	private int[][] matrixB;
	private int[][] ergMatrix;
	private int anzahlThreads;
	private WorkerThread[] threadpool;
	Semaphore sem = new Semaphore (0);
	private int sleep;

	public ThreadPool(int[][] matrixA, int[][] matrixB, int[][] ergMatrix, int anzahlThreads, int sleep) {

		this.matrixA = matrixA;
		this.matrixB = matrixB;
		this.ergMatrix = ergMatrix;
		this.anzahlThreads = anzahlThreads;
		threadpool = new WorkerThread[anzahlThreads];
		this.sleep=sleep;
	}

	public void calculate() {

		for (int a = 0; a < anzahlThreads; a++) {

			threadpool[a] = new WorkerThread(matrixA, matrixB, ergMatrix, anzahlThreads, a);
			threadpool[a].start();

			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int a = 0; a < anzahlThreads; a++) {
			try {
				threadpool[a].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}


	}

}
