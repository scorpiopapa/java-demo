package edu.lee;

import edu.lee.service.IService;
import edu.lee.service.impl.HelloService;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

public class DynamicProxyTest {

    @Test void test() throws Exception{
        IService service = new HelloService();
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        IService proxy = (IService)Proxy.newProxyInstance(DynamicProxyTest.class.getClassLoader(),
                new Class[]{IService.class}, new DynamicProxy(service));

        proxy.hello("proxy");
    }
}
