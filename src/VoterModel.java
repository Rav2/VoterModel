import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class VoterModel {
	int numberOfSimulationSteps;
	int numberOfRealizations;
	String filePath;
	int[] states;
	double magnetization;
	int interfaces;
	ArrayList<ArrayList<Integer>> adjList;
	int size;
	int k;

	public VoterModel(ArrayList<ArrayList<Integer>> list, int simSteps, int realizations, String path, int kk){
		numberOfSimulationSteps = simSteps;
		numberOfRealizations = realizations;
		size = list.size();
		filePath = path;
		states = new int[size];
		drawStates(size);
		adjList = new ArrayList<ArrayList<Integer>>(size);
		adjList = list;
		magnetization = 0;
		interfaces = 0;
		k = kk;
	}

	//losuje opinie -1 lub 1
	void drawStates(int length){
		Random r = new Random();
		for (int i = 0; i < length; i++) {
			states[i]= 2 * r.nextInt(2) - 1;
		}
	}

	void magnetization(){
		magnetization = 0;
		for (int s : states){
			magnetization += s;
		}
		magnetization /= size;
	}

	void interfaces(){
		interfaces = 0;
		for (int i = 0; i < size; i++){
			for (int j = 0; j < adjList.get(i).size(); j++){
				if (states[i] != states[adjList.get(i).get(j)])
					interfaces ++;
			}
		}
		interfaces/=2;
	}

	void dynamics(ArrayList<ArrayList<Integer>> list) throws IOException {
		int randomAgent = 0;
		int randomNeighbour = 0;
		int takenOpinion = 0;
		Random r = new Random();
		File file = new File("./output/" + "file" + ".txt");
		FileWriter fileWriter = new FileWriter(file);
		File fileAveraged = new File("./output/" + "output_k" + Integer.toString(k) + ".txt");
		FileWriter fileWriterAveraged = new FileWriter(fileAveraged);
		Miscellaneous.writeToFile(fileWriter, "M\t" + "I \n");
		for (int k = 0; k < numberOfRealizations; k++){
			double averageMagnetization = 0;
			double averageInterfaces = 0;
			drawStates(size);	//los stanow
			for (int i = 0; i < numberOfSimulationSteps; i++){
				for (int j = 0; j < size; j++){
					//przejmowanie opinii
					randomAgent = r.nextInt(size);
					randomNeighbour = list.get(randomAgent).get(r.nextInt(list.get(randomAgent).size()));
					takenOpinion = states[randomNeighbour];
					//Wyswietlanie
					/*System.out.println("Przed: " + states[randomAgent] + " opinia sasiada" +  " "
							+ states[randomNeighbour] + " taken opinion: " +
							takenOpinion + " " + randomNeighbour);
					states[randomAgent] = takenOpinion;
					System.out.println("Po: " + states[randomAgent] + " " + randomAgent +
							" opinia sasiada" +  " " + states[randomNeighbour]
							+ " " + randomNeighbour);*/
				}

				magnetization();
				interfaces();
				//zapis do pliku
				fileWriter = new FileWriter(file,true);
				Miscellaneous.writeToFile(fileWriter, Math.round(magnetization * 1000.0) / 1000.0 + " " + interfaces + "\n");
				averageInterfaces += interfaces;
				averageMagnetization += magnetization;
			}
			averageInterfaces /= numberOfSimulationSteps;
			averageMagnetization /= numberOfSimulationSteps;
			fileWriterAveraged = new FileWriter(fileAveraged,true);
			Miscellaneous.writeToFile(fileWriterAveraged, Math.round(averageMagnetization * 1000.0) / 1000.0 + " " + averageInterfaces + "\n");
		}
	}


}
