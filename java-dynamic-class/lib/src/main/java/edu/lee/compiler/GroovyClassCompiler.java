package edu.lee.compiler;

import groovy.lang.GroovyClassLoader;

public class GroovyClassCompiler implements ClassCompiler {
    private GroovyClassLoader groovyClassLoader = new GroovyClassLoader();

    @Override
    public Class<?> compile(String className, String source) throws Exception {
        return groovyClassLoader.parseClass(source);
    }
}
