package work.xiaoying.annotation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainTest {
    public static void main(String[] args) {
        Class<Test> testClass = Test.class;
        if (isComponents(testClass)){
            MyComponent annotation = AnnotationUtil.getAnnotation(testClass, MyComponent.class);
            log.info("value===>{}",annotation.value());
        }else {
            log.info("未被标记");
        }
    }

    public static boolean isComponents(Class clazz){
        return AnnotationUtil.isAnnotationPresent(clazz,MyComponent.class);
    }
}
