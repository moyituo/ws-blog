package work.xiaoying.entity.dto.friend;

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
@ApiModel("友链参数")
public class FriendLinkDTO {

    @ApiModelProperty("友链ID")
    @NotNull(message = ErrorMessage.PARAMETER_ERROR,groups = {UpdateSequence.Level1.class})
    @Min(value = 1,message = ErrorMessage.PARAMETER_ERROR,groups = {UpdateSequence.Level2.class})
    private Integer id;

    @ApiModelProperty("昵称")
    @NotBlank(message = ErrorMessage.NAME_IS_BLANK,groups = {InsertSequence.Level1.class,UpdateSequence.Level1.class})
    @Length(max = 20,message = ErrorMessage.NAME_TOO_LONG,groups = {InsertSequence.Level2.class,UpdateSequence.Level2.class})
    private String nickName;

    @ApiModelProperty("博客地址")
    @NotBlank(message = ErrorMessage.ICON_IS_BLANK,groups = {InsertSequence.Level1.class,UpdateSequence.Level1.class})
    @Length(max = 255,message = ErrorMessage.ICON_TOO_LONG,groups = {InsertSequence.Level2.class,UpdateSequence.Level2.class})
    private String blogUrl;

    @ApiModelProperty("个人简介")
    @NotBlank(message = ErrorMessage.DESC_IS_BLANK,groups = {InsertSequence.Level1.class,UpdateSequence.Level1.class})
    @Length(max = 20,message = ErrorMessage.DESC_TOO_LONG,groups = {InsertSequence.Level2.class,UpdateSequence.Level2.class})
    private String desc;

    @ApiModelProperty("用户头像")
    @NotBlank(message = ErrorMessage.ICON_IS_BLANK,groups = {InsertSequence.Level1.class,UpdateSequence.Level1.class})
    @Length(max = 255,message = ErrorMessage.ICON_TOO_LONG,groups = {InsertSequence.Level2.class,UpdateSequence.Level2.class})
    private String avatar;
}
