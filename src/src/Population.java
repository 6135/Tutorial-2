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
    Comparator<Cell> fitnessCmp;
    List<Cell> population;
    /**
     * 
     * @param populationSize Size of the disired population
     * @param dnaSize Size of the chromosomes 
     * @param random The random generator
     */
    public Population(int populationSize, int dnaSize, Random random, Comparator<Cell> cmp){
        this.populationSize = populationSize;
        this.dnaSize = dnaSize;
        this.random = random;
        this.fitnessCmp = cmp;
        this.population = new ArrayList<>();
        initializePopulation();
    }

    /**
     * 
     * @param populationSize Size of the disired population
     * @param dnaSize Size of the chromosomes 
     * @param random The random generator
     */
    public Population(int populationSize, int dnaSize, Random random, Comparator<Cell> cmp, List<Double> startingFitness){
        this.populationSize = populationSize;
        this.dnaSize = dnaSize;
        this.random = random;
        this.fitnessCmp = cmp;
        this.population = new ArrayList<>();
        initializePopulation(startingFitness);
    }    

    /**
     * 
     * @param populationSize Size of the disired population
     * @param dnaSize Size of the chromosomes 
     * @param random The random generator
     */
    public Population(int populationSize, int dnaSize, Random random, Comparator<Cell> cmp, List<String> cells,  List<Double> startingFitness){
        this.populationSize = populationSize;
        this.dnaSize = dnaSize;
        this.random = random;
        this.fitnessCmp = cmp;
        this.population = new ArrayList<>();
        initializePopulation(cells,startingFitness);
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

    public List<Cell> tournament(){

        List<Cell> winners = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            int a = 0; int b = populationSize-1;
            double u = random.nextDouble();
            //System.out.println(u);
            int index1 = (int) (a + Math.round(u * (b - a)));
            u = random.nextDouble();
            //System.out.println(u);
            int index2 = (int) (a + Math.round(u * (b - a)));
            Cell one = population.get(index1);
            Cell two = population.get(index2);
            int value = fitnessCmp.compare(one, two);
            if(value >= 0 )
                winners.add(one);
            else winners.add(two);
        }
        return winners;
    }
    public List<Cell> spin(){

        List<Cell> winners = new ArrayList<>();
        int totalSum,partial;
        population.sort(Fitness.cmpBasicFit.reversed());
        for (int i = 0; i < populationSize; i++) {
            totalSum=0;
            partial = 0;
            
            for (Cell cell : population) 
                totalSum+=cell.fitness;
            //double u = random.nextDouble();
            int rand = random.nextInt(totalSum+1);//(int) (0 + Math.round(u * (totalSum - 0)));
            //System.out.println(rand);
            for (Cell cell : population) {
                partial+=cell.fitness;
                if(partial >= rand){
                    //System.out.println("adding + " +  cell.toString());
                    winners.add(cell);
                    break;
                }
            }
            
        }
        winners.sort(Fitness.cmpDnaOrder);
        return winners;
    }

    
    


}
