import java.util.ArrayList;
public class Execution {
    double probab = 0.00;
    Execution( double p) {
        probab = p;


        try {
            //TOPOLOGY
            int fixedDegree = 2;
            int simSteps = 10;
            System.out.printf("Starting a new Execution with p=%f, k=%d, simSteps=%d\n", p, fixedDegree, simSteps);

            Ring r1 = new Ring(10, fixedDegree, probab);
            ArrayList<ArrayList<Integer>> am = r1.sortList(r1.getadjacencyList());
            int[] deg = r1.getDegrees();
            VoterModel model = new VoterModel(r1.adjacencyList, simSteps, simSteps, "", fixedDegree, probab);
            model.dynamics(r1.adjacencyList);
           // System.out.printf("average path length=%.3f\n", r1.computeAveragePathLength());


            Miscellaneous.readFile("./output/M_k" + fixedDegree + "_p" + String.format("%.2f", probab).substring(2, 4) + ".txt", "./output/I_k" + fixedDegree + "_p" + String.format("%.2f", probab).substring(2, 4) + ".txt", simSteps, fixedDegree, probab);
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
