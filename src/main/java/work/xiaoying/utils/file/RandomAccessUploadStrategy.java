package work.xiaoying.utils.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Objects;

@UploadMode(mode = UploadModeEnum.RANDOM_ACCESS)
@Slf4j
public class RandomAccessUploadStrategy extends SliceUploadTemplate {

    @Autowired
    private FileUtils filePathUtil;

    @Value("${upload.chunkSize}")
    private long defaultChunkSize;

    @Override
    public boolean upload(FileUploadRequestDTO param) {
        RandomAccessFile accessTmpFile = null;
        try {
            String uploadDirPath = filePathUtil.getPath(param);
            File tmpFile = super.createTmpFile(param);
            accessTmpFile = new RandomAccessFile(tmpFile, "rw");
            //这个必须与前端设定的值一致
            long chunkSize = Objects.isNull(param.getChunkSize()) ? defaultChunkSize * 1024 * 1024
                    : param.getChunkSize();
            long offset = chunkSize * param.getChunk();
            //定位到该分片的偏移量
            accessTmpFile.seek(offset);
            //写入该分片数据
            accessTmpFile.write(param.getFile().getBytes());
            return super.checkAndSetUploadProgress(param, uploadDirPath);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            FileUtils.close(accessTmpFile.getChannel());
        }
        return false;
    }

}