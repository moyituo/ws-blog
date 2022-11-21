package work.xiaoying.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

/**
 * 用户
 *
 * @author 小樱
 * @date 2022/11/22
 */
@Data
@Builder
@TableName("t_user")
@ApiModel(value = "User对象", description = "")
public class User {

    @Tolerate
    public User() {
        //
    }

    @TableId(type = IdType.AUTO)
    @ApiModelProperty("用户ID")
    private Integer id;

    @TableField("nick_name")
    @ApiModelProperty("用户昵称")
    private String nickName;

    @TableField("email")
    @ApiModelProperty("用户邮箱")
    private String email;

    @TableField("avatar")
    @ApiModelProperty("用户头像")
    private String avatar;

    @TableField("desc")
    @ApiModelProperty("用户简介")
    private String desc;

    @TableField("web_site")
    @ApiModelProperty("个人网站地址")
    private String webSite;

    @TableField("disabled")
    @ApiModelProperty("是否被禁用")
    private Integer disabled;

    @TableField("create_time")
    @ApiModelProperty("创建时间")
    private Date createTime;

    @TableField("update_time")
    @ApiModelProperty("更新时间")
    private Date updateTime;
}
