import java.util.*;
import java.io.*;
public class Input {
    static Scanner scan = new Scanner(System.in);
    static Console cons = System.console();
    public String scanner() {
        String scanin = scan.next();
        return scanin;
    }
    public String console() {
        String consin = cons.readLine();
        return consin;
    }
}