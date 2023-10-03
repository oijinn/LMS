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
import java.util.Scanner;
import javafx.scene.control.TextField;

public class Return {
    private static Scanner x;
    @FXML
    private Button btnBack;

    @FXML
    private ComboBox<?> cmbList;

    @FXML
    private TextField txtuserID;

    @FXML
    private Button btnConfirm;

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
    void btnConfirmPressed() throws IOException{
        File oldFile = new File("Records.txt");//https://www.youtube.com/watch?v=TpyRKom0X_s
        File newFile = new File("tempFile.txt");//https://stackoverflow.com/questions/822150/modify-a-txt-file-in-java
        String userID = txtuserID.getText();
        String BookName;
        String Date;
        String nDate;

        FileWriter fw = new FileWriter("tempFile.txt", true);
        PrintWriter pw = new PrintWriter(fw);
        x = new Scanner(new File("Records.txt"));
        while (x.hasNext()) {
            String row = x.nextLine();
            String[] details;
            details = row.split(",");
            userID = details[0];
            BookName = details[1];
            Date = details[2];
            nDate = details[3];
            if (!userID.equals(txtuserID.getText()) || !BookName.equals(cmbList.getValue())) {
                pw.println(userID + "," + BookName + "," + Date + "," +nDate);

            }
        }
        x.close();
        pw.close();
        oldFile.delete();
        File trash = new File("Records.txt");
        newFile.renameTo(trash);
    }



    @FXML
    void cmbListPressed() {
        Book bk= new Book();
        bk.showList();
        cmbList.setItems(bk.getCmbList());
    }
}
