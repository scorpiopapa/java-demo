package edu.lee;

import javax.tools.SimpleJavaFileObject;
import java.io.IOException;
import java.net.URI;

public class InMemoryJavaFileObject extends SimpleJavaFileObject {
    private String contents = null;

    public InMemoryJavaFileObject(String className, String contents) {
        super(URI.create("string:///" + className.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
        this.contents = contents;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return contents;
    }
}
