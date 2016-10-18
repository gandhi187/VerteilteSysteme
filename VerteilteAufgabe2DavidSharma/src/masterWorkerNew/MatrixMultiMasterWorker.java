package masterWorkerNew;


public class MatrixMultiMasterWorker {
	int[][] matrix1 = { { 1, -2, 3, 4, -1 }, { -2, 3, 0, 1, 2 }, { 4, -1, 2, 1, -2 }, { -2, 1, 3, -1, 3 },
			{ 0, 2, -1, 2, 4 } };
	int[][] matrix2 = { { 2, -4, -1, 1, -2 }, { -1, 1, -2, 2, 1 }, { 5, 0, 3, -2, -4 }, { 1, -2, 1, 0, 2 },
			{ 2, 3, -3, 0, 0 } };
	int[][] ergMatrix = new int[matrix1.length][matrix2.length];

	boolean fredReady = true;
	static int fredCounter = 0;
	
	public int[][] calculate(int anzahlThreads, int sleep) {
		MasterThread masterThread = new MasterThread(matrix1, matrix2, ergMatrix, anzahlThreads);
		masterThread.calculate();
		return getErgbMatrix();

	}

	public int[][] getErgbMatrix() {
		return ergMatrix;
	}

	
	
}
