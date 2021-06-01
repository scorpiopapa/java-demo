package edu.lee.compiler;

import com.itranswarp.compiler.JavaStringCompiler;

import java.util.Map;

public class ItranswarpClassCompiler implements ClassCompiler{
    @Override
    public Class<?> compile(String className, String source) throws Exception {
        // 编译器
        JavaStringCompiler compiler = new JavaStringCompiler();

        String[] elements = className.split("\\.");
        String fileName = elements[elements.length - 1];

        // 编译
        Map<String, byte[]> results = compiler.compile(fileName + ".java", source);

        // 加载内存中byte到Class<?>对象
        return compiler.loadClass(className, results);
    }
}
