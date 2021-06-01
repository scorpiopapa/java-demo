package edu.lee.compiler;

import edu.lee.utils.MemoryClassLoader;
import edu.lee.utils.MemoryJavaFileManager;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.util.Arrays;
import java.util.Map;

public class DefaultClassCompiler implements ClassCompiler {
    @Override
    public Class<?> compile(String className, String source) throws Exception {
        Map<String, byte[]> results;
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager stdManager = compiler.getStandardFileManager(null, null, null);
        try (MemoryJavaFileManager manager = new MemoryJavaFileManager(stdManager)) {
            JavaFileObject javaFileObject = manager.makeStringSource(className, source);
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, Arrays.asList(javaFileObject));
            if (task.call()) {
                System.out.println("compile success");
                results = manager.getClassBytes();
            }else{
                System.err.println("compile failed");
                return null;
            }
        }

        MemoryClassLoader loader = new MemoryClassLoader(results);
        return loader.findClass(className);
    }
}
