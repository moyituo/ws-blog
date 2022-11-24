package work.xiaoying.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 切面
 *
 * @author 小樱
 * @date 2022/11/25
 */
@Aspect
@Component
public class OperateAspect {

    @Pointcut("@annotation(work.xiaoying.annotation.OperateLog)")
    public void pointCut(){
        //pointCut()
    }



}
