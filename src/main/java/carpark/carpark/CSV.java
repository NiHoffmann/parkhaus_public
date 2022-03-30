/**Auszug aus einem größeren Projekt , diese Klasse funktioniert
 los gelöst vom Rest der Projektes also habe ich sie als Demo gewählt **/

package carpark.carpark;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Class for management of CSV */
public class CSV {

    /**Logger for Errors*/
    private static final Logger LOGGER = Logger.getLogger(CSV.class.getName());

    /** String containing the path of CSV file */
    static final String BUILDPATH = System.getProperty("user.dir") + "\\";

    /**Private constructor of CSV class so you cant initialize an CSV object*/
    private CSV(){
    }

    /**
     * @param name Name of CSV file
     * @return Content of CSV file in form of an ArrayList
     */
    public static List<String> read(String name) {

        File file = new File(BUILDPATH + name + ".csv");
        List<String> lines = new ArrayList<>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();
        }
        catch (IOException e){
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    LOGGER.log(Level.FINEST, e.toString(), e);
                }
            }
        }

        return lines;
    }
    /**
     * @param fileName Name of CSV file
     * @param content Content to be written to CSV file
     */
    public static void save(String fileName, String content) {
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(fileName+".csv"));
            writer.write(content);
            writer.newLine();
            writer.close();
        }
        catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    LOGGER.log(Level.FINEST, e.toString(), e);
                }
            }
        }

    }
    /**
     * @param filename Name of CSV file
     * @param content Content to be written to CSV file
     */
    public static void save(String filename, String[] content) {
        StringBuilder newContent = new StringBuilder();

        for (String s: content) {
            newContent.append(s).append("\n");
        }

        CSV.save(filename, newContent.toString());
    }
}
