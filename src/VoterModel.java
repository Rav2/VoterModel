import java.util.ArrayList;
import java.util.Random;

public class VoterModel {
	int numberOfSimulationSteps;
	int numberOfRealizations;
	String filePath;
	int[] states;
	double magnetization;
	int interfaces;
	int numberOfInterfaces;
	ArrayList<ArrayList<Integer>> adjList;
	int size;

	public VoterModel(ArrayList<ArrayList<Integer>> list, int simSteps, int realizations, String path){
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

	void dynamics(ArrayList<ArrayList<Integer>> list){
		int randomAgent = 0;
		int randomNeighbour = 0;
		int takenOpinion = 0;
		Random r = new Random();
		for (int k = 0; k < numberOfRealizations; k++){
			drawStates(size);	//los stanow
			for (int i = 0; i < numberOfSimulationSteps; i++){
				for (int j = 0; j < size; j++){
					//przejmowanie opinii
					randomAgent = r.nextInt(size);
					randomNeighbour = list.get(randomAgent).get(r.nextInt(list.get(randomAgent).size()));
					takenOpinion = states[randomNeighbour];
					System.out.println("Przed: " + states[randomAgent] + " opinia sasiada" +  " "
							+ states[randomNeighbour] + " taken opinion: " +
							takenOpinion + " " + randomNeighbour);
					states[randomAgent] = takenOpinion;
					System.out.println("Po: " + states[randomAgent] + " " + randomAgent +
							" opinia sasiada" +  " " + states[randomNeighbour]
							+ " " + randomNeighbour);
				}

				magnetization();
				interfaces();
				//zapis do pliku
				Miscellaneous.writeToFile("test", "BEFORE\n" + Miscellaneous.displayList(size, list, true, states)
						+ "\nAFTER\n" + Miscellaneous.displayList(size, list, true, states) + "\n\n Magnetization: "
						+ magnetization + "\nInterfaces: " + interfaces);
			}
		}
	}


}
