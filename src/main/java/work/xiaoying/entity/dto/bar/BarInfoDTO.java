package work.xiaoying.entity.dto.bar;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import work.xiaoying.constant.ErrorMessage;
import work.xiaoying.validator.InsertSequence;
import work.xiaoying.validator.UpdateSequence;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("新增导航栏参数对象")
public class BarInfoDTO {

    @ApiModelProperty("导航栏ID")
    @NotNull(message = ErrorMessage.PARAMETER_ERROR,groups = {UpdateSequence.Level1.class})
    @Min(value = 1,message = ErrorMessage.PARAMETER_ERROR,groups = {UpdateSequence.Level2.class})
    private Integer id;

    @ApiModelProperty("导航栏名称")
    @NotBlank(message = ErrorMessage.NAME_IS_BLANK,groups = {InsertSequence.Level1.class, UpdateSequence.Level1.class})
    @Length(max = 10,message = ErrorMessage.NAME_TOO_LONG,groups = {InsertSequence.Level2.class, UpdateSequence.Level2.class})
    private String barName;

    @ApiModelProperty("导航栏图标")
    @NotBlank(message = ErrorMessage.ICON_IS_BLANK,groups = {InsertSequence.Level1.class,UpdateSequence.Level1.class})
    @Length(max = 200,message = ErrorMessage.ICON_TOO_LONG,groups = {InsertSequence.Level2.class, UpdateSequence.Level2.class})
    private String barIcon;

    @ApiModelProperty("导航栏标签")
    @NotBlank(message = ErrorMessage.NAME_IS_BLANK,groups = {InsertSequence.Level1.class,UpdateSequence.Level1.class})
    @Length(max = 20,message = ErrorMessage.NAME_TOO_LONG,groups = {InsertSequence.Level2.class,UpdateSequence.Level2.class})
    private String barLabel;

}
