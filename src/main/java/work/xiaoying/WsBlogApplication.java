package work.xiaoying;

import cn.dev33.satoken.SaManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@EnableAsync
@SpringBootApplication
public class WsBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(WsBlogApplication.class, args);
        log.info("启动成功：Sa-Token配置如下：" + SaManager.getConfig());
    }

}
