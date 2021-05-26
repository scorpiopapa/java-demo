package edu.lee;

import javassist.*;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;

public class TransformerFactory {

    private static Map<String, ClassFileTransformer> INSTANCES = new HashMap<>();
    private static String CLASS_NAME = "edu/lee/CalculationService";

    static{
        INSTANCES.put("default", createDefaultTransformer());
        INSTANCES.put("replace", createReplaceMethodTransformer());
    }

    public static ClassFileTransformer createTransformer(String strategy){
        System.out.println("create transformer by strategy [" + strategy + "]");
        ClassFileTransformer transformer = INSTANCES.get(strategy);
        System.out.println("get transformer -> " + transformer);

        if(transformer == null){
            transformer = createDefaultTransformer();
        }

        return transformer;
    }

    /**
     * print out the class information only
     * @return
     */
    private static ClassFileTransformer createDefaultTransformer() {
//        System.out.println("Create default transformer");
        return new ClassFileTransformer(){
            @Override
            public byte[] transform(Module module, ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                System.out.println("premain load Class     :" + className);
                return ClassFileTransformer.super.transform(module, loader, className, classBeingRedefined, protectionDomain, classfileBuffer);
            }

            @Override
            public String toString(){
                return "DefaultTransformer";
            }
        };
    }

    /**
     * replace add method of CalculationService
     * @return
     */
    private static ClassFileTransformer createReplaceMethodTransformer() {
//        System.out.println("Create replace method transformer");
        return new ClassFileTransformer(){
            @Override
            public byte[] transform(Module module, ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
//                System.out.println("Loaded -> " + className);

                if(!CLASS_NAME.equals(className)){
                    return ClassFileTransformer.super.transform(module, loader, className, classBeingRedefined, protectionDomain, classfileBuffer);
                }

                try {
                    System.out.println("process add method replacement");

                    // 加载 javassist 类池
                    ClassPool cp = ClassPool.getDefault();

                    // WAR 包需要把 loader 补充进去，不然下面 javassist 解析字节码会报告找不到 import 的类
//                cp.appendClassPath(new LoaderClassPath(loader));

                    // 把字节码载入进去，生成 CtClass
                    CtClass cc = cp.makeClass(new ByteArrayInputStream(classfileBuffer));

                    // 定位到方法 add
                    CtMethod cm = cc.getDeclaredMethod("add");

                    // 修改方法代码体
                    cm.setBody("{System.out.println(\"" + CLASS_NAME + "#add method was changed by agent, will return 0\"); return 0;}");

                    // 返回重新编译的字节码
                    return cc.toBytecode();
                }catch (Exception e){
                    return ClassFileTransformer.super.transform(module, loader, className, classBeingRedefined, protectionDomain, classfileBuffer);
                }
            }

            @Override
            public String toString(){
                return "ReplaceMethodTransformer";
            }
        };
    }
}
