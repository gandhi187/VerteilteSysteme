package masterWorkerNew;

public class WorkerThread  extends Thread{

	int[][] matrix1;
	int[][] matrix2;
	int[][] ergMatrix;
	public boolean threadReady = true;
	
	
	public WorkerThread(int[][] matrix1, int[][] matrix2, int[][] ergMatrix,int threadName) {
		super();
		this.matrix1 = matrix1;
		this.matrix2 = matrix2;
		this.ergMatrix = ergMatrix;
		this.setName("ThreadNr" + threadName);
	}


	
	public void run() {
		System.out.println(this.getName() + " gestartet!");
	}

	public void feldBerechnen(int x, int y) {
		int feldwert = 0;
		for (int i = 0; i < matrix1.length; i++) {
			// System.out.println("Rechnung: " + matrixA[y][i] + " mal " +
			// matrixB[i][x] );
			feldwert += (matrix1[y][i] * matrix2[i][x]);
		}
		ergMatrix[x][y] = feldwert;
		System.out.println(this.getName() + " hat Wert von x=" + x + " und y=" + y + " gerechnet: " + feldwert);
	}
	
}
