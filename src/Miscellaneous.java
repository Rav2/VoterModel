/**
 * Created by rafalmaselek on 07.03.2016.
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Miscellaneous
{
    public static float average(int[] array)
    {
        float sum = 0;
        for(int x:array){sum += x;}
        return sum / (float)(array.length);
    }

    public static String displayList(int size, ArrayList<ArrayList<Integer>> list, boolean showHeaders, int[] state)
    {
        String message = new String();
        if(showHeaders) {message+="No\tNeighbours\n Opinion\n";}
        for (int i = 0; i < size; i++)
        {
            message += String.format("%d\t",i);
            for (int j = 0; j < list.get(i).size(); j++) message += String.format(" %d", (list.get(i).get(j)));
            message += "\t\t\t" + state[i];
            message += '\n';
        }
        return message;
    }

    public static void writeToFile(FileWriter fileWriter, String content)
    {
        try (BufferedWriter bw = new BufferedWriter(fileWriter)) {
            bw.write(content);
            bw.close();
        }catch (java.io.IOException ex) {
            System.out.println('\n'+ex.toString());
        }

    }

    public static void readFile(String file){
        Scanner read;
        String buf;
        ArrayList M = new ArrayList();
        ArrayList I = new ArrayList();
        try {
            read = new Scanner(new File(file));

            buf = read.nextLine();
            while(read.hasNextLine()){

                M.add(read.nextLine());
                I.add(read.nextLine());
                /*//System.out.println(buf);
                //System.out.println(buf.substring(0,5));
                //System.out.println(buf.substring(6));
                M.add(String.valueOf(buf.substring(0,5)));
                I.add(String.valueOf(buf.substring(6)));*/


            }
            for (int i=0; i<M.size(); i++){
                System.out.println(M.get(i+1) + " "+ I.get(i+1)  + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
