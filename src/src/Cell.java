// package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cell implements Cloneable {
    String dna;
    double fitness;

    /**
     * Init cell with starting dna
     * 
     * @param dna starting dna
     */
    public Cell(String dna) {
        this.dna = dna;
        fitness = 0.0;
    }

    /**
     * Init cell with dna and fitness specific
     */
    public Cell(String dna, double fitness) {
        this.dna = dna;
        this.fitness = fitness;
    }

    public Cell(Cell source){
        this.dna = source.dna;
        this.fitness = source.fitness;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Cell(this);
    }

    public static final Cell newCell(int chromosomeSize, Random random, double fitness){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chromosomeSize; i++){
            sb.append(newBit(random.nextDouble()));
        }
        return new Cell(sb.toString(),fitness);
    }

    public static final Cell newCellOneMax(int chromosomeSize, Random random){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chromosomeSize; i++){
            sb.append(newBit(random.nextDouble()));
        }
        Cell cell = new Cell(sb.toString());
        cell.fitness = Fitness.onemax(cell.dna);
        return cell;
    }  

    public static final Cell newCell(int chromosomeSize, Random random){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chromosomeSize; i++){
            sb.append(newBit(random.nextDouble()));
        }
        return new Cell(sb.toString());
    }

    private static int newBit(double randomBit){
        return randomBit < 0.5 ? 0 : 1;
    }

    @Override
    public String toString() {
        return dna;
    }

    @Override
    public boolean equals(Object c){
        if(c instanceof Cell){
            Cell cell = (Cell) c;
            return cell.dna.equals(this.dna);
        }   
        return false;
    }

    public static List<Cell> singlePointCrossover(Cell leftParent, Cell rightParent, Random random){
        List<Cell> result=new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        int p = (int) (1 + Math.round(random.nextDouble() * ((leftParent.dna.length()) - 2))); //we exclude 0 and lenght() itself because we cant crossover at the ends.
        // copy the first part until p
        for (int i = 0; i < p; i++) {
            sb.append(leftParent.dna.charAt(i));
            sb2.append(rightParent.dna.charAt(i));
        }
        // and switch the second part after p
        for (int i = p; i < leftParent.dna.length();i++) {
            sb.append(rightParent.dna.charAt(i));
            sb2.append(leftParent.dna.charAt(i));
        }
        result.add(new Cell(sb.toString()));
        result.add(new Cell(sb2.toString()));
        return result;
    }

    public static List<Cell> uniformCrossover(Cell leftParent, Cell rightParent, Random random){
        List<Cell> children = new ArrayList<>();
        StringBuilder child1 = new StringBuilder();
        StringBuilder child2 = new StringBuilder();
        for (int i = 0; i < leftParent.dna.length(); i++) {
            double u = random.nextDouble();
            if(u < 0.5) {
                child2.append(rightParent.dna.charAt(i));
                child1.append(leftParent.dna.charAt(i));
            } else {
                child2.append(leftParent.dna.charAt(i));
                child1.append(rightParent.dna.charAt(i));                
            }
        }
        children.add(new Cell(child1.toString()));
        children.add(new Cell(child2.toString()));
        return children;
    }

    public Cell nbitflip(double pm, Random random){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dna.length(); i++) {
            if(random.nextDouble() < pm){
                if(dna.charAt(i) == '1')
                    sb.append('0');
                else if(dna.charAt(i) == '0')
                    sb.append('1');
            } else sb.append(dna.charAt(i));
        }
        return new Cell(sb.toString());
    }
    
}
