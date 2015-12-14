package listem;

import java.io.*;
import java.util.*;
import java.util.regex.*;

/** Grep
 *
 * a class to search a file or set of files (in a directory) for a specific pattern (RegEx)
 */
public class Grep extends Searcher implements IGrep {
    
    private Pattern p;
    private Matcher m;
    private Map<File, List<String>> matches;

    public Grep() {}

    @Override
    public Map<File, List<String>> grep(File directory, String fileSelectionPattern,
                                        String substringSelectionPattern, boolean recursive) {
        this.matches = new HashMap<>();
        p = Pattern.compile(substringSelectionPattern);
        searchDirectory(directory, fileSelectionPattern, recursive);
        return this.matches;
    }

    @Override
    public void processLine(File file, String line) {
        m = p.matcher(line);
        if (m.find()) {
            if (matches.containsKey(file)) {
                matches.get(file).add(line);
            } else {
                List l = new ArrayList<>();
                l.add(line);
                matches.put(file, l);
            }
        }
    }

}
