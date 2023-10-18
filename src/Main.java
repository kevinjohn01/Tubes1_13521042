import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * The Main class. The program is executed through this class.
 *
 * @author Jedid Ahn
 *
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("InputFrame.fxml"));
        primaryStage.setTitle("Adjacency Gameplay");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
//        launch(args);
        Button[][] buttons = new Button[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 4; j++){
                buttons[i][j] = new Button();
                buttons[i][j].setText("X");
            }
        }
        for(int i = 0; i < 8; i++){
            for(int j = 4; j < 8; j++){
                buttons[i][j] = new Button();
                buttons[i][j].setText("O");
            }
        }

        buttons[0][6].setText("");
        buttons[0][5].setText("");
        buttons[0][4].setText("");
        buttons[1][4].setText("");

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                System.out.print(buttons[i][j].getText());
                if (buttons[i][j].getText().equals("")){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

//
//        Tree t = new Tree(buttons, 2, 0,4, true);
//
//        for(int[] i: t.getListMax()){
//            System.out.println(i[0] + " " + i[1]);
//        }

//        Tree t1 = new Tree(buttons, 2, 2, 0,6, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
//
//        System.out.println(t.getVal());
//        System.out.println(t1.getVal());
//        System.out.println("Size t: " + t.getTreeList().size());
//        System.out.println("Size t[0]: " + t.getTreeList().get(0).getTreeList().size());
//        System.out.println("Size t[0][0]: " + t.getTreeList().get(0).getTreeList().get(0).getTreeList().size());

        System.out.println("Done");
//
        BotGeneticAlgorithm bmm = new BotGeneticAlgorithm();
        bmm.setIndividu(buttons,2,true);
    }
}