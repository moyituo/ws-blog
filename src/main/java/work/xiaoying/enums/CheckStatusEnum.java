package work.xiaoying.enums;

import lombok.Getter;

/**
 * 检查状态枚举
 *
 * @author 小樱
 * @date 2022/12/01
 */
@Getter
public enum CheckStatusEnum implements BaseEnum{

    PENDING(0,"待定"),
    PASS(1,"通过"),
    FAIL(2,"失败");

    public final Integer value;
    public final String title;

    CheckStatusEnum(Integer value,String title){
        this.value=value;
        this.title=title;
    }

}
