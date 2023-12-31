import javafx.scene.control.Button;

public class BotMiniMax extends Bot{
    private int alpha;
    private int beta;

    @Override
    public int[] move(Button[][] buttons, int round) {
        // initial value max_val
        int i_max = 0;
        int j_max = 0;
        this.alpha = Integer.MIN_VALUE;
        this.beta = Integer.MAX_VALUE;
        //buttons0 = updateButton(buttons, i_max, j_max);

        //Tree t0 = new Tree(buttons0, round,i_max, j_max, true, alpha, beta);
        int max_val = Integer.MIN_VALUE;

//        for(int i = 0; i < 8; i++){
//            for(int j = 0; j < 8; j++){
//                if (buttons[i][j].getText().isEmpty()) {
//                    Tree t = new Tree(buttons, round, i, j, false, alpha, beta);
//                    maxEval = Math.max(t.getAlpha(),t.getVal());
//                    this.alpha = Math.max(t.getBeta(), t.getVal());
//                    this.beta = t.getBeta();
//                    if (t.getVal() >= max_val) {
//                        max_val = t.getVal();
//                        i_max = i;
//                        j_max = j;
//                    }
//                    if (this.alpha >= this.beta) {
//                        break;
//                    }
//                }
//            }
//        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.println(".");
                if (buttons[i][j].getText().equals("")) {
                    System.out.println("masuk ga " + i + " " + j);
                    Tree t = new Tree(buttons, round, round, i, j, false, this.alpha, this.beta);
                    max_val = Math.max(max_val, t.getVal());
                    this.alpha = Math.max(t.getVal(), this.alpha);
                    System.out.println("bval: " + t.getVal() + " bmax: " + max_val);
                    if (t.getVal() >= max_val) {
                        i_max = i;
                        j_max = j;
                    }
                    if (this.alpha >= this.beta) {//pruning
                        break;
                    }

                }
            }
            if (this.alpha >= this.beta) {//pruning
                break;
            }
        }
        System.out.println(max_val);
        return new int[]{i_max,j_max};
    }
}
