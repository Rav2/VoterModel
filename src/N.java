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
            int simulationSteps = 1000;
            int realizations = 100;
            int size = 1000;
            File aplFile = new File("./output/" + "apl" + "_k" + fixedDegree + "_steps" + simulationSteps + "_N" + size + ".txt");
            FileWriter aplWriter = new FileWriter(aplFile);
            for (double probabilty = 0.0; probabilty < 1; probabilty += 0.05) {
                Execution e = new Execution(fixedDegree, probabilty, simulationSteps, realizations, size, aplFile, aplWriter);
            }
        }
        koniec = currentTimeMillis();
        long delta = koniec - start;
        System.out.println("END OF WORK, time(min): " +((double)delta / ( 60 * 1000)));


    }
}
