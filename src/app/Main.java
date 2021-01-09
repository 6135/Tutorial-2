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

        Population ppl = new Population(sc.nextInt(), sc.nextInt(), generator,true);
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

    public static void O(String[] args){
        Random generator = new Random(0);
        Scanner sc = new Scanner(System.in);

        List<Integer> list = Population.randomPermutation(sc.nextInt(), generator);
        for (Integer integer : list) System.out.println(integer);

        sc.close();
    }

    public static void P(String[] args){
        Random generator = new Random(0);
        Scanner sc = new Scanner(System.in);

        List<String> cells = new ArrayList<>();
        List<Double> fitness = new ArrayList<>();
        int s = sc.nextInt();
        while(sc.hasNext()){
            String str = sc.next();
            cells.add(str);
            Double d = Double.parseDouble(sc.next());
            fitness.add(d);
        }

        Population ppl = new Population(cells.size(), cells.get(0).length(), generator, cells, fitness);
        ppl = ppl.tournamentWithoutReplacement(s);
        System.out.println(ppl.toString());
        sc.close();
    }
    public static void Q(String[] args) {
        Random generator = new Random(0);
        Scanner sc = new Scanner(System.in);

        DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
        unusualSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", unusualSymbols);

        Population p = new Population(sc.nextInt(), sc.nextInt(),generator); //onemax
        int s = sc.nextInt();
        double pm = Double.parseDouble(sc.next());
        double pc = Double.parseDouble(sc.next());
        System.out.println("0: " + df.format(p.maxFitness()) + " " + df.format(p.averageFitness()) + " " + df.format(p.minFitness()));
        p.generationOneMax(s, pm, pc);
        System.out.println("1: " + df.format(p.maxFitness()) + " " + df.format(p.averageFitness()) + " " + df.format(p.minFitness()));

    }
    public static void main(String[] args) {
        Q(args);
    }

}
