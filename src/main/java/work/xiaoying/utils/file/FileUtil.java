package work.xiaoying.utils.file;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileUtil {
    public static String withoutHeadAndTailDiagonal(String path) {
        return "";
    }


    public static String getExtension(String toFileNewName) {
        return "";
    }

    public static void freedMappedByteBuffer(MappedByteBuffer mappedByteBuffer) {
    }

    public static void close(FileChannel fileChannel) {
        try {
            fileChannel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
