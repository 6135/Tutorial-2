package app;

import java.util.Comparator;

public class Fitness {

    public static Comparator<Cell> cmpFitAscending = new Comparator<>() {
        public int compare(Cell o1, Cell o2) {
            return (int) Math.signum(o1.fitness - (double) o2.fitness);
        }
    }; 

    public static Comparator<Cell> cmpFindMax = new Comparator<>() {
        public int compare(Cell o1, Cell o2) {
            return (int) Math.signum(findmax(o1.dna, '1') - (double) findmax(o2.dna, '1'));
        }
    }; 
    public static int findmax(String s, char f){
        return (int) s.chars().filter(chr -> chr == f).count();
    }

	public static int stringToBaseSquared(String next, int base) {
		return (int) Math.pow(Integer.parseInt(next, base), 2);
    }   
    
    public static Comparator<Cell> cmpDnaAscedingOrder = new Comparator<>() {
        public int compare(Cell o1, Cell o2) {
            return o1.dna.compareTo(o2.dna);
        }
    }; 



}
