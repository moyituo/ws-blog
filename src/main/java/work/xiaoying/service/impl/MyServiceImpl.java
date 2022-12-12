package work.xiaoying.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import work.xiaoying.entity.MyEvent;
import work.xiaoying.service.MyService;

@Service
@Slf4j
public class MyServiceImpl implements MyService {

    @Autowired
    private ApplicationEventPublisher publisher;
    @Override
    public String publish() {
        log.info("开始处理业务");
        log.info("发布器所在线程：" + Thread.currentThread().getName());
        publisher.publishEvent(new MyEvent());
        log.info("主线业务处理完毕");
        return "OK";
    }
}
