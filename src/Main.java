import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    public void start(Stage arg0) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Login.fxml")));
        Scene loginPage = new Scene(root);
        //
        arg0.setTitle("Library System");
        arg0.setScene(loginPage);
        arg0.show();
        arg0.setResizable(false);
    }

    public static void main(String args[]){
        launch(args);
    }
}
