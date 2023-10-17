import javafx.scene.control.Button;

public class BotMiniMax extends Bot{
    public int[] move(Button[][] buttons, int round) {
        // initial value max_val
        int i_max = firstEmpty(buttons)[0];
        int j_max = firstEmpty(buttons)[1];
        Button[][] buttons0 = new Button[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                buttons0[i][j] = new Button();
            }
        }
        buttons0 = updateButton(buttons, i_max, j_max);
        Tree t0 = new Tree(buttons0, round,i_max, j_max, true);
        int max_val = t0.getVal();

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if (buttons[i][j].getText().isEmpty()){
                    Tree t = new Tree(buttons, round, i, j, true);
                    if (t.getVal() > max_val){
                        max_val = t.getVal();
                        i_max = i;
                        j_max = j;
                    }
                }
            }
        }
        return new int[]{i_max,j_max};
    }
}
