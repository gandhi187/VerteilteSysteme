package logikParallelismus;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class WorkerThread extends Thread {

	private int matrixA[][];
	private int matrixB[][];
	int[][] ergebnismatrix;
	private int anzahl;
	private int runde;
	private String name;
	private Semaphore sem; 
	
	public WorkerThread(int matrixA[][], int matrixB[][], int ergmatrix[][], int anzahl, int runde ) {
		this.matrixA = matrixA;
		this.matrixB = matrixB;
		this.ergebnismatrix = ergmatrix;
		this.anzahl = anzahl;
		this.runde = runde;
		name = "Thread" + anzahl;
		
	}

	@Override
	public void run() {
	
		int zwischenErg = 0;

		// TODO Auto-generated method stub

		for (int a = runde; a < 25; a = a + anzahl) {

			for (int c = 0; c < matrixA.length; c++) {
				zwischenErg += matrixA[a % 5][c] * matrixB[c][a / 5]; // Speichern des Zwischenergebnisses

			}

			ergebnismatrix[a % 5][a / 5] = zwischenErg;
			zwischenErg = 0;
			System.out.println("Thread " + runde + "   " + Arrays.deepToString(ergebnismatrix));
		}

		
	}

	public int[][] getMatrix() {

		return ergebnismatrix;
	}

}
