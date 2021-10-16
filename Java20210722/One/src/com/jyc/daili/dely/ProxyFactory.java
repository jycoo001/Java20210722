package com.jyc.daili.dely;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
    public static Object getObjectInstance(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("ProxyFactory.invoke before");
                        Object objectValue =  method.invoke(target, args);
                        System.out.println("ProxyFactory.invoke after");
                        return objectValue;
                    }
                }
        );
    }
}
