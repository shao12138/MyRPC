package org.nudt;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

//反射工具类
public class ReflectionUtils {
    //根据Class创建对象
    public static <T> T newInstance(Class clazz) {
        try {
            return (T) clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    //获取某个类所有的公共方法
    public static Method[] getPublicMethods(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> pmethods = new ArrayList<>();
        for (Method m : methods) {
            if (Modifier.isPublic(m.getModifiers())) {
                pmethods.add(m);
            }
        }
        return pmethods.toArray(new Method[0]);
    }

    //调用某个对象的指定方法
    public static Object invoke(Object obj, Method method, Object... args) {
        try {
            return method.invoke(obj, args);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
