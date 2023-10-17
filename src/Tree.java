import javafx.application.Application;
import javafx.scene.control.Button;
import java.util.ArrayList;
import javafx.application.Application;

public class Tree {
    private int val;
    private ArrayList<Tree> treeList;

    public Tree(Button[][] buttons, int h, int h_init, int i_tree, int j_tree, boolean giliransaya){
        treeList = new ArrayList<Tree>();
        // hitung value sendiri di h = 0
        if (h == 1){
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
            System.out.println("Buttons: ");
            printButtons(buttons);
            this.val = nO-nX;
            System.out.println("val: " + this.val);
        }
        // rekursif buat tree child
        else{
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if (buttons[i][j].getText().isEmpty()){
                        Button[][] newButtons = new Button[8][8];
                        for(int is = 0; is < 8; is++){
                            for(int js = 0; js < 8; js++){
                                newButtons[i][j] = new Button();
                            }
                        }
                        newButtons = updateButtons(buttons, h, h_init, i, j);
                        Tree updatedTree = new Tree(newButtons, h-1, h_init, i, j, !giliransaya);
                        this.treeList.add(updatedTree);
                    }
                }
            }
            if(giliransaya) {
                this.val = max(this.treeList);
            } else {
                this.val = min(this.treeList);
            }
        }
    }

    public int getVal(){
        return this.val;
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
    public Button[][] updateButtons(Button[][] oldButtons, int h, int h_init, int i_tree, int j_tree){
        Button[][] newButtons = new Button[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                newButtons[i][j] = new Button();
                newButtons[i][j].setText(oldButtons[i][j].getText());
            }
        }
        if ((h_init-h)%2 == 0){
            System.out.println("Giliran O");
            // giliran O
            newButtons[i_tree][j_tree].setText("O");
            // cek atas
            if (i_tree-1 > 0 && newButtons[i_tree-1][j_tree].getText().equals("X")){
                newButtons[i_tree-1][j_tree].setText("O");
            }
            // cek bawah
            if (i_tree+1 < 8 && newButtons[i_tree+1][j_tree].getText().equals("X")){
                newButtons[i_tree+1][j_tree].setText("O");
            }
            // cek kiri
            if (j_tree-1 > 0 && newButtons[i_tree][j_tree-1].getText().equals("X")){
                newButtons[i_tree][j_tree-1].setText("O");
            }
            // cek kanan
            if (j_tree+1 < 8 && newButtons[i_tree][j_tree+1].getText().equals("X")){
                newButtons[i_tree][j_tree+1].setText("O");
            }
        }
        else{
            System.out.println("Giliran X");
            // giliran X
            newButtons[i_tree][j_tree].setText("X");
            // cek atas
            if (i_tree-1 > 0 && newButtons[i_tree-1][j_tree].getText().equals("O")){
                newButtons[i_tree-1][j_tree].setText("X");
            }
            // cek bawah
            if (i_tree+1 < 8 && newButtons[i_tree+1][j_tree].getText().equals("O")){
                newButtons[i_tree+1][j_tree].setText("X");
            }
            // cek kiri
            if (j_tree-1 > 0 && newButtons[i_tree][j_tree-1].getText().equals("O")){
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
