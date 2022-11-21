package work.xiaoying.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import work.xiaoying.entity.User;
import work.xiaoying.mapper.UserMapper;
import work.xiaoying.service.UserService;


/**
 * 用户服务impl
 *
 * @author 小樱
 * @date 2022/11/22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
