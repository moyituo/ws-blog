package work.xiaoying.enums;

import work.xiaoying.entity.vo.OptionVO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 基本枚举
 *
 * @author 小樱
 * @date 2022/12/01
 */
public interface BaseEnum {

    Integer getValue();

    String getTitle();


    static List<OptionVO<Integer>> getOptions(Class<? extends BaseEnum> clazz) {
        BaseEnum[] enumConstants = clazz.getEnumConstants();
        if (enumConstants != null && enumConstants.length > 0) {
            return Arrays.stream(enumConstants)
                    .map(enumC -> OptionVO.<Integer>builder()
                            .label(enumC.getTitle())
                            .value(enumC.getValue())
                            .build())
                    .collect(Collectors.toList());
        }
        return null;
    }

    static BaseEnum valueOf(Class<? extends BaseEnum> enumClass, Integer value) {
        if (value == null) {
            return null;
        }
        BaseEnum[] enumConstants = enumClass.getEnumConstants();
        if (enumConstants != null && enumConstants.length > 0) {
            for (BaseEnum enumConstant : enumConstants) {
                if (value.equals(enumConstant.getValue())) {
                    return enumConstant;
                }
            }
        }
        return null;
    }
}
