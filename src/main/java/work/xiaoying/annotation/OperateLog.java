package work.xiaoying.annotation;

import java.lang.annotation.*;

/**
 * 操作日志
 *
 * @author 小樱
 * @date 2022/11/25
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateLog {

    String operateType() default "";
}
