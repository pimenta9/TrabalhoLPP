import java.io.*;

public class BoolCompiler {

    public static void main(String[] args) {

        System.out.println("From " + args[0] + " to " + args[1]);

        try {

            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));

            String line;
            while((line = reader.readLine()) != null) {

                writer.write(line + "\n");
            }

            writer.close();
            reader.close();

        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }
}