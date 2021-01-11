package app2;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Main {
    public static void testF(){
        Scanner sc = new Scanner(System.in);
        int n= sc.nextInt();
        int l= sc.nextInt();
        AG ag=new AG(n,l);
        System.out.print(ag.getPopulation());
    }

    public static void testG(){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        Individual x = new Individual (s);
        System.out.println(x.oneMax());
    }

    public static void testH(){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        Individual x = new Individual(s);
        System.out.println((int)Math.pow(x.converterDecimal(),2));
    }

    public static void testI(){
        Scanner sc = new Scanner(System.in);
        int n= sc.nextInt();
        int l= sc.nextInt();
        AG ag=new AG(n,l);
        for(int i=0;i<n;i++)
            ag.getPopulation().getPopulation().get(i).setFitness(sc.nextDouble());
        for(Individual i: ag.getPopulation().torneio())
            System.out.println(i);
    }

    public static void testJ(){
        Scanner sc = new Scanner(System.in);
        List<Individual> membros=new ArrayList<>();
        while(sc.hasNext()){
            membros.add(new Individual(sc.next(),sc.nextDouble()));
        }
        AG ag =new AG(membros);
        Collections.sort(ag.getPopulation().getPopulation(), Collections.reverseOrder());
        //System.out.println(ag.getPopulation());
        List<Individual> result=ag.getPopulation().selection();
        result.sort(Comparator.comparing(Individual::toString));
        for (Individual i: result)
            System.out.println(i);
    }

    public static void testK(){
        Scanner sc = new Scanner(System.in);
        List<Individual> membros=new ArrayList<>();
        while(sc.hasNext()){
            membros.add(new Individual(sc.next(),sc.nextDouble()));
        }
        AG ag =new AG(membros);
        List<Individual> result=ag.getPopulation().selectionSUS();
        //result.sort(Comparator.comparing(Individual::toString));
        for (Individual i: result)
            System.out.println(i);
    }

    public static void testL(){
        Scanner sc = new Scanner(System.in);
        List<Individual> result=Individual.onePointCrossover(new Individual(sc.next()),new Individual(sc.next()));
        for (Individual i: result)
            System.out.println(i);
    }

    public static void testM(){
        Scanner sc = new Scanner(System.in);
        AG ag=new AG();
        List<Individual> result=Individual.uniformCrossover(new Individual(sc.next()),new Individual(sc.next()));
        for (Individual i: result)
            System.out.println(i);
    }

    public static void testN(){
        Scanner sc = new Scanner(System.in);
        double pm=sc.nextDouble();
        Individual i=new Individual(sc.next());
        System.out.println(i.mutation(pm));
    }


    public static void testO(){
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        Integer [] v=Population.permutation(n);
        for (int i=0;i<n;i++)
            System.out.println(v[i]);
    }



    public static void testP(){
        Scanner sc = new Scanner(System.in);
        int s=sc.nextInt();
        List<Individual> membros=new ArrayList<>();
        while(sc.hasNext()){
            membros.add(new Individual(sc.next(),sc.nextDouble()));
        }
        AG ag =new AG(membros);
        List<Individual> result=ag.getPopulation().tournamentSelectionWithoutReplacement(s);
        for (Individual i: result)
            System.out.println(i);
    }

    public static void testQ(){
        Scanner sc = new Scanner(System.in);
        int n= sc.nextInt();
        int l= sc.nextInt();
        AG ag=new AG(n,l);
        int s=sc.nextInt();
        double pm=sc.nextDouble();
        double pc=sc.nextDouble();
        int g=1;
        DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
        unusualSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", unusualSymbols);
        List<Individual> membros=new ArrayList<>();
        for(Individual i:ag.getPopulation().getPopulation()){
            membros.add(new Individual(i.toString(),i.oneMax()));
        }
        ag=new AG(membros);
        for (int i=0; i < g + 1; i++) {
            System.out.println(i+": "+df.format(ag.getPopulation().maxFitness())+" " + df.format(ag.getPopulation().mediaFitness())+" "+df.format(ag.getPopulation().minFitness()));
            ag=new AG(ag.getPopulation().getNextGeneration(s,pm,pc));
        }
    }

    public static void testR(){
        Scanner sc = new Scanner(System.in);
        int n= sc.nextInt();
        int l= sc.nextInt();
        AG ag=new AG(n,l);
        int s=sc.nextInt();
        double pm=sc.nextDouble();
        double pc=sc.nextDouble();
        int g=sc.nextInt();
        DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
        unusualSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", unusualSymbols);
        List<Individual> membros=new ArrayList<>();
        for(Individual i:ag.getPopulation().getPopulation()){
            membros.add(new Individual(i.toString(),i.oneMax()));
        }
        ag=new AG(membros);
        for (int i=0; i < g + 1; i++) {
            System.out.println(i+": "+df.format(ag.getPopulation().maxFitness())+" " + df.format(ag.getPopulation().mediaFitness())+" "+df.format(ag.getPopulation().minFitness()));
            ag=new AG(ag.getPopulation().getNextGeneration(s,pm,pc));
        }
    }
    public static void main(String[]args){
        //testF();
        //testG();
        //testH();
        //testI();
        //testJ();
        //testK();
        //testL();
        //testM();
        //testN();
        //testO();
        //testP();
        testQ();
        // testR();
    }
}
