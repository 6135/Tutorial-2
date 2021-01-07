

import java.util.Random;

public class Cell {
    Cell leftParent;
    Cell rightParent;
    String dna;
    /**
     * Init cell with  starting dna
     * @param leftParent left parent
     * @param rightParent right parent
     * @param dna starting dna
     */
    public Cell(Cell leftParent, Cell rightParent, String dna){
        this.leftParent = leftParent;
        this.rightParent = rightParent;
        this.dna = dna;
    }
    /**
     * Init cell without starting 
     * @param leftParent left parent
     * @param rightParent right parent
     */
    public Cell(Cell leftParent, Cell rightParent){
        this.leftParent = leftParent;
        this.rightParent = rightParent;
    }

    public static final Cell newCell(int chromosomeSize, Random random){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chromosomeSize; i++){
            sb.append(newBit(random.nextDouble()));
        }
        return new Cell( null,null,sb.toString() );
    }
    private static int newBit(double randomBit){
        return randomBit < 0.5 ? 0 : 1;
    }

    @Override
    public String toString() {
        return dna;
    }
}
