import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GUI extends Application {
    @Override
    public void start(Stage primaryStage){
        //title of the stage
        primaryStage.setTitle("Shopaholic");

        //pane to place all other aspects
        BorderPane border =  new BorderPane();

        //scene which displays initially upon launch
        Scene mainScene = new Scene(border, 500, 500);

        //setting initial scene
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
    public static void main(String[] args) throws Exception{
        launch(args);
    }
}
