package Interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassMethod
{
    public String name;
    public String signature;
    public Map<String, Integer> localVariables;
    public ArrayList<String> parameters;
    public ArrayList<String> body;

    public ClassMethod()
    {
        this.localVariables = new HashMap();
        this.body = new ArrayList();
        this.parameters = new ArrayList();
    }

    public ClassMethod(String signature)
    {
        this.signature = signature;
        this.name = signature.split("\\(")[0];
        this.localVariables = new HashMap<String, Integer>();
        this.body = new ArrayList<>();
        this.parameters = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\(([a-zA-z]+)|, ([a-zA-z]+)");
        Matcher matcher = pattern.matcher(signature);
        while (matcher.find())
        {
            if (matcher.group(1) != null)
                parameters.add(matcher.group(1));

            if (matcher.group(2) != null)
                parameters.add(matcher.group(2));
        }
    }
}
