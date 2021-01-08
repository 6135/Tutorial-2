// package app;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Population{
    int populationSize;
    int dnaSize;
    Random random;
    List<Cell> population;
    /**
     * 
     * @param populationSize Size of the disired population
     * @param dnaSize Size of the chromosomes 
     * @param random The random generator
     */
    public Population(int populationSize, int dnaSize, Random random){
        this.populationSize = populationSize;
        this.dnaSize = dnaSize;
        this.random = random;
        this.population = new ArrayList<>();
        initializePopulation();
    }

    /**
     * 
     * @param populationSize Size of the disired population
     * @param dnaSize Size of the chromosomes 
     * @param random The random generator
     */
    public Population(int populationSize, int dnaSize, Random random, List<Double> startingFitness){
        this.populationSize = populationSize;
        this.dnaSize = dnaSize;
        this.random = random;
        this.population = new ArrayList<>();
        initializePopulation(startingFitness);
    }    

    /**
     * 
     * @param populationSize Size of the disired population
     * @param dnaSize Size of the chromosomes 
     * @param random The random generator
     */
    public Population(int populationSize, int dnaSize, Random random, List<String> cells,  List<Double> startingFitness){
        this.populationSize = populationSize;
        this.dnaSize = dnaSize;
        this.random = random;
        this.population = new ArrayList<>();
        initializePopulation(cells,startingFitness);
    }    

    public Population(int size, int dnaSize){
        this.populationSize = size;
        this.dnaSize = dnaSize;
        this.population = new ArrayList<>();        
    }

    private void initializePopulation(){
        if( population.isEmpty())
            for (int i = 0; i < populationSize; i++)
                population.add(Cell.newCell(dnaSize, random));
    }

    private void initializePopulation(List<Double> startingFitness){
        if( population.isEmpty())
            for (int i = 0; i < populationSize; i++)
                population.add(Cell.newCell(dnaSize, random,startingFitness.get(i)));
    }

    private void initializePopulation(List<String> cells, List<Double> startingFitness){
        if( population.isEmpty())
            for (int i = 0; i < populationSize; i++)
                population.add( new Cell(cells.get(i), startingFitness.get(i)));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < populationSize; i++) {
            sb.append(population.get(i));
            if(i<populationSize-1)
                sb.append("\n");
        }
        return sb.toString();
    }

    public Population tournament(){
        Population p = new Population(populationSize,dnaSize);
        List<Cell> winners = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            p.add(tourny());
        }
        return p;
    }

    public Cell tourny(){
        int a = 0; int b = populationSize-1;
        int index1 = (int) (a + Math.round(random.nextDouble() * (b - a)));
        int index2 = (int) (a + Math.round(random.nextDouble() * (b - a)));
        Cell one = population.get(index1);
        Cell two = population.get(index2);
        int value = Fitness.cmpFitAscending.compare(one, two);
        if(value >= 0 )
            return one;
        else return two;
    }
    private double fitnessSum(){
        double f = 0;
        for (Cell cell : population) {
            f+=cell.fitness;
        }
        return f;
    }

    private double fitnessSumTo(int start, int end){
        double f = 0;
        for (int i = start; i < end+1; i++)
            f+=population.get(i).fitness;
        return f;
    }

    public Population spin(){
        sort(Fitness.cmpFitAscending.reversed());
        Population p = new Population(populationSize,dnaSize);
        double sum = fitnessSum();
        if(sum == 0)
            sum=1;

        List<Double> probs = new ArrayList<>(); //probability list
        double last = 0;

        for(Cell cell : population){
            last+=(cell.fitness/sum);
            probs.add(last);
        }
        
        for(int i = 0; i<populationSize;i++){
            double rand = random.nextDouble();
            for(int j = 0; j<probs.size();j++){
                double value = probs.get(j);
                if(value>=rand){
                    p.add(population.get(j));
                    break;
                }
            }
        }

        p.sort(Fitness.cmpDnaAscedingOrder);
        return p;
    }

    public Population SUS(){
        // sort(Fitness.cmpFitAscending);
        // System.out.println(this);
        Population p = new Population(populationSize,dnaSize);
        double sum = fitnessSum();
        double space = sum/populationSize;
        double start = (Math.round(random.nextDouble() * (space)));
        // System.out.println("Start: " + start);
        List<Double> pointers = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) pointers.add( (start + i * space));
        return rws(p,pointers);
    }

    private Population rws(Population p,List<Double> pointers){
        // System.out.println(pointers);
        for (Double d : pointers) {
            int i;
            // System.out.println("P: " + d + " with starting sum: " +  fitnessSumTo(0,0));
            for(i = 0; fitnessSumTo(0,i) < d; i++) {/* just increment i */}
                
            p.add(population.get(i));
        }
        return p;
    }

    private void add(Cell cell){
        population.add(cell);
    }
    
    public void sort(Comparator<Cell> cmp){
        population.sort(cmp);
    }

}
