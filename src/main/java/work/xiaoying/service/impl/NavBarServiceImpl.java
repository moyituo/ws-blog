package work.xiaoying.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import work.xiaoying.entity.NavBar;
import work.xiaoying.entity.dto.bar.BarInfoDTO;
import work.xiaoying.entity.vo.bar.NavBarVO;
import work.xiaoying.mapper.NavBarMapper;
import work.xiaoying.result.R;
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

    @Override
    public R<String> add(BarInfoDTO barInfoDTO) {
        NavBar navBar = BeanUtil.copyProperties(barInfoDTO, NavBar.class);
        save(navBar);
        return R.success();
    }

    @Override
    public R<String> edit(BarInfoDTO barInfoDTO) {
        updateById(BeanUtil.copyProperties(barInfoDTO,NavBar.class));
        return R.success();
    }

    @Override
    public R<String> delete(Integer id) {
        removeById(id);
        return R.success();
    }
}
