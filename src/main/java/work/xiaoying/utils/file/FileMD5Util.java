package work.xiaoying.utils.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件md5跑龙套
 *
 * @author 小樱
 * @date 2022/12/02
 */
@Slf4j
public class FileMD5Util {
    public static String getFileMD5(MultipartFile file) {
        try {
            return DigestUtils.md5Hex(file.getBytes());
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return "";
    }
}
