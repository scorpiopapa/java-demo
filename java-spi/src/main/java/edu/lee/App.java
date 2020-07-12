/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.lee;

import edu.lee.service.IService;

import java.util.ServiceLoader;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        ServiceLoader<IService> services = ServiceLoader.load(IService.class);
        for(IService service : services){
            service.print();
        }
    }
}
