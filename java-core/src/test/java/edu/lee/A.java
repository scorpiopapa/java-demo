package edu.lee;

import java.io.IOException;

public class A {
    protected Object returnSomeValue() throws IOException {
        return "somevalueA";
    }

    public static void main(String args[]) throws Exception{
        B b = new B();
        System.out.println(b.returnSomeValue());
    }
}

class B extends A {
    public String returnSomeValue() throws IOException {
        return "somevalueB";
    }
}