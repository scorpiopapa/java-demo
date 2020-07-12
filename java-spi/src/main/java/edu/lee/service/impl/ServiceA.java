package edu.lee.service.impl;

import edu.lee.service.IService;

public class ServiceA implements IService {

    @Override
    public void print() {
        System.out.println("This is in ServiceA");
    }
}
