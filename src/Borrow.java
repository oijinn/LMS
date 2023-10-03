import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javafx.scene.control.TextField;

public class Borrow {

    public void setDate(){
        LocalDate today = LocalDate.now();
        txtdateStart.setText(String.valueOf(today));
    }

    public void setEnd(){
        LocalDate endDate = LocalDate.now().plusDays(14);
        txtdateEnd.setText(String.valueOf(endDate));
    }
    @FXML
    private Button btnBack;

    @FXML
    private Button btnConfirm;

    @FXML
    private ComboBox<?> cmbList;

    @FXML
    private TextField txtdateStart;

    @FXML
    private TextField txtdateEnd;

    @FXML
    private TextField txtuserID;

    private Librarian librarianSession = new Librarian();

    @FXML
    void btnBackPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Loan.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) btnBack.getScene().getWindow();
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
        showLibrarianID(librarianSession.getUserID());
    }

    private void showLibrarianID(String ID){
        txtuserID.setText(ID);
    }

    @FXML
    void btnConfirmPressed() {
        File f = new File("Records.txt");
        String userID = txtuserID.getText();
        String BookName = (String) cmbList.getValue();
        String Date = txtdateStart.getText();
        String eDate = txtdateEnd.getText();

        try {
            FileWriter fw = new FileWriter(f,true);
            PrintWriter pw = new PrintWriter(fw);

                pw.println(userID + "," + BookName + "," + Date + "," + eDate);
                pw.close();
                System.out.println("Book have successfully borrowed");
            } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    @FXML
    void cmbListPressed() {
    Book bk= new Book();
        bk.showList();
        cmbList.setItems(bk.getCmbList());
    }

}
