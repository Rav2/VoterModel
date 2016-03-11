/**
 * Created by rafalmaselek on 07.03.2016.
 */
import com.sun.javafx.binding.StringFormatter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Miscellaneous
{
    public static float average(int[] array)
    {
        float sum = 0;
        for(int x:array){sum += x;}
        return sum / (float)(array.length);
    }

    public static String displayList(int size, ArrayList<ArrayList<Integer>> list, boolean showHeaders)
    {
        String message = new String();
        if(showHeaders) {message+="No\tNeighbours\n";}
        for (int i = 0; i < size; i++)
        {
            message += String.format("%d\t",i);
            for (int j = 0; j < list.get(i).size(); j++) message += String.format(" %d", list.get(i).get(j));
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

//    public static String handleExceptions(Exception e)
//    {
//        String mes = new String("\nException catched!\n");
//        String localizedMessage = "Localized Message:\n" + e.getLocalizedMessage() +"\n";
//        String message = "Message:\n" + e.getMessage() + "\n";
//        String cause = "Cause\n" + e.getCause() + "\n";
//        String stackTrace = "StackTrace:\n" + e.getStackTrace() + "\n";
//        String _class = "Class:\n"+e.getClass()+"\n";
//        String suppressed= "Suppressed:\n" + e.getSuppressed() + "\n";
//        mes =  message + cause + stackTrace + localizedMessage + _class + suppressed;
//        return mes;
//    }
}
