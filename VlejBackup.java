import java.util.*;
import java.io.*;
public class Main {
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        TreeMap<Integer, String> tm= new TreeMap<Integer, String>();
        Enumeration linenum;
        String linepr;
        int ln = 0;
        for (String filename: args) {
            try {
                // This will reference one line at a time
                String line = null;
                // FileReader reads text files in the default encoding.
                FileReader fileReader = new FileReader(filename);
                // Always wrap FileReader in BufferedReader.
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while((line = bufferedReader.readLine()) != null) {
                    filecontents.put(ln, line);
                    ln++;
                }
             // Always close files.
                bufferedReader.close();
            }
            catch(FileNotFoundException ex) {
                System.out.println("Unable to open file '" + filename + "'");
            }
            catch(IOException ex) {
                System.out.println("Error reading file '" + filename + "'");
            }
        }
    linenum = filecontents.keys();
    while(linenum.hasMoreElements()) {
        int lns;
        lns = (int) linenum.nextElement();
        System.out.println(filecontents.get(lns));
    }
    }
}