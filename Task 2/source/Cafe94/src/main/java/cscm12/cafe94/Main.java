package cscm12.cafe94;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**[Main]
 * Main place where the stage starts to branch out, the login page (root).
 * @author Sumi Sunuwar
 * @version 1.0
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("StaffLogin.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Cafe94");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}