package work.xiaoying.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import work.xiaoying.entity.User;
import work.xiaoying.entity.vo.UserVO;
import work.xiaoying.mapper.UserMapper;
import work.xiaoying.service.UserService;

import java.util.List;


/**
 * 用户服务impl
 *
 * @author 小樱
 * @date 2022/11/22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public List<UserVO> selectVos() {
        return baseMapper.selectVos(Wrappers.<User>lambdaQuery().eq(User::getEmail,"xxxx@qq.com"));
    }

    @Override
    public Page<User> page() {
        Page<User> page = new Page<>(1,2);
        return baseMapper.page(page,Wrappers.<User>lambdaQuery().eq(User::getEmail,"XXX"));
    }
}
