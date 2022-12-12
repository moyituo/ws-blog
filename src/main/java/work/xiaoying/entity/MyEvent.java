package work.xiaoying.entity;

import cn.hutool.core.util.IdUtil;
import lombok.Data;

@Data
public class MyEvent {
    private String id;
    private String name;

    public MyEvent(){
        this.id= IdUtil.objectId();
        this.name="自定义事件";
    }
}
