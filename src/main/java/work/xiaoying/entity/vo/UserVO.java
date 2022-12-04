package work.xiaoying.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 小樱
 * @date 2022/12/05
 */
@Data
public class UserVO {

    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty("用户邮箱")
    private String email;

    @ApiModelProperty("用户头像")
    private String avatar;
}
