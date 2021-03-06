/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.lee.factory;

import edu.lee.compiler.DefaultClassCompiler;
import edu.lee.compiler.ClassCompiler;
import edu.lee.compiler.GroovyClassCompiler;
import edu.lee.compiler.ItranswarpClassCompiler;

import java.util.Map;

public class ClassFactory {

    private static final Map<Strategy, ClassCompiler> compilers;

    static {
        compilers = Map.of(
                Strategy.Default, new DefaultClassCompiler()
                , Strategy.Itranswarp, new ItranswarpClassCompiler()
                , Strategy.Groovy, new GroovyClassCompiler()
        );
    }

    public static Object createClassObject(Strategy strategy, String className, String source) throws Exception {
        ClassCompiler compiler = compilers.get(strategy);
        if(compiler == null){
            compiler = compilers.get(Strategy.Default);
        }

        Class<?> cls = compiler.compile(className, source);
        return cls.getDeclaredConstructor().newInstance();
    }
}
