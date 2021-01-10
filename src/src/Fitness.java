// package app;

import java.util.Comparator;
import java.util.List;

public abstract class Fitness {

    private Fitness(){}
    public static final Comparator<Cell> cmpFitAscending = new Comparator<>() {
        public int compare(Cell o1, Cell o2) {
            return (int) Math.signum(o1.fitness - (double) o2.fitness);
        }
    }; 

    public static final Comparator<Cell> cmpFindMax = new Comparator<>() {
        public int compare(Cell o1, Cell o2) {
            return (int) Math.signum(findmax(o1.dna, '1') - (double) findmax(o2.dna, '1'));
        }
    }; 
    public static final int findmax(String s, char f){
        return (int) s.chars().filter(chr -> chr == f).count();
    }

    public static final int onemax(String s){
        return findmax(s, '1');
    }

	public static final int stringToBaseSquared(String next, int base) {
		return (int) Math.pow(Integer.parseInt(next, base), 2);
    }   
    
    public static final Comparator<Cell> cmpDnaAscedingOrder = new Comparator<>() {
        public int compare(Cell o1, Cell o2) {
            return o1.dna.compareTo(o2.dna);
        }
    }; 

    public static final <T> T getBest(List<T> list, Comparator<T> cmp) {
        T temp = list.get(0);
        for (T t : list) {
            int val = cmp.compare(temp, t);
            if(val < 0) temp=t;
        }
        return temp;

    }

}
