package Interpreter;

import java.util.HashMap;
import java.util.Map;

public class ClassObject
{
    public Map<String, Integer> attributes;
    public Map<String, ClassMethod> methods;

    public ClassObject (ClassDef c)
    {
        this.attributes = c.attributes;
        this.methods = c.methods;
    }
}
