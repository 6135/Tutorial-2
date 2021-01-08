// package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cell {
    String dna;
    double fitness;

    /**
     * Init cell with  starting dna
     * @param dna starting dna
     */
    public Cell(String dna){
        this.dna = dna;
    }

    /**
     * Init cell with dna and fitness specific
     */
    public Cell(String dna, double fitness){
        this.dna = dna;
        this.fitness = fitness;
    }

    public static final Cell newCell(int chromosomeSize, Random random, double fitness){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chromosomeSize; i++){
            sb.append(newBit(random.nextDouble()));
        }
        return new Cell(sb.toString(),fitness);
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

    public static List<Cell> singlePointCrossover(Cell rightParent, Cell leftParent, int crossPoint){
        List<String> subChromossomes = new ArrayList<>(); //This list will be half for rP Chromosomes splits and half for lP chromosomes splits
        
        subChromossomes.add(leftParent.dna.substring(0, crossPoint));
        subChromossomes.add(rightParent.dna.substring(0, crossPoint));

        subChromossomes.add(rightParent.dna.substring(crossPoint));
        subChromossomes.add(leftParent.dna.substring(crossPoint));
        
        //System.out.println(subChromossomes);
        List<Cell> children = new ArrayList<>();
        StringBuilder sb;
        for(int i = 0; i < subChromossomes.size()/2; i++){
            sb = new StringBuilder();
            sb.append(subChromossomes.get(i));
            sb.append(subChromossomes.get(i+subChromossomes.size()/2));
            children.add(new Cell(sb.toString()));
        }
        return children;
    }
 
}
