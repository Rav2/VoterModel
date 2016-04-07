/**
 * Created by Kasia on 07.04.16.
 */
public class N {
    public static void main(String[] args)
    {

        for (double probabilty = 0.0; probabilty<1.0; probabilty+= 0.02){
           // System.out.println(String.format("%.2f",i));
            Execution e = new Execution(probabilty);
        }
        System.out.println("END OF WORK");

    }
}
