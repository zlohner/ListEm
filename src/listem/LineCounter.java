package listem;

import java.io.*;
import java.util.*;

/** LineCounter
 *
 * a class to count the number of lines in a file or set of files (in a directory)
 */
public class LineCounter extends Searcher implements ILineCounter {

    private Map<File, Integer> lineCounts;

    public LineCounter() {}

    @Override
    public Map<File, Integer> countLines(File directory, String fileSelectionPattern,
                                         boolean recursive) {
        this.lineCounts = new HashMap<>();
        searchDirectory(directory, fileSelectionPattern, recursive);
        return this.lineCounts;
    }

    @Override
    public void processLine(File file, String line) {
        if (lineCounts.containsKey(file)) {
            lineCounts.put(file, lineCounts.get(file) + 1);
        }
        else { lineCounts.put(file, 1); }
    }

}
