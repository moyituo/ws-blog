package work.xiaoying.service.impl;

import lombok.extern.slf4j.Slf4j;
import work.xiaoying.entity.Student;
import work.xiaoying.service.IStudentService;
@Slf4j
public class StudentImpl implements IStudentService {
    @Override
    public void save(Student student) {
        log.info("保存信息");
    }

    @Override
    public Student query(Integer id) {
        log.info("查询");
        return Student.builder().name("zhang").age(12).build();
    }

}
