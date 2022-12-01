package work.xiaoying.utils.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadDTO {
    private Map<Integer,String> chunkMd5Info;
    private boolean uploadComplete;
    private  long mtime;
    private String path;
    private Long size;
    private String  fileExt;
    private String fileId;
    public boolean isUploadComplete() {
        return true;
    }
}
