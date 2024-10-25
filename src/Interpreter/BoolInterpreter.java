package Interpreter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BoolInterpreter {

    public static void main(String[] args) {

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));

            String[] tokens = reader.readLine().split(" ");

            if (tokens[0].equals("class"))
            {
                ClassDef c = new ClassDef(tokens[1]);

                tokens = reader.readLine().split(" |, ");

                if (tokens[0].equals("vars"))
                {
                    for (int i = 1; i < tokens.length; i++)
                    {
                        c.attributes.put(tokens[i], 0);
                    }

                    tokens = reader.readLine().split(" ");
                }

                while (!tokens[0].equals("end-class"))
                {
                    ClassMethod m = new ClassMethod(tokens[1]);

                    tokens = reader.readLine().split(" |, ");

                    if (tokens[0].equals("vars"))
                    {
                        for (int i = 1; i < tokens.length; i++)
                        {
                            m.localVariables.put(tokens[i], 0);
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

                    tokens = line.split(" ");
                }
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
