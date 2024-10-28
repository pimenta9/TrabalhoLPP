package Interpreter;

import java.util.HashMap;
import java.util.Map;

public class ClassObject
{
    public Map<String, Integer> attributes;
    public Map<String, ClassMethod> methods;

    public ClassObject ()
    {

    }

    public ClassObject (int n)
    {
        this.methods = new HashMap<>();
        this.methods.put("print", new ClassMethod());
    }

    public ClassObject (ClassDef c)
    {
        this.attributes = new HashMap<>();
        this.attributes.putAll(c.attributes);
        attributes.put("_prototype", 0);
        this.methods = new HashMap<>();
        this.methods.putAll(c.methods);
    }
}
