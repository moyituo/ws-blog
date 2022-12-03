package work.xiaoying.entity.vo.friend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 朋友联系签证官
 * 友链
 *
 * @author 小樱
 * @date 2022/11/24
 */
@Data
@ApiModel("友链视图")
public class FriendLinkVO {

    @ApiModelProperty("主键ID")
    private Integer id;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("博客地址")
    private String blogUrl;

    @ApiModelProperty("个人简介")
    private String description;

    @ApiModelProperty("头像")
    private String avatar;

}
