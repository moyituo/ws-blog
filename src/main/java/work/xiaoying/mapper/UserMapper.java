package work.xiaoying.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import work.xiaoying.entity.User;
import work.xiaoying.entity.vo.UserVO;

import java.util.List;

/**
 * 用户映射器
 *
 * @author 小樱
 * @date 2022/11/22
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT nick_name,email,avatar from t_user ${ew.customSqlSegment}")
    List<UserVO> selectVos(@Param(Constants.WRAPPER) Wrapper<User> wq);

    @Select("SELECT * FROM t_user ${ew.customSqlSegment}")
    Page<User> page(Page<User> page,@Param(Constants.WRAPPER) Wrapper<User> wq);

    User getUser(Integer age);
}
