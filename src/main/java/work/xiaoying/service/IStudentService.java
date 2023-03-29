package work.xiaoying.service;

import work.xiaoying.entity.Student;

public interface IStudentService {

    void save(Student student);

    Student query(Integer id);
}
