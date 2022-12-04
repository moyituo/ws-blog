package work.xiaoying.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import work.xiaoying.entity.User;
import work.xiaoying.entity.vo.UserVO;

import java.util.List;


/**
 * 用户服务
 *
 * @author 小樱
 * @date 2022/11/22
 */
public interface UserService extends IService<User> {

    List<UserVO> selectVos();

    Page<User> page();
}
