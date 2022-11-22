package work.xiaoying.service;

import com.baomidou.mybatisplus.extension.service.IService;
import work.xiaoying.entity.NavBar;
import work.xiaoying.entity.vo.NavBarVO;

import java.util.List;

/**
 * 导航栏服务
 *
 * @author 小樱
 * @date 2022/11/22
 */
public interface NavBarService extends IService<NavBar> {

    /**
     * 获得栏列表
     *
     * @return {@link List}<{@link NavBarVO}>
     */
    List<NavBarVO> getBarList();
}
