import java.io.File;

public class Recursion {
    /**
     * Counts the total number of files in the given directory recursively.
     *
     * @param f The current file or directory
     * @return The total number of files in f
     */
    public static int filecount(File f) {
        int result = 0;

        if (f.isFile()) {
            result++;
        } else {
            File[] fileList = f.listFiles();
            if (fileList != null) {
                for (int i = 0; i < fileList.length; i++) {
                    result += filecount(fileList[i]);
                }
            }
        }
        return result;
    }
}