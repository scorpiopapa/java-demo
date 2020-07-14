package edu.lee.service.impl;

import edu.lee.service.IService;

public class HelloService implements IService {
    @Override
    public String hello(String text) {
        String result = "hello " + text;
        System.out.println(result);
        return result;
    }
}
