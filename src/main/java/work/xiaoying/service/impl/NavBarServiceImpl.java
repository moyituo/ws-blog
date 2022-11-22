package work.xiaoying.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import work.xiaoying.entity.NavBar;
import work.xiaoying.entity.vo.NavBarVO;
import work.xiaoying.mapper.NavBarMapper;
import work.xiaoying.service.NavBarService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 导航栏服务impl
 *
 * @author 小樱
 * @date 2022/11/22
 */
@Service
public class NavBarServiceImpl extends ServiceImpl<NavBarMapper, NavBar> implements NavBarService {
    @Override
    public List<NavBarVO> getBarList() {
        return list().stream().map(bar-> BeanUtil.copyProperties(bar, NavBarVO.class)).collect(Collectors.toList());
    }
}
