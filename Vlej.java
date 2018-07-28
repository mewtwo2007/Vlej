import java.util.*;
import java.io.*;
public class Vlej {
    static Console scan = System.console();
    public static void main(String[] args) {
        Input input = new Input();
        TreeMap<Integer, String> filecontents= new TreeMap<Integer, String>();
        String command;
        String lineinr;
        String linepr;
        String viewlf;
        int ln = 0;
        int lineinvi;
        Set keys;
        Set viewkeys;
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
        System.out.println("If 'null' appears, the line does not exist");
        System.out.println("Enter the number of the line you would like to read.");
        System.out.println("For more information about how to edit enter 'h'");
        while (true) {
        command = input.scanner();
        if (command.compareToIgnoreCase("h") == 0) {
            System.out.println("Vlej Help");
            System.out.println("To view a line, type 'v'");
            System.out.println("To replace a line, type'r'");
            System.out.println("To add a line, type 'a'");
            System.out.println("To delete a line, type 'd'");
            System.out.println("To save and exit editing, type 'e'");
        } else if (command.compareToIgnoreCase("r") == 0) {
            System.out.println("What line to replace?");
            lineinr = input.scanner();
            int lineinri = Integer.parseInt(lineinr.trim());	
            linepr = filecontents.get(lineinri);
            System.out.println("The current line");
            System.out.println(linepr);
            System.out.println("Enter the text to enter");
            lineinr = input.console();
            filecontents.put(lineinri, lineinr);
        } else if (command.compareToIgnoreCase("e") == 0) {
            for (String filename: args) {
                try {
                    // Assume default encoding.
                    FileWriter fileWriter = new FileWriter(filename);
                    // Always wrap FileWriter in BufferedWriter.
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    // Note that write() does not automatically
                    // append a newline character.
                    keys = filecontents.keySet();
                    for (Iterator i = keys.iterator(); i.hasNext();) {
                    Integer key = (Integer) i.next();
                    String value = (String) filecontents.get(key);
                    bufferedWriter.write(value);
                    bufferedWriter.newLine();
                    }
                    // Always close files.
                    bufferedWriter.close();
                }
                catch(IOException ex) {
                    System.out.println(
                        "Error writing to file '"
                        + filename + "'");
                }
            }
            break;
        } else if (command.compareToIgnoreCase("v") == 0) {
            System.out.println("View line or file?");
            viewlf = input.scanner();
            if (viewlf.compareToIgnoreCase("line") == 0) {
            System.out.println("What line to view?");
            String lineinv = input.scanner();
            lineinvi = Integer.parseInt(lineinv.trim());
            linepr = filecontents.get(lineinvi);
            System.out.println(linepr);
            } else if (viewlf.compareToIgnoreCase("file") == 0) {
                viewkeys = filecontents.keySet();
                for (Iterator i = viewkeys.iterator(); i.hasNext();) {
                    Integer viewkey = (Integer) i.next();
                    String prfile = (String) filecontents.get(viewkey);
                    System.out.println(prfile);
                }
            } else if (viewlf != null) {
                System.out.println("That is not a choice.");
            }
        } else if (command.compareToIgnoreCase("a") == 0) {
            System.out.println("What line do you want to add in front of?");
            String lineina = input.scanner();
            int lineinai = Integer.parseInt(lineina.trim());
            lineinai++;
            System.out.println("Type the text to add.");
            String lineinst = input.console();
            if (lineinai <= filecontents.lastKey()) {
                keys = filecontents.keySet();
                Iterator i = keys.iterator();
                Integer current = (Integer) i.next();
                String templine;
                while (current != null) {
                    if (i.hasNext()) {
                        if (current >= lineinai) {
                            templine = filecontents.get(current);
                            filecontents.put(current, lineinst);
                            lineinst = templine;
                        }
                        current = (Integer) i.next();
                    } else {
                        templine = filecontents.get(current);
                        filecontents.put(current, lineinst);
                        lineinst = templine;
                        filecontents.put(current + 1, lineinst);
                        current = null;
                    }
                }
            }
        } else if (command.compareToIgnoreCase("d") == 0) {
            System.out.println("What line do you want to delete?");
            String lineind = input.scanner();
            int lineindi = Integer.parseInt(lineind.trim());
            if (lineindi <= filecontents.lastKey()) {
                keys = filecontents.keySet();
                Iterator i = keys.iterator();
                Integer current = (Integer) i.next();
                while (current != null) {
                    if (i.hasNext()) {
                        Integer next = (Integer) i.next();
                        if (current >= lineindi) {
                            String delline = filecontents.get(next);
                            filecontents.put(current, delline); 
                        }
                        current = next;
                    } else {
                        filecontents.remove(current);
                        current = null;
                    }
                }
            }
        } else if (command != null) {
            System.out.println("Sorry, the command you entered does not exist.");
        }
        }
    }
}