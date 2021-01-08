// package app;

import java.util.Random;

public class Cell {
    String dna;
    double fitness;

    /**
     * Init cell with  starting dna
     * @param leftParent left parent
     * @param rightParent right parent
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
 
}