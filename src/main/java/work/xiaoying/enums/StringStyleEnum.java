package work.xiaoying.enums;

import lombok.Getter;

/**
 * 风格字符串枚举
 *
 * @author 小樱
 * @date 2022/12/01
 */
@Getter
public enum StringStyleEnum {

    UNDER_LINE_CASE(1,"下划线"),
    CAMEL_CASE(2,"小驼峰");

    public final Integer value;
    public final String title;

    StringStyleEnum(Integer value,String title){
        this.value=value;
        this.title=title;
    }
}
