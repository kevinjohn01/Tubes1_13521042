import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Random;

public class BotGeneticAlgorithm extends Bot{
    static double mutationProb = 1;
    static int popsize = 4;
    private ArrayList<int[]> oneIndividu;
    private ArrayList<int[]> twoIndividu;
    private ArrayList<int[]> threeIndividu;
    private ArrayList<int[]> fourIndividu;
    private int persenOne;
    private int persenTwo;
    private int persenThree;
    private int persenFour;
    private ArrayList<int[]> simpan;
    public BotGeneticAlgorithm(){
        this.oneIndividu = new ArrayList<int[]>();
        this.twoIndividu = new ArrayList<int[]>();
        this.threeIndividu = new ArrayList<int[]>();
        this.fourIndividu = new ArrayList<int[]>();
        this.simpan = new ArrayList<int[]>();

    }
    public void getRandom(){
        Random random = new Random();
        this.persenOne = random.nextInt(101);
        this.persenTwo = random.nextInt(101);
        this.persenThree = random.nextInt(101);
        this.persenFour = random.nextInt(101);
        System.out.println(persenFour);
        System.out.println(persenThree);
        System.out.println(persenTwo);
        System.out.println(persenOne);
    }
    public void setIndividu(Button[][] buttons, int h, boolean giliransaya){
        int minVal = 1000;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(buttons[i][j].getText().isEmpty()){
                    Tree t = new Tree(buttons, h, i, j, giliransaya);
                    System.out.println("Cek 1");
                    System.out.println(i);
                    System.out.println(j);
                    int[] array1 = new int[]{t.getVal(),i,j};
                    if(t.getVal() < minVal){
                        minVal = t.getVal();
                    }
                    System.out.println(array1[0]);
                    this.simpan.add(array1);
                }
            }
        }
        for (int[] arrayupdate : this.simpan) {
            if (arrayupdate.length > 0) {
                arrayupdate[0] = arrayupdate[0]+Math.abs(minVal);
            }
        }
        int sum = 0;
        for (int[] array : this.simpan) {
            if (array.length > 0) {
                System.out.println("Aduh");
                System.out.println(array[0]);
                sum += array[0];
            }
        }

        for (int[] arrayupdate : this.simpan) {
            if (arrayupdate.length > 0) {
                System.out.println("Updatee");
                System.out.println(arrayupdate[0]);
                System.out.println(sum);
                arrayupdate[0] = arrayupdate[0]/sum;
                System.out.println(arrayupdate[0]);
            }
        }
        getRandom();
        int[] previous = null;
        for(int[] arraycheck : this.simpan){
            if(previous != null){
                if((arraycheck[0] > persenOne) && (previous[0] < arraycheck[0])){
                    int[] arr = new int[]{arraycheck[1],arraycheck[2]};
                    this.oneIndividu.clear();
                    this.oneIndividu.add(arr);
                }
                if((arraycheck[0] > persenTwo) && (previous[0] < arraycheck[0])){
                    int[] arr = new int[]{arraycheck[1],arraycheck[2]};
                    this.twoIndividu.clear();
                    this.twoIndividu.add(arr);
                }
                if((arraycheck[0] > persenThree) && (previous[0] < arraycheck[0])){
                    int[] arr = new int[]{arraycheck[1],arraycheck[2]};
                    this.threeIndividu.clear();
                    this.threeIndividu.add(arr);
                }
                if((arraycheck[0] > persenFour) && (previous[0] < arraycheck[0])){
                    int[] arr = new int[]{arraycheck[1],arraycheck[2]};
                    this.fourIndividu.clear();
                    this.fourIndividu.add(arr);
                }

            }
            else{
                int[] arr = new int[]{arraycheck[1],arraycheck[2]};
                this.oneIndividu.add(arr);
                this.twoIndividu.add(arr);
                this.threeIndividu.add(arr);
                this.fourIndividu.add(arr);
            }
            previous = arraycheck;
        }
        for (int[] array : this.oneIndividu) {
            int firstElement = array[0];
            int secondElement = array[1];
            Tree t1 = new Tree(buttons,h,firstElement,secondElement,giliransaya);
            //this.oneIndividu.clear();
            this.oneIndividu = t1.getListMax();
        }
        for (int[] array : this.twoIndividu) {
            int firstElement = array[0];
            int secondElement = array[1];
            Tree t2 = new Tree(buttons,h,firstElement, secondElement, true);
            //this.twoIndividu.clear();
            this.twoIndividu = t2.getListMax();
        }

        for (int[] array : this.threeIndividu) {
            int firstElement = array[0];
            int secondElement = array[1];
            Tree t3 = new Tree(buttons,h,firstElement, secondElement, true);
            //this.threeIndividu.clear();
            this.threeIndividu = t3.getListMax();
        }
        for (int[] array : this.fourIndividu) {
            int firstElement = array[0];
            int secondElement = array[1];
            Tree t4 = new Tree(buttons,h,firstElement, secondElement, true);
            //this.fourIndividu.clear();
            this.fourIndividu = t4.getListMax();
        }

        for(int[] i: this.oneIndividu){
            System.out.println(i[0] + " " + i[1]);
        }
        System.out.println("AAA");
        for(int[] i: this.twoIndividu){
            System.out.println(i[0] + " " + i[1]);
        }
        System.out.println("AAA");
        for(int[] i: this.threeIndividu){
            System.out.println(i[0] + " " + i[1]);
        }
        System.out.println("AAA");
        for(int[] i: this.fourIndividu){
            System.out.println(i[0] + " " + i[1]);
        }


    }

    public void Mutation(ArrayList<Integer> mutationArr) {
        ArrayList population = new ArrayList<>();
        BotGenetic result = new BotGenetic();
        Random random = new Random();
        double mutationProbCheck = random.nextDouble() * 10;
        if (mutationProbCheck <= mutationProb) {
            int tmpVal = random.nextInt(4);
            int currentVal = mutationArr.get(tmpVal);
            if (currentVal == 1)
                mutationArr.set(tmpVal, 0);
            else
                mutationArr.set(tmpVal, 1);
            System.out.println("Setelah mutasi: " + mutationArr);
        } else {
            System.out.println("Tidak ada mutasi " + mutationArr);
        }
        boolean checking = population.contains(mutationArr);
        population.add(mutationArr);
        popsize += 1;
    }


}