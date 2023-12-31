import javafx.application.Application;
import javafx.scene.control.Button;
import java.util.ArrayList;
import javafx.application.Application;

public class Tree {
    private int val;
    private ArrayList<Tree> treeList;
    private ArrayList<int[]> listMax;
    private int alpha;
    private int beta;

    public Tree(Button[][] buttons, int h, int i_tree, int j_tree, boolean giliransaya){
        listMax = new ArrayList<int[]>();
        treeList = new ArrayList<Tree>();
        // hitung value sendiri di h = 1
        if (h == 1){
            listMax.add(new int[]{i_tree,j_tree});
            buttons = updateButtons(buttons, i_tree, j_tree, giliransaya);
            int nX = 0;
            int nO = 0;
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if (buttons[i][j].getText().equals("X")){
                        nX++;
                    }
                    if (buttons[i][j].getText().equals("O")){
                        nO++;
                    }
                }
            }
//            System.out.println(i_tree + " " + j_tree + " Buttons: ");
//            printButtons(buttons);
            this.val = nO-nX;
//            System.out.println("val: " + this.val);
        }
        // rekursif buat tree child
        else{
            buttons = updateButtons(buttons,i_tree,j_tree,giliransaya);
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if (buttons[i][j].getText().isEmpty()){
                        Tree updatedTree = new Tree(buttons, h-1, i, j, !giliransaya);
                        this.treeList.add(updatedTree);
                    }
                }
            }
            if(giliransaya) {
                this.val = max(this.treeList);
                for(Tree t: this.treeList){
                    if (t.getVal() == this.val){
                        this.listMax.add(new int[]{i_tree, j_tree});
                        this.listMax.addAll(t.getListMax());
                        break;
                    }
                }
            } else {
                this.val = min(this.treeList);
                for(Tree t: this.treeList){
                    if (t.getVal() == this.val){
                        this.listMax.add(new int[]{i_tree, j_tree});
                        this.listMax.addAll(t.getListMax());
                        break;
                    }
                }
            }

        }
    }

    public Tree(Button[][] buttons, int h, int h_init, int i_tree, int j_tree, boolean giliransaya, int alpha, int beta){
        System.out.println("h: " + h);
        treeList = new ArrayList<Tree>();
        // hitung value sendiri di h = 1
        if (h == 1 || (h_init-h == 1)){
            System.out.println("..");
            buttons = updateButtons(buttons, i_tree, j_tree, giliransaya);
            int nX = 0;
            int nO = 0;
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if (buttons[i][j].getText().equals("X")){
                        nX++;
                    }
                    if (buttons[i][j].getText().equals("O")){
                        nO++;
                    }
                }
            }
            System.out.println(i_tree + " " + j_tree + " Buttons: ");
            //printButtons(buttons);
            this.val = nO-nX;
            System.out.println("val: " + this.val);

            //assign value alpha beta
            this.alpha = alpha;
            this.beta = beta;
        }
        // rekursif buat tree child
        else{
            if (giliransaya) {
                int maxEval = Integer.MIN_VALUE;
                System.out.println("...");
                buttons = updateButtons(buttons, i_tree, j_tree, true);
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (buttons[i][j].getText().isEmpty()) {
                            Tree updatedTree = new Tree(buttons, h - 1, h,  i, j, false, alpha, beta);
                            maxEval = Math.max(maxEval, updatedTree.getVal());
                            System.out.println("maxEval:" + maxEval);
                            this.alpha = Math.max(this.alpha, updatedTree.getVal());

                            if (this.alpha >= this.beta) {//pruning
                                break;
                            }
                            this.treeList.add(updatedTree);
                        }
                    }
                    if (this.alpha >= this.beta) {//pruning
                        break;
                    }
                }
                this.val = maxEval;
            } else {
                int minEval = Integer.MAX_VALUE;
                System.out.println("...");
                buttons = updateButtons(buttons, i_tree, j_tree, false);
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (buttons[i][j].getText().isEmpty()) {
                            Tree updatedTree = new Tree(buttons, h - 1, h, i, j, true, alpha, beta);
                            minEval = Math.min(minEval, updatedTree.getVal());
                            System.out.println("maxEval:" + minEval);

                            this.beta = Math.min(this.beta, updatedTree.getVal());

                            if (this.alpha >= this.beta) {//pruning
                                break;
                            }
                            this.treeList.add(updatedTree);
                        }
                    }
                    if (this.alpha >= this.beta) {//pruning
                        break;
                    }
                }
                this.val = minEval;
            }

            System.out.println("else " + this.val);
        }

        System.out.println("alpha: "+this.alpha);
        System.out.println("beta: "+this.beta);
    }

    public int getVal(){
        return this.val;
    }

    public int getAlpha(){
        return this.alpha;
    }

    public int getBeta(){
        return this.beta;
    }

    public ArrayList<int[]> getListMax(){
        return this.listMax;
    }
    public void setVal(int value) {
        this.val = value;
    }

    public int max(ArrayList<Tree> listval){
        int max = -9999;
        for (Tree j : listval) {
            if (j.getVal() >= max) {
                max = j.getVal();
            }
        }
        return max;
    }

    public int min(ArrayList<Tree> listval){
        int min = 9999;
        for (Tree j : listval) {
            if (j.getVal() <= min) {
                min = j.getVal();
            }
        }
        return min;
    }

    public ArrayList<Tree> getTreeList(){
        return this.treeList;
    }
    public Button[][] updateButtons(Button[][] oldButtons, int i_tree, int j_tree, boolean giliransaya){
        Button[][] newButtons = new Button[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                newButtons[i][j] = new Button();
                newButtons[i][j].setText(oldButtons[i][j].getText());
            }
        }
        if (giliransaya){
            // giliran O
            newButtons[i_tree][j_tree].setText("O");
            // cek atas
            if (i_tree-1 >= 0 && newButtons[i_tree-1][j_tree].getText().equals("X")){
                newButtons[i_tree-1][j_tree].setText("O");
            }
            // cek bawah
            if (i_tree+1 < 8 && newButtons[i_tree+1][j_tree].getText().equals("X")){
                newButtons[i_tree+1][j_tree].setText("O");
            }
            // cek kiri
            if (j_tree-1 >= 0 && newButtons[i_tree][j_tree-1].getText().equals("X")){
                newButtons[i_tree][j_tree-1].setText("O");
            }
            // cek kanan
            if (j_tree+1 < 8 && newButtons[i_tree][j_tree+1].getText().equals("X")){
                newButtons[i_tree][j_tree+1].setText("O");
            }
        }
        else{
            // giliran X
            newButtons[i_tree][j_tree].setText("X");
            // cek atas
            if (i_tree-1 >= 0 && newButtons[i_tree-1][j_tree].getText().equals("O")){
                newButtons[i_tree-1][j_tree].setText("X");
            }
            // cek bawah
            if (i_tree+1 < 8 && newButtons[i_tree+1][j_tree].getText().equals("O")){
                newButtons[i_tree+1][j_tree].setText("X");
            }
            // cek kiri
            if (j_tree-1 >= 0 && newButtons[i_tree][j_tree-1].getText().equals("O")){
                newButtons[i_tree][j_tree-1].setText("X");
            }

            // cek kanan
            if (j_tree+1 < 8 && newButtons[i_tree][j_tree+1].getText().equals("O")){
                newButtons[i_tree][j_tree+1].setText("X");
            }
        }
        return newButtons;
    }

    public void printButtons(Button[][] buttons){
        String[][] m = new String[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                m[i][j] = buttons[i][j].getText();
            }
        }
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                System.out.print(m[i][j]);
                if (m[i][j].isEmpty()){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

}
