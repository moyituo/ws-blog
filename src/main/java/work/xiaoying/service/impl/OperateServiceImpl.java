package work.xiaoying.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import work.xiaoying.entity.Operate;
import work.xiaoying.mapper.OperateMapper;
import work.xiaoying.service.OperateService;

/**
 * 运营服务impl
 *
 * @author 小樱
 * @date 2022/11/25
 */
@Service
public class OperateServiceImpl extends ServiceImpl<OperateMapper, Operate> implements OperateService {
}
