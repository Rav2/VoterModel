import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Ring
{
	// Data fields
	int size;
	int fixedDegree;
	double probability;
	ArrayList<ArrayList<Integer>> adjacencyList;
	int[] degrees;

	// Constructors
	public Ring(int n) // constructor #1
	{
		size = n;
		fixedDegree = 2; // minimal degree for the ring is 2
		probability = 1.0;
		adjacencyList = new ArrayList<ArrayList<Integer>>(size);
		for (int ii = 0; ii<size; ii++ )adjacencyList.add(new ArrayList<Integer>());
		degrees = new int[size];
		filladjacencyList(fixedDegree);
	}

	public Ring(int n, int k) throws Exception// constructor #2
	{
		if(k<2)
		{
			throw new Exception("Value of k must be at least 2!");
		}

		size = n;
		fixedDegree = k;
		probability = 1.0;
		adjacencyList = new ArrayList<ArrayList<Integer>>(size);
		for (int ii = 0; ii<size; ii++ )adjacencyList.add(new ArrayList<Integer>());
		degrees = new int[size];
		filladjacencyList(fixedDegree);
	}

	public Ring(int n, int k, double p) throws Exception// constructor #3
	{
		if(k<2)
		{
			throw new Exception("Value of k must be at least 2!");
		}

		size = n;
		fixedDegree = k;
		probability = p;
		adjacencyList = new ArrayList<ArrayList<Integer>>(size);
		for (int ii = 0; ii<size; ii++ )adjacencyList.add(new ArrayList<Integer>());
		degrees = new int[size];
		filladjacencyList(fixedDegree);
		rewireConnections(probability);
	}

	// Computing methods
	public ArrayList<ArrayList<Integer>> filladjacencyList(int k) // create connection between nodes
	{

		//LISTS ARE ENUMERATED STARTING FROM 0 UP TO SIZE-1
		adjacencyList = new ArrayList<ArrayList<Integer>>(size);
		for (int ii = 0; ii<size; ii++ )adjacencyList.add(new ArrayList<Integer>());
		// reset list
		degrees = new int[size]; // reset degrees distribution
		fixedDegree = k;

		if(k >= size-1)
		{
			//When k>= size-1 then the matrix is full of 1
			for(int i=0; i<size; i++)
				for(int j=i+1; j< size; j++)
				{
					adjacencyList.get(i).add(j);
					adjacencyList.get(j).add(i);
					degrees[i]++;
					degrees[j]++;
				}
			return adjacencyList;
		}
		else
		{
			int jump = (k + 1) / 2;
			for (int i = 0; i < size; i++)
			{
				for (int l = 0; l < Math.floor(k / 2); l++)
				{
					adjacencyList.get(i).add((i + l + 1) % size);
					adjacencyList.get(i).add((i - (l + 1) + size) % size);
					degrees[i]++;
					degrees[i]++;
				}

				if (k % 2 == 1)
				{
					if (Math.floor(i / jump) % 2 == 0 && (i + jump < size - 1))
					{
						adjacencyList.get(i).add(i + jump);
						adjacencyList.get(i + jump).add(i);
						degrees[i]++;
						degrees[i + jump]++;
					}
				}
			}
			return adjacencyList;
		}
	}

	public ArrayList<ArrayList<Integer>> rewireConnections(double p) // rewire connections with probability p
	{
		probability = p;
		Random r = new Random();
		for(int ii=0; ii<size; ii++)
		{
			int length = adjacencyList.get(ii).size();
			for(int jj=0; jj<length; jj++)
			{
				if(r.nextDouble() < probability)
				{
					Integer neighbour = adjacencyList.get(ii).get(jj);
					adjacencyList.get(ii).remove(jj);
					degrees[ii]--;
					for(int kk=0; kk<adjacencyList.get(neighbour).size(); kk++)
					{
						if(adjacencyList.get(neighbour).get(kk) == ii)
						{
							adjacencyList.get(neighbour).remove(kk);
							degrees[neighbour]--;
							kk = adjacencyList.get(neighbour).size();
						}
					}

					Integer newNeighbour = ii;
					do
					{
						newNeighbour = r.nextInt(size);
					}
					while (newNeighbour == ii || adjacencyList.get(ii).contains(newNeighbour));

					adjacencyList.get(ii).add(newNeighbour);
					adjacencyList.get(newNeighbour).add(ii);
					degrees[ii]++;
					degrees[newNeighbour]++;
				}
			}
		}
		return adjacencyList;
	}

	/*private int[] computeDegrees(ArrayList<ArrayList<Integer>> list)
	{
		int[] degreesArray = new int[size];
		for(int i=0; i<size; i++)
		{
			degreesArray[i] = list.get(i).size();
		}
		return degreesArray;
	}*/

	public double computeAveragePathLength()
	{
		double apl = 0.0;

		for (int i = 0; i < size; i++){
			for (int j = 0; j < adjacencyList.get(i).size(); j++){
				apl++;
			}
		}

		apl /= size * (size-1);

		/*
		Funkcja, ktĂłra liczy Ĺ›redniÄ… najkrĂłtszÄ… drogÄ™ w sieci.
		https://en.wikipedia.org/wiki/Average_path_length
		W sieci nieskierowanej d(i,j) = d(j,i) zatem we wzorze pojawia siÄ™ liczba 2 w liczniku zamiast 1.
		*/
		return apl;
	}

	public ArrayList<ArrayList<Integer>> sortList(ArrayList<ArrayList<Integer>> list)
	{
		for(int ii=0; ii<list.size(); ii++)
		{
			Collections.sort(list.get(ii));
		}
		return list;
	}


	// Getters
	public int getSize(){return size;}
	public int getFixedDegree(){return fixedDegree;}
	public ArrayList<ArrayList<Integer>> getadjacencyList(){return adjacencyList;}
	public double getProbability(){return probability;}
	public int[] getDegrees(){return degrees;}

	// For testing purposes
	public static void main(String[] args)
	{
		try
		{
			//TOPOLOGY
			Ring r1 = new Ring(30,3);
			ArrayList<ArrayList<Integer>> am = r1.sortList(r1.getadjacencyList());
			int[] deg = r1.getDegrees();
			VoterModel model = new VoterModel(r1.adjacencyList , 20, 1, 1, "");
			model.dynamics(r1.adjacencyList);

			String mes1 = Miscellaneous.displayList(r1.getSize(), am, true, model.states);
			System.out.println(mes1);
			System.out.println("\nDegrees:");
			for (int i = 0; i < r1.getSize(); i++) System.out.printf(" %d", deg[i]);
			System.out.printf("\nAverage degree: %.2f", Miscellaneous.average(deg));

			for(int kk=0; kk<10; kk++){r1.rewireConnections(0.5);}
			String mes2 = Miscellaneous.displayList(r1.getSize(), r1.sortList(am), true, model.states);
			System.out.print("\n" + mes2);

			System.out.println("\nDegrees:");
			for (int i = 0; i < r1.getSize(); i++) System.out.printf(" %d", deg[i]);
			System.out.printf("\nAverage degree: %.2f", Miscellaneous.average(deg));


			//DYNAMICS
			System.out.println("\n\nTesting dynamics\n");
			mes2 = Miscellaneous.displayList(r1.getSize(), r1.sortList(am), true, model.states);
			System.out.print(mes2);
			System.out.println("\nDegrees:");
			for (int i = 0; i < r1.getSize(); i++) System.out.printf(" %d", deg[i]);
			System.out.printf("\nAverage degree: %.2f", Miscellaneous.average(deg));

			//Miscellaneous.writeToFile("\ntest", "BEFORE\n" + mes1 + "\nAFTER\n" + mes2);

		}
		catch (Exception e)
		{
			System.out.print("\n");
			e.printStackTrace();
		}


	}

}
