import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import java.io.*;
import java.util.Scanner;

public class Register {
    private String userID;
    private String name;
    private String password;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtuserID;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button registerBtn;

    @FXML
    private Button backBtn;

    @FXML
    void backBtnPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.setScene(new Scene((root), 800, 500));
        stage.setTitle("Library System");

    }

    @FXML
    void registerBtnPressed() {
        File f = new File("LoginDetails.txt");
        Librarian librarian = new Librarian(txtuserID.getText(),txtName.getText(),txtPassword.getText());
        librarian.setUserID(txtuserID.getText());
        librarian.setName(txtName.getText());
        librarian.setPassword(txtPassword.getText());
        String ID;
        String pass;
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String row = sc.nextLine();
                String[] details;
                details = row.split(",");
                ID = details[0];
                pass = details[2];
                if (txtuserID.getText().equals("") ||txtName.getText().equals("")||txtPassword.getText().equals("")){
                    System.out.println("Please fill in the empty fields!");
                    break;
                }
                if (txtuserID.getText().equals(ID)) {
                    System.out.println("Record exists");
                    break;
                }
                else{
                    librarian.Register(txtuserID.getText(),txtName.getText(),txtPassword.getText());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) registerBtn.getScene().getWindow();
                    stage.setScene(new Scene((root), 800, 500));
                    stage.setTitle("Library System");
                    break;
                }

            }sc.close();
        } catch (IOException e) {
            System.out.println("Details cannot be found");
        }
    }

}
