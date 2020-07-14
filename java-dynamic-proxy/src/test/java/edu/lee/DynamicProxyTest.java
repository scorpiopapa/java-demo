package edu.lee;

import edu.lee.service.IAddService;
import edu.lee.service.IDummyService;
import edu.lee.service.IService;
import edu.lee.service.impl.HelloService;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DynamicProxyTest {

    @Test void test1() throws Exception{
        IService service = new HelloService();
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        IService proxy = (IService)Proxy.newProxyInstance(DynamicProxyTest.class.getClassLoader(),
                new Class[]{IService.class}, new DynamicProxy(service));

        assertEquals("hello proxy", proxy.hello("proxy"));
    }

    @Test void test2() throws Exception{
        // load interface from both concrete class and string
        Object proxy = Proxy.newProxyInstance(DynamicProxyTest.class.getClassLoader(),
                new Class[]{IService.class, IAddService.class, Class.forName("edu.lee.service.IDummyService")},
                new EmptyProxy());

        // return string type
        IService service = (IService)proxy;
        String text = service.hello("proxy");
        System.out.println("hello method returned -> " + text);
        System.out.println(service instanceof IAddService);

        // return int type
        IAddService addService = (IAddService)proxy;
        int sum = addService.add(10, 20);
        System.out.println("add method returned -> " + sum);

        // return void type
        IDummyService dummyService = (IDummyService)proxy;
        dummyService.dummy();
    }
}
