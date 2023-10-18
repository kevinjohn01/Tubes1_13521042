import javafx.scene.control.Button;
import java.util.Timer;
import java.util.TimerTask;

abstract public class Bot {

    abstract public int[] move(Button[][] buttons, int round);
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
