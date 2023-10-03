import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.*;
import java.util.Scanner;

public class Login {

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegister;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtuserID;

    @FXML
    void btnLoginPressed() throws IOException {
        File f = new File("LoginDetails.txt");
        Librarian librarian = new Librarian();
        librarian.setUserID(txtuserID.getText());
        librarian.setPassword(txtPassword.getText());
        String ID = "";
        String pass = "";
        String name = "";
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String row = sc.nextLine();
                String[] details;
                details = row.split(",");
                ID = details[0];
                pass = details[2];
                name = details[1];
                if (txtuserID.getText().equals(ID) && txtPassword.getText().equals(pass)) {
                    librarian.setName(name);
                    System.out.println("Login successfully");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) btnLogin.getScene().getWindow();
                    stage.setScene(new Scene((root), 600, 400));
                    stage.setTitle("Homepage");
                    Homepage homepage = loader.getController();
                    homepage.retrieveSession(librarian);
                    homepage.showUserName();

                    break;
                } else {
                    System.out.println("Login failed! Please try again.");
                }
            } sc.close();
        } catch (IOException e) {
            System.out.println("Details cannot be found");
        }


    }

    @FXML
    void btnRegisterPressed() throws IOException {//https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Register.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) btnRegister.getScene().getWindow();
        stage.setScene(new Scene((root), 800, 500));
        stage.setTitle("Registration Form");

    }

}
