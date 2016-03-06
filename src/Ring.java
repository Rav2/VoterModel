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
	
	public Ring(int n, int k) throws Exception// constructor #2
	{
		if(k<2)
		{
			throw new Exception("Value of k must be at least 2!");
		}

		size = n;
		fixedDegree = k;
		probability = 0.0;
		adjacencyMatrix = new int[size][size];
		degrees = new int[size];
		fillAdjacencyMatrix(fixedDegree);
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

		if(k >= size-1)
		{
			//When k>= size-1 then the matrix is full of 1
			for(int i=0; i<size; i++)
				for(int j=i+1; j< size; j++)
				{
					adjacencyMatrix[i][j]=1;
					adjacencyMatrix[j][i]=1;
				}
			degrees=computeDegrees(adjacencyMatrix);
			return adjacencyMatrix;
		}
		else
		{
			int jump = (k + 1) / 2;
			for (int i = 0; i < size; i++)
			{
				for (int l = 0; l < Math.floor(k / 2); l++)
				{
					adjacencyMatrix[i][(i + l + 1) % size] = 1;
					adjacencyMatrix[i][(i - (l + 1) + size) % size] = 1;
				}

				if (k % 2 == 1)
				{
					if (Math.floor(i / jump) % 2 == 0 && (i + jump < size - 1))
					{
						adjacencyMatrix[i][i + jump] = 1;
						adjacencyMatrix[i + jump][i] = 1;
					}
				}
			}
			degrees=computeDegrees(adjacencyMatrix);
			return adjacencyMatrix;
		}
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

	private int[] computeDegrees(int[][] matrix)
	{
		int[] degreesArray = new int[size];
		for(int i=0; i<size; i++)
			for(int j=i; j<size; j++)
			{
				if(matrix[i][j]==1)
				{
					degreesArray[i] = degreesArray[i]+1;
					degreesArray[j] = degreesArray[j]+1;
				}
			}
		return degreesArray;
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

	//Additional functions
	public static float average(int[] array)
	{
		float sum = 0;
		for(int x:array){sum += x;}
		return sum / (float)(array.length);
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
		try
		{
			Ring r1 = new Ring(32,12);
			int[][] am = r1.getAdjacencyMatrix();
			int[] deg = r1.getDegrees();
			for (int i = 0; i < r1.getSize(); i++)
			{
				for (int j = 0; j < r1.getSize(); j++) System.out.printf(" %d", am[i][j]);
				System.out.println();
			}
			System.out.println("\nDegrees:");
			for (int i = 0; i < r1.getSize(); i++) System.out.printf(" %d", deg[i]);
			System.out.printf("\nAverage degree: %.2f", average(deg));
		}
		catch (Exception e)
		{
			System.out.printf("Exception catched! %n %s", e.getMessage());
		}

		
	}

}
