package app2;

import java.util.*;

public class AG {
    private Population p;
    protected static Random generator = new Random(0);

    public AG(int n, int l){
        p=new Population(n,l,generator);
    }

    public AG(List<Individual> p){
        this.p=new Population(p);
    }

    public AG(){
    }

    public Population getPopulation(){
        return p;
    }


}

