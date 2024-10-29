package Interpreter;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class GarbageCollector
{
    Stack<Long> stack = new Stack();
    TreeMap<Long, ClassObject> objects;
    int currentColor;
    TreeMap<Long, ClassObject> usedObjects;

    public GarbageCollector(Stack<Long> s)
    {
        this.usedObjects = new TreeMap<Long, ClassObject>();
        currentColor = 0;
        stack = s;
    }

    public void dfs (Long ref)
    {
//        System.out.print(ref + " ");
//        System.out.println(objects.get(ref));
        ClassObject obj = objects.get(ref);

        obj.color = currentColor;

        for (Map.Entry<String, Long> entry : obj.attributes.entrySet())
        {
            if (entry.getValue() > 2147483647L)
            {
                if (objects.get(entry.getValue()).color != currentColor)
                {
                    dfs(entry.getValue());
                }
            }
        }
    }

    public void collect (Map<Long, ClassObject> used)
    {
        usedObjects.putAll(used);
//        System.out.println("COLLECTOR");
        for (Long element : stack)
        {
            if (element > 2147483647L)
            {
                this.dfs(element);
            }
        }

        for (Map.Entry<Long, ClassObject> entry : usedObjects.entrySet())
        {
                this.dfs(entry.getKey());
        }

        ArrayList<Long> toBeDeleted = new ArrayList<Long>();
        for (Map.Entry<Long, ClassObject> entry : objects.entrySet())
        {
            if (entry.getValue().color != currentColor && entry.getKey() > 2147483647L)
            {
                toBeDeleted.add(entry.getKey());
            }
        }

        for (Long element : toBeDeleted)
        {
//            System.out.println("APAGANDO " + element);
            objects.remove(element);
        }

        currentColor = (currentColor + 1)%2;
    }
}
