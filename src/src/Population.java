
import java.io.Serializable;
import java.util.ArrayList;
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
     * @param initPopulation The initial population
     */
    public Population(int populationSize, int dnaSize, Random random, List<Cell> initPopulation){
        this.populationSize = populationSize;
        this.dnaSize = dnaSize;
        this.random = random;
        this.population = new ArrayList<>(initPopulation);

    }

    private void initializePopulation(){
        if( population.isEmpty())
            for (int i = 0; i < populationSize; i++)
                population.add(Cell.newCell(dnaSize, random));
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

    public static int findmax(String s, char f){
        return (int) s.chars().filter(chr -> chr == f).count();
    }

	public static int stringToBase(String next, int base) {
		return (int) Math.pow(Integer.parseInt(next, base), 2);
	}
}
