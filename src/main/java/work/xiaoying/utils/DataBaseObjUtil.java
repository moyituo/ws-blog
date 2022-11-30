package work.xiaoying.utils;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;
import work.xiaoying.enums.StringStyleEnum;

import java.lang.reflect.Field;

/**
 * 数据库obj跑龙套
 *
 * @author 小樱
 * @date 2022/12/01
 */
public class DataBaseObjUtil {

    public static String getFieldStr(Class obj,String prefix){
        StringBuilder stringBuilder = new StringBuilder();
        Field[] declaredFields = obj.getDeclaredFields();
        int length = declaredFields.length;
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (CharSequenceUtil.isNotBlank(prefix)){
                stringBuilder.append(prefix);
            }
            stringBuilder.append(field.getName());
            if (--length!=0){
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }

    public static String getFieldStr(Class obj, String prefix, StringStyleEnum stringStyleEnum){
        String fieldStr = getFieldStr(obj, prefix);
        if (ObjectUtil.equal(stringStyleEnum,StringStyleEnum.CAMEL_CASE)){
            fieldStr= CharSequenceUtil.toCamelCase(fieldStr);
        }
        if (ObjectUtil.equal(stringStyleEnum,StringStyleEnum.UNDER_LINE_CASE)){
            fieldStr= CharSequenceUtil.toUnderlineCase(fieldStr);
        }
        return fieldStr;
    }
}
