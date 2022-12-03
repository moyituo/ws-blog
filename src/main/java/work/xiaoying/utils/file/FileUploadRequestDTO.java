package work.xiaoying.utils.file;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileUploadRequestDTO {
    private String path;
    private MultipartFile file;
    private Integer chunk;
    private Integer chunks;
    private String md5;
    private Integer chunkSize;
    private UploadModeEnum modeEnum;
}
