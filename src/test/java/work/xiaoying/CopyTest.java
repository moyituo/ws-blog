package work.xiaoying;

import org.junit.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CopyTest {


    @Test
    public static void main(String[] args) throws IOException {
        String originalFileName = "a.txt";
        String newFileName = getNewFileName(originalFileName);
        System.out.println(newFileName);
    }

    private static String getNewFileName(String originalFileName) {
        String regex = "^(.*)_副本(\\(\\d+\\))?\\.?(\\w+)?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(originalFileName);

        if (matcher.matches()) {
            String baseName = matcher.group(1);
            String suffix = matcher.group(3);
            String indexString = matcher.group(2);

            int index = 1;
            if (indexString != null) {
                index = Integer.parseInt(indexString.substring(1, indexString.length() - 1)) + 1;
            }

            if (suffix == null) {
                return String.format("%s_副本(%d)", baseName, index);
            } else {
                return String.format("%s_副本(%d).%s", baseName, index, suffix);
            }
        } else {
            int dotIndex = originalFileName.lastIndexOf(".");
            String baseName, suffix;
            if (dotIndex == -1) {
                baseName = originalFileName;
                suffix = "";
            } else {
                baseName = originalFileName.substring(0, dotIndex);
                suffix = originalFileName.substring(dotIndex + 1);
            }

            return String.format("%s_副本.%s", baseName, suffix);
        }
    }

}
