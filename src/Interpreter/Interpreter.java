package Interpreter;

import java.util.*;

public class Interpreter
{
    Stack<Integer> stack = new Stack();
    Map<String, ClassDef> classes;
    Map<String, Integer> variables;
    TreeMap<Integer, ClassObject> objects;

    public Interpreter (Map<String, ClassDef> c, Map<String, Integer> v, TreeMap<Integer, ClassObject> o)
    {
        classes = c;
        variables = v;
        objects = o;
    }

    public void run (Map<String, Integer> vars, ArrayList<String> body)
    {
        String[] tokens;

        int value, v1, v2, objReference;

        for (int i = 0; i < body.size(); i++)
        {
            tokens = body.get(i).split(" ");

            switch (tokens[0])
            {
                case "const":
                    stack.push(Integer.parseInt(tokens[1]));
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

                    int reference = objects.lastKey() + 1;

                    objects.put(reference, o);
                    stack.push(reference);
                    break;

                case "get":
                    objReference = stack.pop();
                    stack.push(objects.get(objReference).attributes.get(tokens[1]));
                    break;

                default:
                    break;
            }
        }
    }
}
