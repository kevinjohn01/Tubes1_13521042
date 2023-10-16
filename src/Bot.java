import javafx.scene.control.Button;

public class Bot {
    public int[] move(Button[][] buttons, int round) {
        // create random move
        return new int[]{(int) (Math.random()*8), (int) (Math.random()*8)};
    }

    public int[] miniMax(Button[][] buttons, int round){
        // anggap tree udah bisa dipakai
        int i_max = firstEmpty(buttons)[0];
        int j_max = firstEmpty(buttons)[1];
        Tree t0 = new Tree(buttons, round, round,i_max, j_max);
        int max_val = t0.solve();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if (buttons[i][j].getText().equals("")){
                    Tree t = new Tree(buttons, round, round, i, j);
                    if (t.solve() > max_val){
                        max_val = t.solve();
                        i_max = i;
                        j_max = j;
                    }
                }
            }
        }
        return new int[]{i_max,j_max};
    }

    public int[] firstEmpty(Button[][] buttons){
        boolean found = false;
        int i,j;
        i = 0;
        j = 0;
        while (i < 8 && !found){
            j = 0;
            while (j < 8 && !found){
                if (buttons[i][j].getText().equals("")){
                    found = true;
                }
                else{
                    j++;
                }
            }
            if (!found){
                i++;
            }
        }
        return new int[]{i,j};
    }
}
