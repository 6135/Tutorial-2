package app2;

import java.util.*;

public class Population {

    private static Random r;
    private static List <Individual> membros;

    /**
     * @param n numero de individuos
     * @param l comprimento do cromossoma
     * @param r Random
     */
    public Population(int n, int l, Random r){
        membros=new ArrayList<>();
        for (int i=0; i<n; i++)
        {
            membros.add(new Individual(l,r));
        }
    }

    public Population(List<Individual> p){
        membros=p;
    }

    public List <Individual> getPopulation()
    {
        return membros;
    }

    public String toString()
    {
        String result="";
        for (Individual i:membros)
        {
            result+= i + "\n";
        }
        return result;
    }

    public double getSumFitness(){
        double result=0;
        for(Individual i: membros){
            result+=i.getFitness();
        }
        return result;
    }

    public List<Individual> torneio(){
        double u;
        int a=0;
        int b=getPopulation().size()-1;
        List<Individual> result = new ArrayList<>();
        for(int i=0;i<getPopulation().size();i++){
            u = AG.generator.nextDouble();
            int n1=a+(int)Math.round(u*(b-a));
            u = AG.generator.nextDouble();
            int n2=a+(int)Math.round(u*(b-a));
            Individual i1= getPopulation().get(n1);
            Individual i2= getPopulation().get(n2);
            if(i1.getFitness()>=i2.getFitness())
                result.add(i1);
            else
                result.add(i2);
        }
        return result;
    }

    public List<Individual> selection(){
        List<Individual> result=new ArrayList<>();
        int n=getPopulation().size();
        List<Double> probabilidades=new ArrayList<>();
        double sum=getSumFitness();
        double x=0;
        for(Individual i:getPopulation()) {
            x += i.getFitness() / sum;
            probabilidades.add(x);
        }
        for(int i=0;i<n;i++){
            double r=AG.generator.nextDouble();
            if (r<probabilidades.get(0))
                result.add(getPopulation().get(0));
            else
                for(int j=0;j<probabilidades.size()-1;j++){
                    if(r>probabilidades.get(j)&&r<probabilidades.get(j+1)){
                        result.add(getPopulation().get(j+1));
                        break;
                    }
                }
        }
        return result;
    }


    public List<Individual> selectionSUS(){
        List<Individual> result=new ArrayList<>();
        double sumFitness=getSumFitness();
        int n=getPopulation().size();
        double r = AG.generator.nextDouble()*(sumFitness/n);
        double d=sumFitness/n;
        List<Double> ponteiros =new ArrayList<>();
        ponteiros.add(r);
        for(int i=1;i<n;i++)
            ponteiros.add(ponteiros.get(i-1)+d);
        double x;
        for(int i=0;i<ponteiros.size();i++){
            x=0;
            for(int j=0;j<n;j++)
                if(x+getPopulation().get(j).getFitness()>ponteiros.get(i)){
                    result.add(getPopulation().get(j));
                    break;
                } else x+=getPopulation().get(j).getFitness();
        }
        return result;
    }

    private List<Individual> permutation(List<Individual> original){
       ArrayList<Individual> result = new ArrayList<>(original);
        int r;
        int n=original.size();
        for (int i=0;i<n-1; i++){
            r=((int)(Math.round(AG.generator.nextDouble()*(n-1-i))+i));
            Individual t = result.get(i);
            result.set(i, result.get(r));
            result.set(r, t);
        }
        return result;
    }


    public ArrayList<Individual> tournamentSelectionWithoutReplacement(int s){
        ArrayList<Individual> result = new ArrayList<>();
        List<Individual> original=getPopulation();
        for(int i=0; i<s;i++){
            Population p = new Population(permutation(original));
            //System.out.print(p);
            //System.out.println("---------");
            for(int j = 0; j< p.getPopulation().size(); j+=s){
                Individual x=p.getPopulation().get(j);
                for(int k = j; k <j+s; k++)
                    if(x.getFitness()<p.getPopulation().get(k).getFitness())
                       x=p.getPopulation().get(k);
                //System.out.println("add: "+x);
                result.add(x);
            }
        }
        return result;
    }

    public double maxFitness(){
        double x=Double.MIN_VALUE;
        for(Individual i:getPopulation())
            if(i.getFitness()>x)
                x=i.getFitness();
        return x;
    }

    public double minFitness(){
        double x=Double.MAX_VALUE;
        for(Individual i:getPopulation())
            if(i.getFitness()<x)
                x=i.getFitness();
        return x;
    }

    public double mediaFitness(){
        return getSumFitness()/getPopulation().size();
    }

    public Population clone(){
        List<Individual> m=new ArrayList<>();
        for(Individual i: getPopulation())
            m.add(i);
        return new Population(m);
    }
    public static Integer [] permutation(int n){
        Integer [] v=new Integer[n];
        for( int i=0; i<n; i++)
            v[i]=i;
        Random generator = new Random(0);
        for(int i=0;i<n-1;i++) {
            int  r = ((int)(Math.round(generator.nextDouble()*(n-1-i))+i));
            int x=v[i];
            v[i]=v[r];
            v[r]=x;
        }
        return v;
    }

    public List<Individual> getNextGeneration(int s, double pm, double pc) {
        List<Individual> a=new ArrayList<>(tournamentSelectionWithoutReplacement(s));
        for(int i=0;i<a.size();i+=2){
            double r=AG.generator.nextDouble();
            if(r<pc){
                List<Individual> l=Individual.onePointCrossover(a.get(i),a.get(i+1));
                a.set(i,l.get(0));
                a.set(i+1,l.get(1));
            }
        }
        for(int i=0;i<a.size();i++){
            a.set(i,a.get(i).mutation(pm));
        }
        List<Individual> result=new ArrayList<>();
        for(Individual i:a){
            result.add(new Individual(i.toString(),i.oneMax()));
        }
        return result;
    }
}
