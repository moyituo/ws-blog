package work.xiaoying.proxy;

import work.xiaoying.entity.Student;
import work.xiaoying.mapper.DaoTransaction;
import work.xiaoying.service.IStudentService;
import work.xiaoying.service.impl.StudentImpl;

public class StudentProxy implements IStudentService {
    //目标类对象
    private StudentImpl studentImpl;
    //需要做的增强对象
    private DaoTransaction transaction;

    public StudentProxy(StudentImpl studentImpl, DaoTransaction transaction) {
        this.studentImpl = studentImpl;
        this.transaction = transaction;
    }

    @Override
    public void save(Student student) {
        transaction.before();
        studentImpl.save(student);
        transaction.after();
    }

    @Override
    public Student query(Integer id) {
        return studentImpl.query(id);
    }
}
