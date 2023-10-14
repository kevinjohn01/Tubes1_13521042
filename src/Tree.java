import javafx.scene.control.Button;
import java.util.ArrayList;

public class Tree {
    private int val;
    private ArrayList<Tree> treeList;

    public Tree(Button[][] buttons, int h, int h_init, int i_tree, int j_tree){
        // hitung value sendiri
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

        // rekursif buat tree child
        if (h != 0){
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if (buttons[i][j].getText().equals("")){
                        treeList.add(new Tree(updateButtons(buttons, h, h_init), h-1, h_init, i_tree, j_tree));
                    }
                }
            }
        }
    }

    public Button[][] updateButtons(Button[][] oldButtons, int h, int h_init){
        Button[][] newButtons = oldButtons;
        if ((h_init-h)%2 == 0){
            // giliran O
            newButton[][]
        }
        else{

        }
    }
}
