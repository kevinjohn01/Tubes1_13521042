import javafx.scene.control.Button;
import java.util.ArrayList;

public class Tree {
    private int val;
    private ArrayList<Tree> treeList;

    public Tree(Button[][] buttons, int h, int h_init, int i_tree, int j_tree){
        // hitung value sendiri di h = 0
        if (h == 0){
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
            this.val = nO-nX;
        }
        // rekursif buat tree child
        else{
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if (buttons[i][j].getText().equals("")){
                        treeList.add(new Tree(updateButtons(buttons, h, h_init, i_tree, j_tree), h-1, h_init, i_tree, j_tree));
                    }
                }
            }
        }
    }

    public Button[][] updateButtons(Button[][] oldButtons, int h, int h_init, int i_tree, int j_tree){
        Button[][] newButtons = oldButtons;
        if ((h_init-h)%2 == 0){
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

    public int solve(){
        return 0;
    }
}
