import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.util.Scanner;

public class Book {

    private String userID;
    private String date;
    private String eDate;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String geteDate() {
        return eDate;
    }

    public void seteDate(String eDate) {
        this.eDate = eDate;
    }

    private String Isbn;
    private String Title;
    private String Author;
    private ObservableList lstview;

    public ObservableList getCmbList() {
        return cmbList;
    }

    public void setCmbList(ObservableList cmbList) {
        this.cmbList = cmbList;
    }

    private ObservableList cmbList;

    public Book() {

    }

    public ObservableList getLstview() {
        return lstview;
    }

    public void setLstview(ObservableList lstview) {
        this.lstview = lstview;
    }

    public String getIsbn() {
        return Isbn;
    }

    public void setIsbn(String isbn) {
        Isbn = isbn;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void showBook(){
        File f = new File("Lists.txt");
        lstview = FXCollections.observableArrayList();
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()){
                String row = sc.nextLine();
                String[] details;
                details = row.split(",");
                Isbn = details[0];
                Title = details[1];
                Author = details[2];
                lstview.add(Title+ "," +Author);
            }
            setLstview(lstview);
        } catch (IOException e) {
            System.out.println("Details cannot be found");
        }

    }
    public void showList() {
        File f = new File("Lists.txt");
        cmbList = FXCollections.observableArrayList();
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String row = sc.nextLine();
                String[] details;
                details = row.split(",");
                Isbn = details[0];
                Title = details[1];
                Author = details[2];
                cmbList.add(Title);
            }
            setCmbList(cmbList);
        } catch (IOException e) {
            System.out.println("Details cannot be found");
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "Isbn=" + Isbn +
                ", Title='" + Title + '\'' +
                ", Author='" + Author + '\'' +
                '}';
    }
}
