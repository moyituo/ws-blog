package work.xiaoying.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import work.xiaoying.entity.User;

/**
 * 用户映射器
 *
 * @author 小樱
 * @date 2022/11/22
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
