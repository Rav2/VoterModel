import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
public class Execution {
    Execution( int fixedDegree, double probab, int simSteps, int realizations, int size, File aplFile, FileWriter aplWriter) {
        try {
            //TOPOLOGY
            System.out.printf("\nStarting a new Execution with k=%d, p=%f, simSteps=%d, size=%d\n", fixedDegree, probab,  simSteps, size);

            Ring r1 = new Ring(size, fixedDegree, probab);
            ArrayList<ArrayList<Integer>> am = r1.sortList(r1.getadjacencyList());
            int[] deg = r1.getDegrees();
            VoterModel model = new VoterModel(r1.adjacencyList, fixedDegree, probab, simSteps, realizations);
            model.dynamics(r1.adjacencyList);
            double apl = r1.computeAveragePathLength();
            System.out.printf("average path length=%.3f\n", apl);
            Miscellaneous.readFile(Miscellaneous.makeFileName("M", false, fixedDegree, probab, simSteps, size),
                    Miscellaneous.makeFileName("I", false, fixedDegree, probab, simSteps, size), fixedDegree, probab, simSteps, size);
            aplWriter = new FileWriter(aplFile, true);
            Miscellaneous.writeToFile(aplWriter, String.format("%.3f;\n",apl));

            //wyswietlanie

            /*String mes1 = Miscellaneous.displayList(r1.getSize(), am, true, model.states);
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
*/
        }
         catch (Exception e) {
            System.out.print("\n");
            e.printStackTrace();
            Miscellaneous.handleExceptions(e);
        }


    }
}
