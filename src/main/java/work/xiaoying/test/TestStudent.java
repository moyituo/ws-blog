package work.xiaoying.test;

import org.junit.Test;
import sun.misc.ProxyGenerator;
import work.xiaoying.entity.Student;
import work.xiaoying.handler.TransactionHandler;
import work.xiaoying.mapper.DaoTransaction;
import work.xiaoying.proxy.StudentProxy;
import work.xiaoying.service.IStudentService;
import work.xiaoying.service.impl.StudentImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

public class TestStudent {

    @Test
    public void testSave(){
        DaoTransaction transaction = new DaoTransaction();
        StudentImpl studentImpl = new StudentImpl();
        StudentProxy studentProxy = new StudentProxy(studentImpl,transaction);
        studentProxy.save(new Student());
        studentProxy.query(1);
    }
    
    @Test
    public void testJDKProxy(){
        //增强类
        DaoTransaction transaction = new DaoTransaction();
        //目标类
        IStudentService service = new StudentImpl();
        //方法处理拦截器
        TransactionHandler handler = new TransactionHandler(transaction,service);
        //获取代理实例对象
        IStudentService o = (IStudentService)Proxy.newProxyInstance(StudentImpl.class.getClassLoader(),
                StudentImpl.class.getInterfaces(),
                handler);
        o.save(new Student());
        saveProxyClass("E:\\Desktop\\blog\\ws-blog\\src");
    }

    private void saveProxyClass(String path){
        byte[] $proxies = ProxyGenerator.generateProxyClass("$Proxy", StudentImpl.class.getInterfaces());
        try(FileOutputStream outputStream = new FileOutputStream(new File(path + "$Proxy.class"));) {
            outputStream.write($proxies);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
