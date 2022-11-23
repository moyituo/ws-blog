package work.xiaoying.entity.vo.bar;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("导航栏视图")
public class NavBarVO {

    @ApiModelProperty("主键ID")
    private Integer id;

    @ApiModelProperty("导航栏名称")
    private String barName;

    @ApiModelProperty("导航栏图标")
    private String barIcon;

    @ApiModelProperty("导航栏标签")
    private String barLabel;

}
