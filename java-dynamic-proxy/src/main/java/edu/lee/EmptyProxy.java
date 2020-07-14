package edu.lee;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class EmptyProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before call method: " + method.getName());

        String result = "";
        if(args != null) {
            for (int i = 0; i < args.length; i++) {
                result += "arg[" + i + "] = " + args[i] + ", ";
            }
        }

        System.out.println("result -> [" + result + "]");

        System.out.println("after call method: " + method.getName());

        Class<?> returnType = method.getReturnType();
        System.out.println("return type is " + returnType);

        if(returnType == String.class){
            return result;
        }else if(returnType == int.class){
            return Integer.valueOf(args[0].toString()) + Integer.valueOf(args[1].toString());
        }else{
            return null;
        }

    }
}
