package Interpreter;

import java.util.*;

public class Interpreter
{
    Stack<Long> stack = new Stack();
    Map<String, ClassDef> classes;
    TreeMap<Long, ClassObject> objects;
    int collectorRoutine = 0;
    GarbageCollector collector = new GarbageCollector(stack);

    public Interpreter (Map<String, ClassDef> c, TreeMap<Long, ClassObject> o)
    {
        classes = c;
        objects = o;
        collector.stack = stack;
        collector.objects = objects;
    }

    public Long run (Map<String, Long> vars, ArrayList<String> body)
    {
        vars.put("io", -1L);

        String[] tokens;

        Long value, v1, v2, objReference;

        ClassObject obj;

        for (int i = 0; i < body.size(); i++)
        {
            Map<Long, ClassObject> usedObjects = new TreeMap<Long, ClassObject>();
            collectorRoutine = collectorRoutine%5;
            if (collectorRoutine == 4)
            {
                for (Map.Entry<String, Long> entry : vars.entrySet())
                {
                    if (entry.getValue() > 2147483647L)
                    {
                        usedObjects.put(entry.getValue(), objects.get(entry.getValue()));
                    }
                }

                collector.collect(usedObjects);

            }
            collectorRoutine++;

            System.out.println(i + "---------------------------------------");
            for (Long element : stack)
                System.out.println(element);

            System.out.println(body.get(i));

            tokens = body.get(i).split(" ");

            switch (tokens[0])
            {
                case "const":
                    stack.push(Long.parseLong(tokens[1]));
                    break;

                case "load":
                    value = vars.get(tokens[1]);
                    stack.push(value);
                    break;

                case "store":
                    vars.put(tokens[1], stack.pop());
                    break;

                case "add":
                    v2 = stack.pop();
                    v1 = stack.pop();
                    stack.push(v1 + v2);
                    break;

                case "sub":
                    v2 = stack.pop();
                    v1 = stack.pop();
                    stack.push(v1 - v2);
                    break;

                case "mul":
                    v2 = stack.pop();
                    v1 = stack.pop();
                    stack.push(v1 * v2);
                    break;

                case "div":
                    v2 = stack.pop();
                    v1 = stack.pop();
                    stack.push(v1 / v2);
                    break;

                case "new":
                    ClassObject o = new ClassObject(classes.get(tokens[1]));

                    Long reference = objects.lastKey() + 1;

                    for (ClassMethod m : o.methods.values())
                    {
                        m.localVariables.put("self", reference);
                    }

                    objects.put(reference, o);
                    stack.push(reference);
                    break;

                case "get":
                    objReference = stack.pop();

                    obj = objects.get(objReference);

                    while (!obj.attributes.containsKey(tokens[1]))
                        obj = objects.get(obj.attributes.get("_prototype"));

                    stack.push(obj.attributes.get(tokens[1]));

                    break;

                case "set":
                    objReference = stack.pop();

                    value = stack.pop();

                    obj = objects.get(objReference);

                    while (!obj.attributes.containsKey(tokens[1]))
                        obj = objects.get(obj.attributes.get("_prototype"));

                    obj.attributes.put(tokens[1], value);

                    break;

                case "call":
                    if (tokens[1].equals("print"))
                    {
                        stack.pop();
                        System.out.println(stack.pop());
                        return 0L;
                    }

                    // System.out.println(tokens[1]);
                    objReference = stack.pop();
                    // System.out.println(objReference);
                    obj = objects.get(objReference);
                    while (!obj.methods.containsKey(tokens[1]))
                    {
                        // System.out.println("...");
                        // System.out.println(obj.attributes.get("_prototype"));
                        obj = objects.get(obj.attributes.get("_prototype"));
                    }

                    Map<String, Long> methodVars = new HashMap<>();
                    methodVars.putAll(obj.methods.get(tokens[1]).localVariables);
                    ArrayList<String> methodBody = new ArrayList<>();
                    methodBody.addAll(obj.methods.get(tokens[1]).body);

                    ArrayList<String> params = obj.methods.get(tokens[1]).parameters;
                    // System.out.println(obj.methods.get(tokens[1]).parameters.size());
                    for (int j = params.size() - 1; j >= 0; j--)
                    {
                        // System.out.println(j + " " + params.get(j));
                        String varName = params.get(j);
                        methodVars.put(varName, stack.pop());
                    }

                    stack.push(this.run(methodVars, methodBody));

                    break;

                case "gt":
                    v2 = stack.pop();
                    v1 = stack.pop();
                    if(v1>v2){
                        stack.push(1L);
                    }
                    else{
                        stack.push(0L);
                    }

                    break;

                case "ge":
                    v2 = stack.pop();
                    v1 = stack.pop();
                    if(v1>=v2){
                        stack.push(1L);
                    }
                    else{
                        stack.push(0L);
                    }

                    break;

                case "lt":
                    v2 = stack.pop();
                    v1 = stack.pop();
                    if(v1<v2){
                        stack.push(1L);
                    }
                    else{
                        stack.push(0L);
                    }

                    break;

                case "le":
                    v2 = stack.pop();
                    v1 = stack.pop();
                    if(v1<=v2){
                        stack.push(1L);
                    }
                    else{
                        stack.push(0L);
                    }
                    break;

                case "eq":
                    v2 = stack.pop();
                    v1 = stack.pop();
                    if(Objects.equals(v1, v2)){
                        stack.push(1L);
                    }
                    else{
                        stack.push(0L);
                    }
                    break;

                case "ne":
                    v2 = stack.pop();
                    v1 = stack.pop();
                    if(!Objects.equals(v1, v2)){
                        stack.push(1L);
                    }
                    else{
                        stack.push(0L);
                    }
                    break;

                case "pop":
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                    break;

                case "if":
                    v1 = stack.pop();
                    if(v1!=1L){
                        i = i + Integer.parseInt(tokens[1]);
                    }

                    break;

                case "else":
                    v1 = stack.pop();
                    if(v1==1L){
                        i = i + Integer.parseInt(tokens[1]);
                    }
                    break;

                case "ret":
                    value = stack.pop();
                    return value;

                default:
                    break;
            }
        }

        return 0L;
    }
}
