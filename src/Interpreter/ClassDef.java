package Interpreter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ClassDef
{
    public String name;
    public Map<String, Long> attributes;
    public Map<String, ClassMethod> methods;

    public ClassDef(String name)
    {
        this.name = name;
        this.attributes = new HashMap<String, Long>();
        this.methods = new HashMap<String, ClassMethod>();
    }
}