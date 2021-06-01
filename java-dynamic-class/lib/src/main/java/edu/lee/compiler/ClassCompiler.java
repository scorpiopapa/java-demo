package edu.lee.compiler;

public interface ClassCompiler {
    Class<?> compile(String className, String source) throws Exception ;
}
