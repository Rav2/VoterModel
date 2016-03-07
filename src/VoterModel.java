import java.util.Random;
public class VoterModel {
	int numberOfSimulationSteps;
	int numberOfRealizations;
	String filePath;
	//int[][] adjacencyMatrix;
	
	public VoterModel(int[][] matrix, int simSteps, int realizations, String path){
		numberOfSimulationSteps = simSteps;
		numberOfRealizations = realizations;
		filePath = path;
		/*adjacencyMatrix = new int[matrix.length][matrix.length];
		for (int i = 0; i < matrix.length; i++){
			for (int j = 0; j < matrix.length; j++){
				adjacencyMatrix[i][j] = matrix[i][j];
			}
		}*/
	}
	
	void changeRandomNeighbour(int[][] matrix){
		//choosing random neighbour
		Random rand = new Random();
		int i=-1, j=-1;
		do{
			i = rand.nextInt(matrix.length-1);
			j = rand.nextInt(matrix.length-1);
		}
		while(matrix[i][j] != 1);
		
		//disconnection
		matrix[i][j] = 0;
		matrix[j][i] = 0;
		
		//connection
		i=-1; j=-1;
		do{
			i = rand.nextInt(matrix.length-1);
			j = rand.nextInt(matrix.length-1);
		}
		while(matrix[i][j] == 1);
		matrix[i][j] = 1;
		matrix[j][i] = 1;
		
	}
	
	void dynamics(int[][] matrix){
		for (int i = 0; i < numberOfRealizations; i++){
			for (int j = 0; j < numberOfSimulationSteps; j++){
				changeRandomNeighbour(matrix);
			}
		}
	}
	
	
}
