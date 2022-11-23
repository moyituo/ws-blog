package work.xiaoying.service;

import com.baomidou.mybatisplus.extension.service.IService;
import work.xiaoying.entity.NavBar;
import work.xiaoying.entity.dto.bar.BarInfoDTO;
import work.xiaoying.entity.vo.bar.NavBarVO;
import work.xiaoying.result.R;

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

    /**
     * 添加
     *
     * @param barInfoDTO 参数对象
     * @return {@link R}<{@link String}>
     */
    R<String> add(BarInfoDTO barInfoDTO);

    /**
     * 编辑
     *
     * @param barInfoDTO 参数对象
     * @return {@link R}<{@link String}>
     */
    R<String> edit(BarInfoDTO barInfoDTO);

    /**
     * 删除
     *
     * @param id id
     * @return {@link R}<{@link String}>
     */
    R<String> delete(Integer id);
}
