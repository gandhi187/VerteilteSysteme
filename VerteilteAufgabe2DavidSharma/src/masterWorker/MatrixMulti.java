package masterWorker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MatrixMulti {

	static int[][] matrix1 = { { 1, -2, 3, 4, -1 }, { -2, 3, 0, 1, 2 }, { 4, -1, 2, 1, -2 }, { -2, 1, 3, -1, 3 },
			{ 0, 2, -1, 2, 4 } };
	static int[][] matrix2 = { { 2, -4, -1, 1, -2 }, { -1, 1, -2, 2, 1 }, { 5, 0, 3, -2, -4 }, { 1, -2, 1, 0, 2 },
			{ 2, 3, -3, 0, 0 } };
	static int[][] ergMatrix = new int[matrix1.length][matrix2.length];


		public static void main (String[] args){
			MasterThread m = new MasterThread(matrix1, matrix2, ergMatrix, 15);
			m.setListe();
			m.calculate();
		}
	
	
	 
	// public int[][] calculate(int anzahlThreads) {
	//
	//
	//
	// }
	//



	

	public int[][] getErgbMatrix() {
		return ergMatrix;
	}

	public int[][] getMatrix1() {
		return matrix1;
	}

	public void setMatrix1(int[][] matrix1) {
		this.matrix1 = matrix1;
	}

	public int[][] getMatrix2() {
		return matrix2;
	}

	public void setMatrix2(int[][] matrix2) {
		this.matrix2 = matrix2;
	}

}
