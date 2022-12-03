package work.xiaoying.utils.file;

/**
 * 切片上传策略
 *
 * @author 小樱
 * @date 2022/12/03
 */
public interface SliceUploadStrategy {

    /**
     * 文件切片上传
     *
     * @param param 参数
     * @return {@link FileUploadDTO}
     */
    public FileUploadDTO sliceUpload(FileUploadRequestDTO param);

}
