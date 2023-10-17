import javafx.scene.control.Button;
import java.util.Timer;
import java.util.TimerTask;

public class Bot {

    private volatile boolean timerExpired = false;
    public Button[][] copyButton(Button[][] buttons){
        Button[][] salinanButton = new Button[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (buttons[i][j] != null) {
                    salinanButton[i][j] = new Button();
                    salinanButton[i][j].setText(buttons[i][j].getText());
                } else {
                    salinanButton[i][j] = null;
                }
            }
        }
        return salinanButton;
    }

    public int[] move(Button[][] buttons, int round) {
        // create random move
        //int i = 0;
        //System.out.print("ROUNDDDDDDDDDDDDDDDDDD ");
        //System.out.println(i);
        //i+=1;
        return localSearch(buttons, round);

    }


    public int[] localSearch(Button[][] buttons, int round) {
        // Simulate a 5-second delay
        final int[] localSearchResult = {0, 0}; // Placeholder result

        // Create a timer to limit the search to 5 seconds
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // After 5 seconds, cancel the search and return the current result
                timer.cancel();
            }
        }, 5000);
        Button[][] salinanButton = copyButton(buttons);
        Button[][] salinanButtonNeighbor = copyButton(buttons);
        Button[][] current = updateButton(salinanButton, 0, 0);
        int i = 0;
        int j = 0;
        int n = 5;
        int valueCurrent = ObjectiveFunction(current);
        int[] neighbor;
        neighbor = highValue(salinanButtonNeighbor);
        while (neighbor[0] >= valueCurrent && !Thread.currentThread().isInterrupted() && n <= 5) {
            if(neighbor[0] == valueCurrent){
                n+=1;
            }
            salinanButton = copyButton(buttons);
            current = updateButton(salinanButton, neighbor[1], neighbor[2]);
            i = neighbor[1];
            j = neighbor[2];
            valueCurrent = neighbor[0];
            salinanButtonNeighbor = copyButton(buttons);
            neighbor = highValue(salinanButtonNeighbor);
            System.out.println("Hi ini dari high value");
            System.out.println(neighbor[0]);
        }


        // Update the result only if the timer hasn't canceled the search
        localSearchResult[0] = i;
        localSearchResult[1] = j;
        System.out.println("Hi ini dari local search");
        System.out.println(i);
        System.out.println(j);
        return localSearchResult;
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

    public int[] highValue(Button[][] buttons) {
        Button[][] simpanButton = copyButton(buttons);
        int max = 0;
        int horizon = 0;
        int vertikal = 0;
        int value = 0;
        Button[][] buttonsUpdate = copyButton(updateButton(simpanButton, 0, 0));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(buttons[i][j].getText().equals("")){
                    buttonsUpdate = copyButton(updateButton(simpanButton, i, j));
                    simpanButton = copyButton(buttons);
                    value = ObjectiveFunction(buttonsUpdate);
                    System.out.println("Ini Value");
                    System.out.println(value);
                    System.out.println("Tetangga yang dibangkitkan");
                    System.out.println(i);
                    System.out.println(j);
                    if (value > max) {
                        max = value;
                        horizon = i;
                        vertikal = j;
                    }

                }
                value = 0;
            }
        }
        int[] result = { max, horizon, vertikal };
        return result;
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
