package work.xiaoying.test;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;
import work.xiaoying.entity.Student;
import work.xiaoying.interceptor.CglibInterceptor;
import work.xiaoying.mapper.DaoTransaction;
import work.xiaoying.service.IStudentService;
import work.xiaoying.service.impl.StudentImpl;

public class TestCglib {

    @Test
    public void testCglib(){
        //生成目标代理类
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"E:\\Desktop\\blog\\ws-blog\\src\\");
        //创建方法拦截器
        CglibInterceptor cglibInterceptor = new CglibInterceptor(new DaoTransaction());
        //使用CGLIB框架生成目标类的子类（代理类）实现增强
        Enhancer enhancer = new Enhancer();
        //设置父类字节码
        enhancer.setSuperclass(StudentImpl.class);
        //设置拦截处理
        enhancer.setCallback(cglibInterceptor);
        IStudentService service=(IStudentService) enhancer.create();
        service.save(new Student());
    }
}
