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
        System.out.println("Ini hasil random");
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
            System.out.println("Cek 2");
            if (arrayupdate.length > 0) {
                arrayupdate[0] = arrayupdate[0]+Math.abs(minVal);
                System.out.println(arrayupdate[0]);
            }
        }
        int sum = 0;
        for (int[] array : this.simpan) {
            System.out.println("Cek 3");
            if (array.length > 0) {
                System.out.println(array[0]);
                sum += array[0];
            }
            System.out.println(sum);
        }
        int before = 0;
        for (int[] arrayupdate : this.simpan) {
            if (arrayupdate.length > 0) {
                System.out.println("Cek 4");
                System.out.println(arrayupdate[0]);
                System.out.println(sum);
                int nilai = arrayupdate[0];
                arrayupdate[0] = (nilai*100)/10 + before;
                System.out.println("Hasill");
                System.out.println(arrayupdate[0]);
                before = (nilai*100)/10;
            }
        }
        getRandom();
        int[] previous = null;
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        for(int[] arraycheck : this.simpan){
            if(previous != null){
                if((arraycheck[0] > persenOne) && (previous[0] < persenOne) && count1 != 1){
                    int[] array = new int[]{arraycheck[1],arraycheck[2]};
                    ArrayList<int[]> arr = new ArrayList<int[]>();
                    arr.add(array);
                    this.oneIndividu = arr;
                    count1+=1;
                }
                if((arraycheck[0] > persenTwo) && (previous[0] < persenTwo) && count2 != 1){
                    int[] array = new int[]{arraycheck[1],arraycheck[2]};
                    ArrayList<int[]> arr = new ArrayList<int[]>();
                    arr.add(array);
                    this.twoIndividu = arr;
                    count2+=1;
                }
                if((arraycheck[0] > persenThree) && (previous[0] < persenThree) && count3 != 1){
                    int[] array = new int[]{arraycheck[1],arraycheck[2]};
                    ArrayList<int[]> arr = new ArrayList<int[]>();
                    arr.add(array);
                    this.threeIndividu = arr;
                    count3+=1;
                }
                if((arraycheck[0] > persenFour) && (previous[0] < persenFour) && count4 != 1){
                    int[] array = new int[]{arraycheck[1],arraycheck[2]};
                    ArrayList<int[]> arr = new ArrayList<int[]>();
                    arr.add(array);
                    this.fourIndividu = arr;
                    count4+=1;
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
    public void crossOver(){
        int size = this.oneIndividu.size();
        int i = 0;
        for (int[] array : this.oneIndividu) {
            for(int[] array1 : this.twoIndividu){
                if(i > size/2){
                    this.oneIndividu.add(array1);
                    this.twoIndividu.add(array);
                    this.oneIndividu.remove(array);
                    this.twoIndividu.remove(array1);
                }
            }
        }
        for (int[] array : this.threeIndividu) {
            for(int[] array1 : this.fourIndividu){
                if(i > size/2){
                    this.twoIndividu.add(array1);
                    this.threeIndividu.add(array);
                    this.twoIndividu.remove(array);
                    this.threeIndividu.remove(array1);
                }
            }
        }

        System.out.println("HAsill crossover");
        for(int[] j: this.oneIndividu){
            System.out.println(j[0] + " " + j[1]);
        }
        System.out.println("AAA");
        for(int[] j: this.twoIndividu){
            System.out.println(j[0] + " " + j[1]);
        }
        System.out.println("AAA");
        for(int[] j: this.threeIndividu){
            System.out.println(j[0] + " " + j[1]);
        }
        System.out.println("AAA");
        for(int[] j: this.fourIndividu){
            System.out.println(j[0] + " " + j[1]);
        }

    }


    public ArrayList<int[]> generateRandom(Button[][] buttons) {
        ArrayList<int[]> positions = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    int[] position = {i, j};
                    positions.add(position);
                    break;
                }
            }
        }

        return positions;
    }
    public void Mutation(Button[][] buttons){
        int[] previous = null;
        ArrayList<int[]> array = new ArrayList<int []>();
        array = generateRandom(buttons);
        for(int[] j: this.oneIndividu){
            if(j == previous){
                this.oneIndividu.remove(j);
                this.oneIndividu.add(array.get(0));
            }
        }
        for(int[] j: this.twoIndividu){
            if(j == previous){
                this.twoIndividu.remove(j);
                this.twoIndividu.add(array.get(0));
            }
        }
        for(int[] j: this.threeIndividu){
            if(j == previous){
                this.threeIndividu.remove(j);
                this.threeIndividu.add(array.get(0));
            }
        }
        for(int[] j: this.threeIndividu){
            if(j == previous){
                this.fourIndividu.remove(j);
                this.fourIndividu.add(array.get(0));
            }
        }
        Mutation(buttons);

    }

    public int cekScore(Button[][] buttons, ArrayList<int[]> array){
        for(int[] j: array){
            updateButton(buttons,j[0],j[1]);
        }
        return ObjectiveFunction(buttons);

    }
    public int ObjectiveFunction(Button[][] buttons) {
        int objectiveValue = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (buttons[i][j].getText().equals("O")) {
                    objectiveValue += 1;

                } else if (buttons[i][j].getText().equals("X")) {
                    objectiveValue -= 1;
                }
            }
        }
        return objectiveValue;
    }

    public int[] geneticAlgorithm(Button[][] buttons, int h, boolean giliransaya, int valueNow){
        final int[] geneticAlgorithm = {0, 0};
        setIndividu(buttons,h,giliransaya);
        crossOver();
        for(int[] j: this.oneIndividu){
            int i = j[0];
            int k = j[1];
            updateButton(buttons,i,k);
        }
        for(int[] j: this.twoIndividu){
            int i = j[0];
            int k = j[1];
            updateButton(buttons,i,k);
        }
        for(int[] j: this.threeIndividu){
            int i = j[0];
            int k = j[1];
            updateButton(buttons,i,k);
        }
        for(int[] j: this.fourIndividu){
            int i = j[0];
            int k = j[1];
            updateButton(buttons,i,k);
        }
        Mutation(buttons);
        int a = cekScore(buttons,this.oneIndividu);
        int b = cekScore(buttons,this.twoIndividu);
        int c = cekScore(buttons,this.threeIndividu);
        int d = cekScore(buttons,this.fourIndividu);
        if(a > b && a>c && a>d){
            for(int[] j: this.oneIndividu){
                geneticAlgorithm[0] = j[0];
                geneticAlgorithm[1] = j[1];
            }

        }
        else if(b > a && b>c && b>d){
            for(int[] j: this.twoIndividu){
                geneticAlgorithm[0] = j[0];
                geneticAlgorithm[1] = j[1];
            }

        }
        else if(c > a && c>b && c>d){
            for(int[] j: this.threeIndividu){
                geneticAlgorithm[0] = j[0];
                geneticAlgorithm[1] = j[1];
            }

        }
        else if(d > a && d>b && d>c){
            for(int[] j: this.fourIndividu){
                geneticAlgorithm[0] = j[0];
                geneticAlgorithm[1] = j[1];
            }

        }
        return geneticAlgorithm;


    }
    public Button[][] updateButton(Button[][] simpan, int i, int j) {
        simpan[i][j].setText("O");
        if (i - 1 >= 0 && simpan[i-1][j].getText()=="X") {
            simpan[i - 1][j].setText("O");
        }
        if (i + 1 < 8 && simpan[i+1][j].getText()=="X") {
            simpan[i + 1][j].setText("O");
        }
        if (j - 1 >= 0 && simpan[i][j-1].getText()=="X") {
            simpan[i][j - 1].setText("O");
        }
        if (j + 1 < 8 && simpan[i][j+1].getText()=="X") {
            simpan[i][j + 1].setText("O");
        }
        return simpan;
    }
    public int[] move(Button[][] buttons, int round) {
        // create random move
        //int i = 0;
        //System.out.print("ROUNDDDDDDDDDDDDDDDDDD ");
        //System.out.println(i);
        //i+=1;
        return geneticAlgorithm(buttons, round, true,0);

    }


}