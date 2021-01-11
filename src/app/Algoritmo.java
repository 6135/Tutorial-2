package app;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Algoritmo {
    
    

    public static void F(int populationSize, int dnaSize){
        Random generator = new Random(0);
        
        Population ppl = new Population(populationSize, dnaSize, generator,true);
        System.out.println(ppl.toString());
        sc.close();
    }

    public static void G(){
        Random generator = new Random(0);
        Scanner sc = new Scanner(System.in);

        System.out.println(Fitness.findmax(sc.next(), '1'));
        sc.close();
    }

    public static void H(){
        Random generator = new Random(0);
        Scanner sc = new Scanner(System.in);


        System.out.println(Fitness.stringToBaseSquared(sc.next(), 2));
        sc.close();
    }
    public static void I(){
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

    public static void J(){
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

    public static void K(){
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

    public static void L(){
        Random generator = new Random(0);
        Scanner sc = new Scanner(System.in);

        Cell leftParent = new Cell(sc.next());
        Cell rightParent = new Cell(sc.next());

        for(Cell cell : Cell.singlePointCrossover(rightParent, leftParent, generator))
            System.out.println(cell);
        sc.close();
    }

    public static void uniformCrossover(String cell1, String cell2){
        Random generator = new Random(0);


        Cell leftParent = new Cell(cell1);
        Cell rightParent = new Cell(cell2);

        for(Cell cell : Cell.uniformCrossover(rightParent, leftParent, generator))
            System.out.println(cell);
    }

    public static void flipBits(double pm, String s){
        Random generator = new Random(0);

        System.out.println( (new Cell(s)).nbitflip(pm, generator) );

    }

    public static void randomPermutations(int permutationSize){
        Random generator = new Random(0);

        List<Integer> list = Population.randomPermutation(permutationSize, generator);
        for (Integer integer : list) System.out.println(integer);

    }

    public static void tournamentWithoutReplacement(int s, List<String> cells, List<Double> fitness){
        Random generator = new Random(0);

        Population ppl = new Population(cells.size(), cells.get(0).length(), generator, cells, fitness);
        ppl = ppl.tournamentWithoutReplacement(s);
        System.out.println(ppl.toString());

    }

    public static void oneGenerationOneMax(int populationSize, int dnaSize, int s, double pm, double pc) {
        Random generator = new Random(0);

        DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
        unusualSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", unusualSymbols);

        Population p = new Population(populationSize, dnaSize,generator); //onemax
        System.out.println("0: " + df.format(p.maxFitness()) + " " + df.format(p.averageFitness()) + " " + df.format(p.minFitness()));
        p = p.generationOneMax(s, pm, pc);
        System.out.println("1: " + df.format(p.maxFitness()) + " " + df.format(p.averageFitness()) + " " + df.format(p.minFitness()));

    }

    public static void nGenerations(int populationSize, int dnaSize, int s, double pm, double pc, int gens) {
        Random generator = new Random(0);

        DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
        unusualSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", unusualSymbols);

        Population p = new Population(populationSize, dnaSize,generator); //onemax

        System.out.println("0: " + df.format(p.maxFitness()) + " " + df.format(p.averageFitness()) + " " + df.format(p.minFitness()));
        for (int i = 1; i <= gens; i++) {
            p = p.generationOneMax(s, pm, pc);
            System.out.println(i+": " + df.format(p.maxFitness()) + " " + df.format(p.averageFitness()) + " " + df.format(p.minFitness()));            
        }


    }

}
