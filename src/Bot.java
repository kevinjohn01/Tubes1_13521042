import javafx.scene.control.Button;

public class Bot {
    public int[] move(Button[][] buttons, int round) {
        // create random move
        return new int[]{(int) (Math.random()*8), (int) (Math.random()*8)};
    }

    public int miniMax(Button[][] buttons, int round){
        // anggap udah ada list tree yang bisa diperiksa
    }
}
