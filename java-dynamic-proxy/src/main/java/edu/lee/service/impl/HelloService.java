package edu.lee.service.impl;

import edu.lee.service.IService;

public class HelloService implements IService {
    @Override
    public void hello(String text) {
        System.out.println("hello " + text);
    }
}
