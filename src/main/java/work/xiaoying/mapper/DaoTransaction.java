package work.xiaoying.mapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DaoTransaction {
    public void before(){
      log.info("开启事务操作");
    }

    public void after(){
        log.info("关闭事务");
    }
}
