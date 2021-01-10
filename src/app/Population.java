package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Population{
    int populationSize;
    int dnaSize;
    Random random;
    List<Cell> population;

    /**
     * Empty Population without cell initialization and no population limit
     * @param dnaSize Size of the chromosomes 
     * @param random The random generator
     */
    public Population(int dnaSize, Random random){
        this.populationSize = 0;
        this.dnaSize = dnaSize;
        this.random = random;
        this.population = new ArrayList<>();
    }

   /**
     * New Population with cell initialization, dynamic fitness based on onemax.
     * @param populationSize Size of the disired population
     * @param dnaSize Size of the chromosomes 
     * @param random The random generator
     */
    public Population(int populationSize, int dnaSize, Random random){
        this.populationSize = populationSize;
        this.dnaSize = dnaSize;
        this.random = random;
        this.population = new ArrayList<>();
        initializePopulationOneMax();
    }
    
    /**
     * New Population with basic toggleable cell initialization, no fitness.
     * 
     * @param populationSize Size of the disired population
     * @param dnaSize        Size of the chromosomes
     * @param random         The random generator
     * @param initPopulation If the population is to be initialized with random
     *                       cells
     */
    public Population(int populationSize, int dnaSize, Random random, boolean initPopulation){
        this.populationSize = populationSize;
        this.dnaSize = dnaSize;
        this.random = random;
        this.population = new ArrayList<>();
        if(initPopulation) initializePopulation();
    }

    /**
     * New Population with basic toggleable cell initialization, static fitness.
     * @param populationSize Size of the desired population
     * @param dnaSize Size of the chromosomes
     * @param random The random generator
     * @param startingFitness List of fixed fitnesses to be given to the cells that are randomly generated
     */
    public Population(int populationSize, int dnaSize, Random random, List<Double> startingFitness){
        this.populationSize = populationSize;
        this.dnaSize = dnaSize;
        this.random = random;
        this.population = new ArrayList<>();
        initializePopulation(startingFitness);
    }    

    /**
     * New Population with no cell initialization, staatic fitness, static cells.
     * @param populationSize Size of the desired population
     * @param dnaSize Size of the chromosomes
     * @param random The random generator
     * @param cells List of fixed cells
     * @param startingFitness List of fixed fitnesses to be given to the cells that are randomly generated
     */
    public Population(int populationSize, int dnaSize, Random random, List<String> cells,  List<Double> startingFitness){
        this.populationSize = populationSize;
        this.dnaSize = dnaSize;
        this.random = random;
        this.population = new ArrayList<>();
        initializePopulation(cells,startingFitness);
    }     



    private void initializePopulation(){
        if( population.isEmpty())
            for (int i = 0; i < populationSize; i++)
                population.add(Cell.newCell(dnaSize, random));
    }

    private void initializePopulationOneMax() {
        if( population.isEmpty())
            for (int i = 0; i < populationSize; i++){
                population.add(Cell.newCellOneMax(dnaSize, random));    
            }    
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
        Population p = new Population(populationSize,dnaSize, random, false);
        for (int i = 0; i < populationSize; i++) p.add(tourny());
        return p;
    }

    public Cell tourny(){
        int a = 0; 
        int b = populationSize-1;
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
        Population p = new Population(populationSize,dnaSize,random,false);
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
        Population p = new Population(populationSize,dnaSize,random,false);
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
        if(populationSize < population.size()) populationSize++;
    }
    
    public void sort(Comparator<Cell> cmp){
        population.sort(cmp);
    }

    public static List<Integer> randomPermutation(int size, Random random){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) list.add(i);
        
        for (int i = 0; i < size-1; i++) {
            int r = (int) (i + Math.round(random.nextDouble() * ((size-1) - i)));
            Collections.swap(list,r,i);
        }
        return list;
    }
    private Population randomPermutatedPopulation(){
        List<Integer> positions = Population.randomPermutation(populationSize, random);
        Population p = new Population(populationSize, dnaSize, random, false);
        // System.out.println("Old pop");
        // System.out.println(this);
        for (Integer integer : positions)
            p.add(population.get(integer));
        // System.out.println("random");
        // System.out.println(p.population);
        // System.out.println("------");
        return p;
    }
    public Population tournamentWithoutReplacement(int iter){
        Population p = new Population(populationSize,dnaSize,random,false);
        Population result = p;
        for (int i = 0; i < iter; i++) {
            p = this.randomPermutatedPopulation();
            for (int j = 0; j < populationSize/iter; j++) {
                List<Cell> sublist = p.population.subList(j*iter, (j*iter) + iter); // 0x2 == 0 -> 0 + 2,  1x2 = 2 -> 2+2  etc
                result.add(Fitness.getBest(sublist, Fitness.cmpFitAscending));
            }
        }
        return result;
        
    }


    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Population){
            Population p = (Population) obj;
            return population.equals(p.population);
        }
        return false;
    }

    public void addAll(List<Cell> list){
        for (Cell cell : list) {
            this.add(cell);
        }
    }

    public void merge(Population p){
        for (Cell cell : p.population) {
            this.add(cell);
        }
    }
    public Cell get(int i){
        if(i<populationSize)
            return population.get(i);
        else return null;
    }
    private void singlePointCrossoverProbPopulation(double pc){
        for(int i=0;i<population.size();i+=2){
            double r=random.nextDouble();
            if(r<pc){
                List<Cell> l=Cell.singlePointCrossover(population.get(i),population.get(i+1),random);
                population.set(i,l.get(0));
                population.set(i+1,l.get(1));
            }
        }
    }

    private void nbitFlipPop(double pm){
        for(int i=0;i<population.size();i++) population.set(i,population.get(i).nbitflip(pm,random));
    }

    public double maxFitness(){
        return Collections.max(population,Fitness.cmpFitAscending).fitness;
    }
    public double averageFitness(){
        return fitnessSum()/populationSize;
    }
    public double minFitness(){
        return Collections.min(population,Fitness.cmpFitAscending).fitness;
    }

    private void recalcFitnessOneMax(){
        for (int i = 0; i < population.size(); i++) {
            Cell c = population.get(i);
            c.fitness = Fitness.onemax(c.dna);
            population.set(i,c);
        }
    }

    public Population generationOneMax(int s, double pm, double pc){
        System.out.println("Original " + population.toString());
        Population p = tournamentWithoutReplacement(s);
        System.out.println("Torneio " + population.toString());
        p.singlePointCrossoverProbPopulation(pc);
        System.out.println("Crossover " + population.toString());
        p.nbitFlipPop(pm);
        System.out.println("Mutation " + population.toString());
        p.recalcFitnessOneMax();
        System.out.println("Recalculated fitness " + population.toString());
        return p;
    }
}
