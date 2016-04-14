import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class VoterModel {
	int numberOfRealizations;
	int[] states;
	double magnetization;
	int interfaces;
	ArrayList<ArrayList<Integer>> adjList;
	int size;
	int k;
	double p;
	int simSteps;
	ArrayList<ArrayList<Double>> M = new ArrayList<ArrayList<Double>>(size);
	ArrayList<ArrayList<Integer>> I = new ArrayList<ArrayList<Integer>>(size);

	public VoterModel(ArrayList<ArrayList<Integer>> list,int kk, double pp, int steps, int realizations){
		simSteps = steps;
		numberOfRealizations = realizations;
		size = list.size();
		states = new int[size];
		drawStates(size);
		adjList = new ArrayList<ArrayList<Integer>>(size);
		adjList = list;
		magnetization = 0;
		interfaces = 0;
		k = kk;
		p = pp;
	}

	//losuje opinie -1 lub 1
	void drawStates(int length){
		Random r = new Random(new Date().getTime());
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

	void dynamics(ArrayList<ArrayList<Integer>> topologyList) throws IOException {
		int randomAgent = 0;
		int randomNeighbour = 0;
		int takenOpinion = 0;
		Random r = new Random(new Date().getTime());

		File magnetizationFile = new File(Miscellaneous.makeFileName("M", false, k, p, simSteps, numberOfRealizations, size));
		FileWriter magnetizationFileWriter;
		File interfacesFile = new File(Miscellaneous.makeFileName("I", false, k, p, simSteps, numberOfRealizations, size));
		FileWriter interfacesFileWriter;
		magnetizationFileWriter = new FileWriter(magnetizationFile);
		interfacesFileWriter = new FileWriter(interfacesFile);

		for (int k = 0; k < numberOfRealizations; k++){
			drawStates(size);	//los stanow
			M.add(new ArrayList<Double>());
			I.add(new ArrayList<Integer>());
			for (int i = 0; i < simSteps; i++){
				for (int j = 0; j < size; j++){
					//przejmowanie opinii
					do
					{
						randomAgent = r.nextInt(size);
						//System.out.printf("\nIn while!\n");
					}
					while(topologyList.get(randomAgent).size()==0);
					int randNeighbourIndex = r.nextInt(topologyList.get(randomAgent).size());
					if(randNeighbourIndex < 0) System.out.println(randNeighbourIndex);
					randomNeighbour = topologyList.get(randomAgent).get(randNeighbourIndex);
					takenOpinion = states[randomNeighbour];
					states[randomAgent] = takenOpinion;
					//Wyswietlanie
					/*System.out.println("Przed: " + states[randomAgent] + " opinia sasiada" +  " "
							+ states[randomNeighbour] + " taken opinion: " +
							takenOpinion + " " + randomNeighbour);

					System.out.println("Po: " + states[randomAgent] + " " + randomAgent +
							" opinia sasiada" +  " " + states[randomNeighbour]
							+ " " + randomNeighbour);*/
				}

				magnetization();
				interfaces();
				//zapis do pliku
				magnetizationFileWriter = new FileWriter(magnetizationFile,true);
				interfacesFileWriter = new FileWriter(interfacesFile,true);
				Miscellaneous.writeToFile(magnetizationFileWriter, String.format("%.2f;", Math.round(Math.abs(magnetization) * 100.0) / 100.0));
				Miscellaneous.writeToFile(interfacesFileWriter,  Integer.toString(interfaces) + ";" );

				//uzupełnianie tablic do usrednienia
				M.get(k).add(Math.round(Math.abs(magnetization) * 100.0) / 100.0);
				I.get(k).add(interfaces);

			}
			magnetizationFileWriter = new FileWriter(magnetizationFile,true);
			interfacesFileWriter = new FileWriter(interfacesFile,true);
			Miscellaneous.writeToFile(magnetizationFileWriter, "\n");
			Miscellaneous.writeToFile(interfacesFileWriter,  "\n");

		}


		Miscellaneous.averageParametersToFile(k, p, simSteps, size, numberOfRealizations, M, I);
	}


}
