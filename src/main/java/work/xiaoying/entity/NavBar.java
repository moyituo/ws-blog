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


/**
 * 导航条
 *
 * @author 小樱
 * @date 2022/11/22
 */
@Data
@Builder
@TableName("t_navbar")
@ApiModel(value = "导航栏", description = "导航栏")
public class NavBar implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty("主键ID")
    private Integer id;

    @TableField("bar_name")
    @ApiModelProperty("导航栏名称")
    private String barName;

    @TableField("bar_icon")
    @ApiModelProperty("导航栏图标")
    private String barIcon;

    @TableField("bar_label")
    @ApiModelProperty("导航栏标签")
    private String barLabel;

    @TableField("create_time")
    @ApiModelProperty("创建时间")
    private Date createTime;

    @TableField("update_time")
    @ApiModelProperty("更新时间")
    private Date updateTime;

}
