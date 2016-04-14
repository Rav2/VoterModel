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

    public static String makeFileName(String type, boolean averaged, int k, double p, int steps, int realizations, int size){
        if (averaged) type += "Av";
        //size = N - ilosc agentow - rozmiar sieci
        String name = "./output/" + type + "_k" + k + "_p"+ String.format("%.2f",p).substring(2,4)
                + "_steps" + steps + "_real" + realizations + "_N" + size + ".txt";
        return name;
    }



    public static void averageParametersToFile(int k, double p, int simSteps, int size, int realizations, ArrayList<ArrayList<Double>> M, ArrayList<ArrayList<Integer>> I) throws IOException {
        //zapisywanie do pliku usrednionych parametrow

        String bufM = "", bufI="";
        for(int col=0; col<simSteps; col++) {
            double sumM=0;
            double sumI=0;
            for (int row = 0; row < realizations; row++) {
                sumM += M.get(row).get(col);
                sumI += I.get(row).get(col);
            }
            bufM += (String.format("%.3f",(sumM / realizations)) + ";\n");
            bufI += (String.format("%d",(int)(sumI / realizations)) + ";\n");
        }
        writeToFile(new FileWriter(new File(Miscellaneous.makeFileName("M", true, k, p, simSteps, realizations, size))), bufM);
        writeToFile(new FileWriter(new File(Miscellaneous.makeFileName("I", true, k, p, simSteps, realizations, size))), bufI);
        // System.out.println(bufM + " \n" + bufI);
    }

    public static String handleExceptions(Exception e)
    {
        String mes = new String("\nException catched!\n");
        String localizedMessage = "Localized Message:\n" + e.getLocalizedMessage() +"\n";
        String message = "Message:\n" + e.getMessage() + "\n";
        String cause = "Cause\n" + e.getCause() + "\n";
        String stackTrace = "StackTrace:\n" + e.getStackTrace() + "\n";
        String _class = "Class:\n"+e.getClass()+"\n";
        String suppressed= "Suppressed:\n" + e.getSuppressed() + "\n";
        mes =  message + cause + stackTrace + localizedMessage + _class + suppressed;
        return mes;
   }
    /*   public static void readFile(String magnFile, String interFile,  int k, double p, int simSteps, int size, int realizations) throws IOException {
        Scanner readM;
        Scanner readI;
        ArrayList<ArrayList<Double>> M = new ArrayList<ArrayList<Double>>(size);
        ArrayList<ArrayList<Integer>> I = new ArrayList<ArrayList<Integer>>(size);
        try {
            readM = new Scanner(new File(magnFile));
            readI = new Scanner(new File(interFile));
            int row =0;
            int col=0;
            readM.useDelimiter(";");
            readI.useDelimiter(";");
            boolean condition = readM.hasNextDouble();
            while(condition){
                M.add(new ArrayList<Double>());
                I.add(new ArrayList<Integer>());
                while(condition) {
                    M.get(row).add(col, readM.nextDouble());
                    I.get(row).add(col, readI.nextInt());
                    col++;
                    if (col == (simSteps)) {
                        col = 0;
                        row++;
                        condition = false;
                    }
                }
                readM.nextLine();
                readI.nextLine();
                condition = readM.hasNextDouble();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String bufM = "", bufI="";
        for(int col=0; col<simSteps; col++) {
            double sumM=0;
            double sumI=0;
            for (int row = 0; row < realizations; row++) {
                sumM += M.get(row).get(col);
                sumI += I.get(row).get(col);
            }
            bufM += (String.format("%.3f",(sumM / realizations)) + ";\n");
            bufI += (String.format("%d",(int)(sumI / realizations)) + ";\n");
        }
        writeToFile(new FileWriter(new File(Miscellaneous.makeFileName("MM", true, k, p, simSteps, size))), bufM);
        writeToFile(new FileWriter(new File(Miscellaneous.makeFileName("II", true, k, p, simSteps, size))), bufI);
    }*/
}

