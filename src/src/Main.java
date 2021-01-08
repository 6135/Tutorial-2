
import java.util.*;
import java.text.*;

public class Main {
    // public static void base(String[] args){
    //     Random generator = new Random(0);
    //     Scanner sc = new Scanner(System.in);
    //     int n = sc.nextInt();
    //     double d;

    //     DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
    //     unusualSymbols.setDecimalSeparator('.');
    //     DecimalFormat df = new DecimalFormat("0.00", unusualSymbols);

    //     for(int i=0; i < n; i++){
    //         d = generator.nextDouble();
    //         System.out.println(df.format(d));
    //     }
    //     sc.close();
    // }

    public static void F(String[] args){
        Random generator = new Random(0);
        Scanner sc = new Scanner(System.in);

        // DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
        // unusualSymbols.setDecimalSeparator('.');
        // DecimalFormat df = new DecimalFormat("0.00", unusualSymbols);

        Population ppl = new Population(sc.nextInt(), sc.nextInt(), generator, Fitness.cmpFindMax);
        System.out.println(ppl.toString());
        sc.close();
    }

    public static void G(String[] args){
        Random generator = new Random(0);
        Scanner sc = new Scanner(System.in);

        // DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
        // unusualSymbols.setDecimalSeparator('.');
        // DecimalFormat df = new DecimalFormat("0.00", unusualSymbols);

        System.out.println(Fitness.findmax(sc.next(), '1'));
        sc.close();
    }

    public static void H(String[] args){
        Random generator = new Random(0);
        Scanner sc = new Scanner(System.in);

        // DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
        // unusualSymbols.setDecimalSeparator('.');
        // DecimalFormat df = new DecimalFormat("0.00", unusualSymbols);

        System.out.println(Fitness.stringToBaseSquared(sc.next(), 2));
        sc.close();
    }
    public static void I(String[] args){
        Random generator = new Random(0);
        Scanner sc = new Scanner(System.in);

        // DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
        // unusualSymbols.setDecimalSeparator('.');
        // DecimalFormat df = new DecimalFormat("0.00", unusualSymbols);
        int n = sc.nextInt();
        int d = sc.nextInt();
        List<Integer> fitness = new ArrayList<>();
        for (int i = 0; i < n; i++)
            fitness.add(sc.nextInt());

        Population ppl = new Population(n, d, generator, Fitness.cmpBasicFit, fitness);
        List<Cell> winners = ppl.tournament();
        for (Cell cell : winners) {
            System.out.println(cell.toString());
        }
        sc.close();
    }

    public static void main(String[] args) {
        I(args);
    }

}
