package work.xiaoying.enums;

import java.util.function.Function;

/**
 * 敏感战略
 *
 * @author 小樱
 * @date 2022/12/05
 */
public enum SensitiveStrategy {

    USERNAME(s->s.replaceAll("(\\S)\\S(\\S)","$1*$2")),
    ID_CARD(s->s.replaceAll("(\\d{4})\\d{10}(\\w{4})","$1****$2")),
    PHONE(s->s.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2")),
    ADDRESS(s->s.replaceAll("(\\S{3})\\S{2}(\\S*)\\S{2}","$1****$2****"));

    private final Function<String,String> desensitizer;

    SensitiveStrategy(Function<String,String> desensitizer){
        this.desensitizer=desensitizer;
    }

    public Function<String,String> desensitizer(){
        return desensitizer;
    }
}
