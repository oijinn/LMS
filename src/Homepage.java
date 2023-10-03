import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class Homepage{

    @FXML
    private Button btnLoan;

    @FXML
    private Button btnLogout;

    @FXML
    private TextField txtName;

    private Librarian librarianSession = new Librarian();


    @FXML
    void btnLoanPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Loan.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) btnLoan.getScene().getWindow();
        stage.setScene(new Scene((root), 800, 500));
        stage.setTitle("Loan books");
        Loan loan = loader.getController();
        loan.retrieveSession(librarianSession);
        Book b = new Book();
        b.showBook();
        loan.showlist(b.getLstview());
    }

    public void retrieveSession(Librarian tempLibrarian){
        librarianSession = tempLibrarian;
    }

    @FXML
    void btnLogoutPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        stage.setScene(new Scene((root), 800, 500));
        stage.setTitle("Library System");
        System.out.println("Logout successfully!");
    }

    public void showUserName() {
        txtName.setText(librarianSession.getName());
    }

}
