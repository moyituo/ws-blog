package work.xiaoying.interceptor;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import work.xiaoying.mapper.DaoTransaction;

import java.lang.reflect.Method;

/**
 * cglib拦截器
 *
 * @author 小樱
 * @date 2023/02/12
 */
public class CglibInterceptor implements MethodInterceptor {

    private final DaoTransaction transaction;

    public CglibInterceptor(DaoTransaction transaction) {
        this.transaction = transaction;
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        transaction.before();
        Object ret=methodProxy.invokeSuper(o,objects);
        transaction.after();
        return ret;
    }
}
