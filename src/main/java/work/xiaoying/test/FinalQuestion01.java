package work.xiaoying.test;

public class FinalQuestion01 {
    //可以在声明时初始化 private final int id;
    //-----------------静态变量------------------//
    //初始化方式一，在定义类变量时直接赋值
    public final static int id_1 = 3;
    //初始化方式二，在方法块中赋值
    private static final int id_2 ;
    static {
        id_2=10;
    }
    //这样直接编译错误,必须在静态代码块中，因为静态的方法和成员变量在对象创建时就实现了

}
