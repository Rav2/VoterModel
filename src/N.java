import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.System.currentTimeMillis;

/**
 * Created by Kasia on 07.04.16.
 */
public class N {
    public static void main(String[] args) throws IOException {
        long start, koniec;
        start = currentTimeMillis();
        for (int deg = 2; deg <= 6; deg++) {
            int fixedDegree = deg;
            int simulationSteps = 5;
            int realizations = 10;
            int size = 100;
            File aplFile = new File("./output/" + "apl" + "_k" + fixedDegree + "_steps" + simulationSteps
                    + "_real" + realizations + "_N" + size + ".txt");
            FileWriter aplWriter = new FileWriter(aplFile);
            //double probability = 0.0;
            for (double probability = 0.0; probability < 1; probability += 0.05) {
                new Execution(fixedDegree, probability, simulationSteps, realizations, size, aplFile, aplWriter);
            }
        }
        koniec = currentTimeMillis();
        long delta = koniec - start;
        System.out.println("END OF WORK, time(min): " +((double)delta / ( 60 * 1000)));


    }
}
