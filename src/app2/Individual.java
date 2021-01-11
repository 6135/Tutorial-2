package app2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Individual implements Comparator<Individual>, Comparable<Individual> {
    private String cromossoma;
    private static int tamanho;
    private Double fitness;
    public Individual (int l, Random r)
    {
        String x="";
        for (int j=0; j<l; j++)
        {
            if (r.nextDouble()<0.5)
                x += "0";
            else
                x += "1";
        }
        cromossoma = x;
        tamanho = x.length();
        fitness = null;
    }

    public Individual (String x)
    {
        cromossoma = x;
        tamanho = x.length();
        fitness = null;
    }

    public Individual (String x,double fitness)
    {
        cromossoma = x;
        tamanho = x.length();
        this.fitness = fitness;
    }

    public void setFitness(double f){
        fitness=f;
    }

    public double getFitness(){
        return fitness;
    }

    public String toString()
    {
        return cromossoma;
    }

    public int oneMax()
    {
        int result=0;
        for (int i=0; i<tamanho; i++)
        {
            if(cromossoma.charAt(i)=='1')
                result++;
        }
        return result;
    }

    public int converterDecimal()
    {
        int result = 0;
        for(int i=tamanho-1; i>=0; i--)
        {
            if(cromossoma.charAt(i)=='1')
                result+=Math.pow(2,tamanho-1-i);
        }
        return result;
    }

    public int compareTo(Individual i) {
        return fitness.compareTo(i.fitness);
    }

    public int compare(Individual i1, Individual i2) {
        return (int)(i1.fitness-i2.fitness);
    }

    public static List<Individual> onePointCrossover(Individual i1, Individual i2){
        List<Individual> result=new ArrayList<>();
        String f1="",f2="";
        int n=i1.toString().length()-2;
        int p =(int)Math.round(AG.generator.nextDouble()*n);
        //System.out.println("point: "+p);
        for(int i=0;i<n+2;i++)
            if(i<=p) {
                f1 += i1.toString().charAt(i);
                f2 += i2.toString().charAt(i);
            }
            else {
                f2 += i1.toString().charAt(i);
                f1 += i2.toString().charAt(i);
            }
        result.add(new Individual(f1));
        result.add(new Individual(f2));
        return result;
    }

    public  static List<Individual> uniformCrossover(Individual i1,Individual i2){
        List<Individual> result=new ArrayList<>();
        String f1="",f2="";
        int n=i1.toString().length();
        for(int i=0;i<n;i++)
            if(AG.generator.nextDouble()<0.5) {
                f1 += i1.toString().charAt(i);
                f2 += i2.toString().charAt(i);
            }
            else {
                f2 += i1.toString().charAt(i);
                f1 += i2.toString().charAt(i);
            }
        result.add(new Individual(f2));
        result.add(new Individual(f1));
        return result;
    }

    public Individual mutation(double pm){
        String s1="";
        String s2=toString();
        int n=s2.length();
        for(int i=0;i<n;i++){
            if(AG.generator.nextDouble()<pm)
                s1+=s2.charAt(i)=='1'?0:1;
            else
                s1+=s2.charAt(i);
        }
        return new Individual(s1);
    }
}
