package Interpreter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BoolInterpreter
{
    public static void main(String[] args)
    {
        Map<String, ClassDef> classes = ClassBuilder.buildClass(args[0]);

        Map<String, Integer> variables = new HashMap<>();

        TreeMap<Integer, ClassObject> objects = new TreeMap<>();
        objects.put(0, new ClassObject());

        Interpreter interpreter = new Interpreter(classes, variables, objects);

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));

            String line;
            while (!(line = (reader.readLine().split(" ")[0])).equals("main()"))
            {}

            String[] tokens = reader.readLine().split(" |, ");

            if (tokens[0].equals("vars"))
            {
                for (int i = 1; i < tokens.length; i++)
                    variables.put(tokens[i], 0);

                line = reader.readLine();
            }

            tokens = reader.readLine().split(" ");



            reader.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
