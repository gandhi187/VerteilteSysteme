package masterWorkerNew;


public class MasterThread {
	int[][] matrix1;
	int[][] matrix2;
	int[][] ergMatrix;
	int threads;

	
	static int threadCounter = 0;

	public MasterThread(int[][] matrix1, int[][] matrix2, int[][] ergMatrix, int threads) {
		super();
		this.matrix1 = matrix1;
		this.matrix2 = matrix2;
		this.ergMatrix = ergMatrix;
		this.threads=threads;

	}
	
	public void calculate(){
	int threadAnzahl = threads;

	WorkerThread[] f = new WorkerThread[threadAnzahl];
	for (int i = 0; i < f.length; i++) {
		f[i] = new WorkerThread(matrix1,matrix2,ergMatrix,i);
		f[i].start();
	}

	for (int x = 0; x < ergMatrix.length; x++) {
		for (int y = 0; y < ergMatrix[0].length; y++) {
			if (threadCounter == threadAnzahl) {
				threadCounter = 0;
			}
			if (f[threadCounter].threadReady) {
				f[threadCounter].feldBerechnen(x, y);
			} else {
				threadCounter++;
				f[threadCounter].feldBerechnen(x, y);
			}
			threadCounter++;

		}
	}

}


}
