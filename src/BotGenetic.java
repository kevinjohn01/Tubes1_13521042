import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Random;

public class BotGenetic extends Bot {
    static double mutationProb = 1;
    static int popsize = 4;

    public void Mutation(ArrayList<Integer> mutationArr) {
        ArrayList population = new ArrayList<>();
        BotGenetic result = new BotGenetic();
        Random random = new Random();
        double mutationProbCheck = random.nextDouble() * 10;
        if (mutationProbCheck <= mutationProb) {
            int tmpVal = random.nextInt(4);
            int currentVal = mutationArr.get(tmpVal);
            if (currentVal == 1)
                mutationArr.set(tmpVal, 0);
            else
                mutationArr.set(tmpVal, 1);
            System.out.println("Setelah mutasi: " + mutationArr);
        } else {
            System.out.println("Tidak ada mutasi " + mutationArr);
        }
        boolean checking = population.contains(mutationArr);
        population.add(mutationArr);
        popsize += 1;
    }
}
