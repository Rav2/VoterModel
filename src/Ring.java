import java.util.Random;

public class Ring 
{
	// Data fields
	int size;
	int fixedDegree;
	double probability;
	int[][] adjacencyMatrix;
	int[] degrees;
	
	// Constructors
	public Ring(int n) // constructor #1
	{
		size = n;
		fixedDegree = 2; // minimal degree for the ring is 2
		probability = 0.0;
		adjacencyMatrix = new int[size][size];
		degrees = new int[size];
		fillAdjacencyMatrix(fixedDegree);
	}
	
	public Ring(int n, int k) // constructor #2
	{
		size = n;
		fixedDegree = k;
		probability = 0.0;
		adjacencyMatrix = new int[size][size];
		degrees = new int[size];
		fillAdjacencyMatrix(fixedDegree);
	}
	
	public Ring(int n, int k, double p) // constructor #3
	{
		size = n;
		fixedDegree = k;
		probability = p;
		adjacencyMatrix = new int[size][size];
		degrees = new int[size];
		fillAdjacencyMatrix(fixedDegree);
		rewireConnections(probability);
	}
	
	// Computing methods
	public int[][] fillAdjacencyMatrix(int k) // create connection between nodes
	{
		adjacencyMatrix = new int[size][size]; // reset matrix
		degrees = new int[size]; // reset degrees distribution
		fixedDegree = k;
		
		/*
		W tym miejscu powinien znajdować się kod, który wygeneruje udekorowany pierścień.
		Dopuszczalne jest (a nawet nieuniknione), aby dla niektórych kombinacji size i fixedDegree nie wszystkie
		węzły miały dokładnie ten sam stopień (w mojej wersji programu ostatni węzeł czasem miał niższy stopień od pozostałych).
		Wskazówka: proszę zacząć od napisania algorytmu, który działa dla parzystych fixedDegree, a potem zastanowić się co zrobić z nieprzystymi.
		*/
		
		return adjacencyMatrix;
	}
	
	public int[][] rewireConnections(double p) // rewire connections with probability p
	{
		probability = p;
		Random r = new Random();
		int newNode = -1;
		
		// loop over existing connections
		for(int i=0; i<size-1; i++)
			for(int j=i+1; j<size; j++)
				if(adjacencyMatrix[i][j]>0)
				{
					if(r.nextDouble()<=probability)
					{
						// find new connection
						

						// update adjacency matrix
						



						// update degrees of the nodes
						

					}
				}
		
		return adjacencyMatrix;
	}

	public double computeAveragePathLength()
	{
		double apl = 0.0;
		/*
		Funkcja, która liczy średnią najkrótszą drogę w sieci.
		https://en.wikipedia.org/wiki/Average_path_length
		W sieci nieskierowanej d(i,j) = d(j,i) zatem we wzorze pojawia się liczba 2 w liczniku zamiast 1.
		*/
		return apl;
	} 
	
	// Getters
	public int getSize(){return size;}
	public int getFixedDegree(){return fixedDegree;}
	public int[][] getAdjacencyMatrix(){return adjacencyMatrix;}
	public double getProbability(){return probability;}
	public int[] getDegrees(){return degrees;}
	
	// For testing purposes
	public static void main(String[] args) 
	{
		Ring r1 = new Ring(36,11);
		int[][] am = r1.getAdjacencyMatrix();
		int[] deg = r1.getDegrees();
		for(int i=0; i<r1.getSize(); i++)
		{
			for(int j=0; j<r1.getSize(); j++) System.out.printf(" %d", am[i][j]);
			System.out.println();
		}
		System.out.println("\nDegrees:");
		for(int i=0; i<r1.getSize(); i++) System.out.printf(" %d", deg[i]);
		
	}

}
