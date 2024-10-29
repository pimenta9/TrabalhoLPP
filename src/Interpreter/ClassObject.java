package Interpreter;

import java.util.HashMap;
import java.util.Map;

public class ClassObject
{
    public Map<String, Long> attributes;
    public Map<String, ClassMethod> methods;
    int color;

    public ClassObject ()
    {

    }

    public ClassObject (int n)
    {
        this.methods = new HashMap<>();
        this.methods.put("print", new ClassMethod());
        this.color = 2;
    }

    public ClassObject (ClassDef c)
    {
        this.attributes = new HashMap<>();
        this.attributes.putAll(c.attributes);
        attributes.put("_prototype", 0L);
        this.methods = new HashMap<>();
        this.methods.putAll(c.methods);
        this.color = 2;
    }
}
