package Interpreter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ClassBuilder {

    public static Map<String, ClassDef> buildClass(String file) {

        Map<String, ClassDef> classes = new HashMap<String, ClassDef>();

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String[] tokens = reader.readLine().split(" ");

            while (tokens[0].equals("class"))
            {
                ClassDef c = new ClassDef(tokens[1]);

                tokens = reader.readLine().split(" |, ");

                if (tokens[0].equals("vars"))
                {
                    for (int i = 1; i < tokens.length; i++)
                    {
                        c.attributes.put(tokens[i], 0L);
                    }

                    tokens = reader.readLine().split(" ");
                }

                while (!tokens[0].equals("end-class"))
                {
                    String methodSignature = tokens[1];
                    ClassMethod m = new ClassMethod(methodSignature);

                    tokens = reader.readLine().split(" |, ");

                    if (tokens[0].equals("vars"))
                    {
                        for (int i = 1; i < tokens.length; i++)
                        {
                            m.localVariables.put(tokens[i], 0L);
                        }
                        tokens = reader.readLine().split(" ");
                    }

                    String line = reader.readLine();
                    while (!line.equals("end-method"))
                    {
                        m.body.add(line);
                        line = reader.readLine();
                    }
                    line = reader.readLine();

                    c.methods.put(methodSignature.split("\\(")[0], m);

                    tokens = line.split(" ");
                }

                classes.put(c.name, c);

                tokens = reader.readLine().split(" ");
            }

            reader.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        return classes;
    }
}
