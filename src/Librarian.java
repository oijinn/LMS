import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;

public class Librarian {

    private String userID;
    private String password;
    private String name;

    public Librarian(){//Constructor
        this.userID = "undefined";
        this.password = "undefined";
        this.name = "undefined";
    }

    public Librarian(String userID, String password, String name) {//Constructor
        this.userID = userID;
        this.password = password;
        this.name = name;
    }


    public String getUserID() {//Getter
        return userID;
    }

    public void setUserID(String userID) {//Setter
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void Register(String u, String p, String n){

        File f = new File("LoginDetails.txt");
        try {
            FileWriter fw = new FileWriter(f,true);
            PrintWriter pw = new PrintWriter(fw);
                setUserID(u);
                setPassword(p);
                setName(n);
                pw.println(u + "," + p + "," + n);
                pw.close();

        } catch (IOException e) {
            System.out.println("Details cannot be insert");
        }
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "userID='" + userID + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
