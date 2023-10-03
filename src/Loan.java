import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Loan {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnBorrow;

    @FXML
    private Button btnExtend;

    @FXML
    private Button btnReturn;

    @FXML
    private ListView<?> lstBookList;

    @FXML
    private TextField txtOverdueFine;

    private Librarian librarianSession = new Librarian();

    @FXML
    void btnBackPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.setScene(new Scene((root), 600, 400));
        stage.setTitle("Homepage");
        Homepage homepage = loader.getController();
        homepage.retrieveSession(librarianSession);
        homepage.showUserName();
    }

    @FXML
    void btnBorrowPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Borrow.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) btnBorrow.getScene().getWindow();
        stage.setScene(new Scene((root), 600, 400));
        stage.setTitle("Borrow books");
        Borrow br = loader.getController();
        br.retrieveSession(librarianSession);
        br.setDate();
        br.setEnd();
    }

    public void retrieveSession(Librarian tempLibrarian){
        librarianSession = tempLibrarian;
        retrieveTotalFine();
    }


    @FXML
    void btnExtendPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Extend.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) btnExtend.getScene().getWindow();
        stage.setScene(new Scene((root), 600, 400));
        stage.setTitle("Extend duration of books");
        Extend ex = loader.getController();
        ex.retrieveSession(librarianSession);
        ex.setDate();
        ex.setEnd();
    }

    @FXML
    void btnReturnPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Return.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) btnReturn.getScene().getWindow();
        stage.setScene(new Scene((root), 600, 400));
        stage.setTitle("Return books");
        Return returns = loader.getController();
        returns.retrieveSession(librarianSession);
    }

    private void retrieveTotalFine(){
        long totalFine = 0;
        File f = new File("Records.txt");
        String nDate;
        String librarianID;
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String row = sc.nextLine();
                String[] details;
                details = row.split(",");
                librarianID = details[0];
               nDate= details[3];
                LocalDate datenow = LocalDate.now();
                LocalDate expiryDate = LocalDate.parse(nDate);
                if (librarianID.equalsIgnoreCase(librarianSession.getUserID())){
                    if (datenow.isAfter(expiryDate)){
                        long yearBetween = expiryDate.until(datenow).getYears();
                        long monthBetween = expiryDate.until(datenow).getMonths();
                        long dayBetween = expiryDate.until(datenow).getDays();
                        totalFine += ((yearBetween*365) + (monthBetween*30) + dayBetween);
                        txtOverdueFine.setText(Long.toString(totalFine));
                    } else{
                        txtOverdueFine.setText(Long.toString(totalFine));
                    }
                } else {
                    txtOverdueFine.setText(Long.toString(totalFine));
                }
            }sc.close();
        } catch (IOException e) {
            System.out.println("Details cannot be found");
        }

    }

    public void showlist(ObservableList ol) {
        lstBookList.setItems(ol);
    }

}
