package app;

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

        Population ppl = new Population(sc.nextInt(), sc.nextInt(), generator);
        System.out.println(ppl.toString());
        sc.close();
    }

    public static void G(String[] args){
        Random generator = new Random(0);
        Scanner sc = new Scanner(System.in);

        System.out.println(Fitness.findmax(sc.next(), '1'));
        sc.close();
    }

    public static void H(String[] args){
        Random generator = new Random(0);
        Scanner sc = new Scanner(System.in);


        System.out.println(Fitness.stringToBaseSquared(sc.next(), 2));
        sc.close();
    }
    public static void I(String[] args){
        Random generator = new Random(0);
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int d = sc.nextInt();
        List<Double> fitness = new ArrayList<>();
        while(sc.hasNext())
            fitness.add(Double.parseDouble(sc.next()));

        Population ppl = new Population(n, d, generator, fitness);
        System.out.println(ppl.tournament());
        sc.close();
    }

    public static void J(String[] args){
        Random generator = new Random(0);
        Scanner sc = new Scanner(System.in);

        List<String> cells = new ArrayList<>();
        List<Double> fitness = new ArrayList<>();

        while(sc.hasNext()){
            String s = sc.next();
            cells.add(s);
            Double d = Double.parseDouble(sc.next());
            fitness.add(d);
        }

        Population ppl = new Population(cells.size(), cells.get(0).length(), generator, cells, fitness);
        ppl = ppl.spin();
        System.out.println(ppl.toString());
        sc.close();
    }

    public static void K(String[] args){
        Random generator = new Random(0);
        Scanner sc = new Scanner(System.in);

        List<String> cells = new ArrayList<>();
        List<Double> fitness = new ArrayList<>();

        while(sc.hasNext()){
            String s = sc.next();
            cells.add(s);
            Double d = Double.parseDouble(sc.next());
            fitness.add(d);
        }

        Population ppl = new Population(cells.size(), cells.get(0).length(), generator, cells, fitness);
        ppl = ppl.SUS();
        System.out.println(ppl.toString());
        sc.close();
    }

    public static void L(String[] args){
        Random generator = new Random(0);
        Scanner sc = new Scanner(System.in);

        Cell leftParent = new Cell(sc.next());
        Cell rightParent = new Cell(sc.next());

        int crossPoint = (int) (0 + Math.round(generator.nextDouble() * (leftParent.dna.length()- 0)));

        for(Cell cell : Cell.singlePointCrossover(rightParent, leftParent, crossPoint))
            System.out.println(cell);
        sc.close();
    }

    public static void M(String[] args){
        Random generator = new Random(0);
        Scanner sc = new Scanner(System.in);

        Cell leftParent = new Cell(sc.next());
        Cell rightParent = new Cell(sc.next());

        for(Cell cell : Cell.uniformCrossover(rightParent, leftParent, generator))
            System.out.println(cell);
        sc.close();
    }

    public static void N(String[] args){
        Random generator = new Random(0);
        Scanner sc = new Scanner(System.in);

        double pm = Double.parseDouble(sc.next());
        String s = sc.next();
        System.out.println( (new Cell(s)).nbitflip(pm, generator) );

        sc.close();
    }

    public static void main(String[] args) {
        N(args);
    }

}
