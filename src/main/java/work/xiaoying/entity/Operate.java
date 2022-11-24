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
@TableName("t_operate")
@ApiModel(value = "操作", description = "操作")
public class Operate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty("主键ID")
    private Integer id;

    @TableField("ip")
    @ApiModelProperty("IP")
    private String ip;

    @TableField("operate_type")
    @ApiModelProperty("操作类型")
    private String operateType;

    @TableField("request_param")
    @ApiModelProperty("请求参数")
    private String requestParam;

    @TableField("response")
    @ApiModelProperty("响应结果")
    private String response;

    @TableField("user_id")
    @ApiModelProperty("用户ID")
    private Integer userId;

    @TableField("nick_name")
    @ApiModelProperty("用户昵称")
    private String nickName;

    @TableField("create_time")
    @ApiModelProperty("创建时间")
    private Date createTime;

    @TableField("update_time")
    @ApiModelProperty("更新时间")
    private Date updateTime;
}
