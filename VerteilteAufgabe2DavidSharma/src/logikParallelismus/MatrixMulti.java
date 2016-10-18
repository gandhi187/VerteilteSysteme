package logikParallelismus;

import java.util.Arrays;

public class MatrixMulti {

	int[][] matrix1 = { { 1, -2, 3, 4, -1 }, { -2, 3, 0, 1, 2 }, { 4, -1, 2, 1, -2 }, { -2, 1, 3, -1, 3 },
			{ 0, 2, -1, 2, 4 } };
	int[][] matrix2 = { { 2, -4, -1, 1, -2 }, { -1, 1, -2, 2, 1 }, { 5, 0, 3, -2, -4 }, { 1, -2, 1, 0, 2 },
			{ 2, 3, -3, 0, 0 } };
	int[][] ergMatrix = new int[matrix1.length][matrix2.length];

	
	public int[][] calculate(int anzahlThreads, int sleep) {
		ThreadPool masterThread = new ThreadPool(matrix1, matrix2, ergMatrix, anzahlThreads,sleep);
		masterThread.calculate();
		return getErgbMatrix();

	}

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
