package work.xiaoying.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@TableName("t_friend_link")
@ApiModel(value = "友链", description = "")
public class FriendLink implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty("主键ID")
    private Integer id;

    @TableField("nick_name")
    @ApiModelProperty("昵称")
    private String nickName;

    @TableField("blog_url")
    @ApiModelProperty("博客链接")
    private String blogUrl;

    @TableField("description")
    @ApiModelProperty("个人简介")
    private String description;

    @TableField("avatar")
    @ApiModelProperty("头像")
    private String avatar;

    @TableField("create_time")
    @ApiModelProperty("创建时间")
    private Date createTime;

    @TableField("update_time")
    @ApiModelProperty("更新时间")
    private Date updateTime;
}
