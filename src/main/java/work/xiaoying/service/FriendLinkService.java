package work.xiaoying.service;

import com.baomidou.mybatisplus.extension.service.IService;
import work.xiaoying.entity.FriendLink;
import work.xiaoying.entity.dto.frient.FriendLinkDTO;
import work.xiaoying.entity.vo.friend.FriendLinkVO;
import work.xiaoying.result.R;

import java.util.List;


/**
 * 友链
 *
 * @author 小樱
 * @date 2022/11/24
 */
public interface FriendLinkService extends IService<FriendLink> {

    /**
     * 链接列表
     *
     * @return {@link R}<{@link List}<{@link FriendLinkVO}>>
     */
    R<List<FriendLinkVO>> linkList();

    /**
     * 删除
     *
     * @param id id
     * @return {@link R}<{@link String}>
     */
    R<String> delete(Integer id);

    /**
     * 编辑
     *
     * @param friendLinkDTO dto朋友联系
     * @return {@link R}<{@link String}>
     */
    R<String> edit(FriendLinkDTO friendLinkDTO);

    /**
     * 添加
     *
     * @param friendLinkDTO dto朋友联系
     * @return {@link R}<{@link String}>
     */
    R<String> add(FriendLinkDTO friendLinkDTO);
}
