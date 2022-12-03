package work.xiaoying.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.xiaoying.result.R;
import work.xiaoying.service.impl.FileService;
import work.xiaoying.utils.file.FileUploadDTO;
import work.xiaoying.utils.file.FileUploadRequestDTO;

/**
 * 用户控制器
 *
 * @author 小樱
 * @date 2022/11/22
 */
@Api(tags = "文件模块")
@RestController
@RequestMapping("/blog/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }


    @PostMapping("upload")
    public R<FileUploadDTO> check(FileUploadRequestDTO param){
        FileUploadDTO fileUploadDTO = fileService.fileUpload(param);
        return R.success(fileUploadDTO);
    }

}
