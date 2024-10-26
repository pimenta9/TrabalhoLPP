package Interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClassMethod
{
    public String name;
    public String signature;
    public Map<String, Integer> localVariables;
    public ArrayList<String> body;

    public ClassMethod(String signature)
    {
        this.signature = signature;
        this.name = signature.split("\\(")[0];
        this.localVariables = new HashMap<String, Integer>();
        this.body = new ArrayList<>();
    }
}
