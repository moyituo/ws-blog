package work.xiaoying.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 注释跑龙套
 *
 * @author 小樱
 * @date 2022/11/30
 */
public class AnnotationUtil {

    public static boolean isAnnotationPresent(AnnotatedElement element, Class<? extends Annotation> clazz){
        if (element.isAnnotationPresent(clazz)){
            return true;
        }
        Annotation[] annotations = element.getAnnotations();
        for (Annotation annotation : annotations) {
            Class<? extends Annotation> annotationType = annotation.annotationType();
            if (annotationFilter(annotationType)){
                continue;
            }
            if(isAnnotationPresent(annotationType,clazz)){
                return true;
            }
        }
        return false;
    }

    public static boolean annotationFilter(Class annotationType){
        return Retention.class.equals(annotationType)|| Target.class.equals(annotationType)|| Documented.class.equals(annotationType);
    }

    public static <T extends Annotation> T getAnnotation(AnnotatedElement element,Class<T> clazz){
        return getAnnotation(element,element,clazz);
    }
    public static <T extends Annotation> T getAnnotation(AnnotatedElement newElement,AnnotatedElement oldElement,Class<T> clazz){
        if (newElement.isAnnotationPresent(clazz)){
            return doGetAnnotation(newElement,oldElement,clazz);
        }
        Annotation[] annotations = newElement.getAnnotations();
        for (Annotation annotation : annotations) {
            Class<? extends Annotation> annotationType = annotation.annotationType();
            if (annotationFilter(annotationType)){
                continue;
            }
            if(isAnnotationPresent(annotationType,clazz)){
                return getAnnotation(annotationType,oldElement,clazz);
            }
        }
        return null;
    }

    private static <T extends Annotation> T doGetAnnotation(AnnotatedElement newElement,AnnotatedElement oldElement,Class<T> clazz) {
        if (newElement==oldElement){
            return newElement.getAnnotation(clazz);
        }
        return (T) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Class subclass = ((Class) newElement).asSubclass(Annotation.class);
                Annotation annotation = oldElement.getAnnotation(subclass);
                String methodName = method.getName();
                Method subclassMethod = subclass.getMethod(methodName);
                return subclassMethod.invoke(annotation,args);
            }
        });
    }

    public static <T extends Annotation> T findAnnotation(Class<?> clazz, Class<T> uploadModeClass) {
        return clazz.getAnnotation(uploadModeClass);
    }
}
