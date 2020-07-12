package edu.lee.service.impl;

import edu.lee.service.IService;

public class ServiceB implements IService {

    @Override
    public void print() {
        System.out.println("This is in ServiceB");
    }
}
