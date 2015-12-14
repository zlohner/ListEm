package listem;

import java.io.*;
import java.util.*;
import java.util.regex.*;

/** Searcher
 *
 * superclass for Grep and LineCounter that implements file and directory searching capabilities
 */
public abstract class Searcher {

    private Pattern p;
    private Matcher m;

    public Searcher() {}

    public void searchDirectory(File directory, String fileSelectionPattern, boolean recursive) {
        if (!directory.exists()) {
            System.out.println("Directory does not exist");
        }
        else {
            this.p = Pattern.compile(fileSelectionPattern);

            File[] children = directory.listFiles();
            for (File file : children) {
                if (file.isDirectory() && recursive) {
                    searchDirectory(file, fileSelectionPattern, recursive);
                } else if (file.isFile()) {
                    m = p.matcher(file.getName());
                    if (m.matches()) {
                        searchFile(file);
                    }
                }
            }
        }
    }

    public void searchFile(File file) {
        Scanner inFile = null;
        try {
            inFile = new Scanner(new BufferedInputStream(new FileInputStream(file.getAbsolutePath())));

            while(inFile.hasNextLine()) {
                String line = inFile.nextLine();
                processLine(file, line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File in directory not found: " + file.getName());
        } finally {
            if (inFile != null) {
                inFile.close();
            }
        }
    }

    public abstract void processLine(File file, String line);

}
