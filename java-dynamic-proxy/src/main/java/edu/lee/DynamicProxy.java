package edu.lee;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {

    private Object target;

    public DynamicProxy(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before call method: " + method.getName());

        Object result = method.invoke(target, args);

        System.out.println("after call method: " + method.getName());

        return result;
    }
}
