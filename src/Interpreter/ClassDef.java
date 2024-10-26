package Interpreter;

import java.lang.reflect.Method;
import java.util.Map;

public class ClassDef
{
    public String name;
    public Map<String, Integer> attributes;
    public Map<String, ClassMethod> methods;

    public ClassDef(String name)
    {
        this.name = name;
    }
}
