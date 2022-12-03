package work.xiaoying.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.xiaoying.annotation.AnnotationUtil;
import work.xiaoying.utils.file.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 文件服务
 *
 * @author 小樱
 * @date 2022/12/03
 */
@Service
public class FileService {

    private Map<UploadModeEnum, SliceUploadStrategy> sliceUploadStrategyMap;

    @Autowired
    public void setSliceUploadStrategyMap(List<SliceUploadStrategy> strategyList){
        sliceUploadStrategyMap=strategyList.stream().collect(Collectors.toMap(strategy->
            AnnotationUtil.findAnnotation(strategy.getClass(), UploadMode.class).mode(),
            v->v,(v1,v2)->v1
        ));
    }

    public FileUploadDTO fileUpload(FileUploadRequestDTO fileUploadRequestDTO){
        SliceUploadStrategy sliceUploadStrategy = sliceUploadStrategyMap.get(fileUploadRequestDTO.getModeEnum());
        return sliceUploadStrategy.sliceUpload(fileUploadRequestDTO);
    }
}
