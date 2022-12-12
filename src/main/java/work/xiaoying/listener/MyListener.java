package work.xiaoying.listener;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import work.xiaoying.entity.MyEvent;

@Component
@Slf4j
public class MyListener {

    @SneakyThrows
    @Async
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @EventListener(MyEvent.class)
    public void MyEventHandler(MyEvent event){
        log.info("监听到了事件");
        log.info("监听器所在线程：  " + Thread.currentThread().getName());
        Thread.sleep(10000);
        log.info(event.toString());
        log.info("处理完毕");
    }
}
