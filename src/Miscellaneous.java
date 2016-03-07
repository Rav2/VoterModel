/**
 * Created by rafalmaselek on 07.03.2016.
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Miscellaneous
{
    public static float average(int[] array)
    {
        float sum = 0;
        for(int x:array){sum += x;}
        return sum / (float)(array.length);
    }

    public static String displayMatrix(int size, int[][] matrix)
    {
        String message = new String();
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++) message += String.format(" %d", matrix[i][j]);
            message += '\n';
        }
        return message;
    }

    public static void writeToFile(String name, String content)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./output/" + name + ".txt")))) {
            bw.write(content);
            bw.close();
        }catch (java.io.IOException ex) {
            System.out.println('\n'+ex.toString());
        }

    }
}
