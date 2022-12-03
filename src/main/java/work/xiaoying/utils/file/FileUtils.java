package work.xiaoying.utils.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j
@Component
public class FileUtils {
    public static byte[] readFileToByteArray(File file) {
        if (file == null) {
            return new byte[0];
        }
        FileInputStream fileInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fileInputStream.read(b)) != -1) {
                byteArrayOutputStream.write(b, 0 , n);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new byte[0];
    }


    public static String withoutHeadAndTailDiagonal(String path) {
        return path;
    }


    public static String getExtension(String toFileNewName) {
        return toFileNewName.substring(toFileNewName.lastIndexOf(".")+1);
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


    public String getPath(FileUploadRequestDTO param) {
        return param.getPath();
    }

    public static String getFileMD5(MultipartFile file) {
        try {
            return DigestUtils.md5Hex(file.getBytes());
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return "";
    }
}
