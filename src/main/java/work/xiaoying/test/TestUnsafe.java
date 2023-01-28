package work.xiaoying.test;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class TestUnsafe {

    private static final Unsafe unsafe;

    static {
        try {
            Class<Unsafe> unsafeClass = Unsafe.class;
            Field theUnsafe = unsafeClass.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe= (Unsafe) theUnsafe.get(null);
        }catch (Exception e){
            throw new Error(e);
        }
    }
    public static void main(String[] args) {
        System.out.println(unsafe);
    }
}
