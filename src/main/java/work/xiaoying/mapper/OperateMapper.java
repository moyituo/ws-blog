package work.xiaoying.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import work.xiaoying.entity.Operate;

/**
 * 操作映射器
 *
 * @author 小樱
 * @date 2022/11/25
 */
@Mapper
public interface OperateMapper extends BaseMapper<Operate> {
}
