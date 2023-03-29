package work.xiaoying.handler;

import work.xiaoying.mapper.DaoTransaction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TransactionHandler implements InvocationHandler {

    private final DaoTransaction daoTransaction;
    private final Object  object;

    public TransactionHandler(DaoTransaction daoTransaction, Object object) {
        this.daoTransaction = daoTransaction;
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object ret;
        //判断当前方法是否需要开启事务
        if ("save".equals(method.getName())){
            daoTransaction.before();
            ret=method.invoke(object,args);
            daoTransaction.after();
        }else {
            ret=method.invoke(object,args);
        }
        return ret;
    }
}
