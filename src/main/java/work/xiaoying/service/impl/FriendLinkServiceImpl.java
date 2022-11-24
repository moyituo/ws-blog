package work.xiaoying.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import work.xiaoying.entity.FriendLink;
import work.xiaoying.entity.dto.frient.FriendLinkDTO;
import work.xiaoying.entity.vo.friend.FriendLinkVO;
import work.xiaoying.mapper.FriendLinkMapper;
import work.xiaoying.result.R;
import work.xiaoying.service.FriendLinkService;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 友链impl
 *
 * @author 小樱
 * @date 2022/11/24
 */
@Service
public class FriendLinkServiceImpl extends ServiceImpl<FriendLinkMapper, FriendLink> implements FriendLinkService {

    @Override
    public R<List<FriendLinkVO>> linkList() {
        List<FriendLinkVO> linkVoList = list().stream().map(f -> BeanUtil.copyProperties(f, FriendLinkVO.class)).collect(Collectors.toList());
        return R.success(linkVoList);
    }

    @Override
    public R<String> delete(Integer id) {
        removeById(id);
        return R.success();
    }

    @Override
    public R<String> edit(FriendLinkDTO friendLinkDTO) {
        updateById(BeanUtil.copyProperties(friendLinkDTO,FriendLink.class));
        return R.success();
    }

    @Override
    public R<String> add(FriendLinkDTO friendLinkDTO) {
        save(BeanUtil.copyProperties(friendLinkDTO,FriendLink.class));
        return R.success();
    }
}
