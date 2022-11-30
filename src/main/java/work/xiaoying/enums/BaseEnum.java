package work.xiaoying.enums;

/**
 * 基本枚举
 *
 * @author 小樱
 * @date 2022/12/01
 */
public interface BaseEnum {

    Integer getValue();
    String getTitle();

    static BaseEnum valueOf(Class<? extends BaseEnum> enumClass,Integer value){
        if (value==null){
            return null;
        }
        BaseEnum[] enumConstants = enumClass.getEnumConstants();
        if (enumConstants!=null&&enumConstants.length>0){
            for (BaseEnum enumConstant : enumConstants) {
                if (value.equals(enumConstant.getValue())){
                    return enumConstant;
                }
            }
        }
        return null;
    }
}
