import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class BoolCompiler {

    public static void main(String[] args) {

        System.out.println("From " + args[0] + " to " + args[1]);

        try {

            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));

            Pattern begin = Pattern.compile("^begin"); // colocar ^ após formatar
            Pattern end = Pattern.compile("^end$|^end-method"); // colocar ^ após formatar

            ArrayList<String> lines = new ArrayList<>();
            ArrayList<String> bodyLines = new ArrayList<>();

            String line;
            while((line = reader.readLine()) != null) {

                line = Useful.format(line);

                if (!line.isEmpty())
                    lines.add(line);

                if(begin.matcher(line).find()) {

                    line = reader.readLine();
                    line = Useful.format(line);

                    while(!end.matcher(line).find()) {

                        bodyLines.add(line);

                        line = reader.readLine();
                        line = Useful.format(line);
                    }

                    Useful.translateBody(bodyLines, lines);
                    bodyLines.clear();

                    line = Useful.format(line);
                    lines.add(line);
                }
            }

            writer.write(lines.getFirst());
            for (int i = 1; i < lines.size(); i++) {

                writer.write("\n" + lines.get(i));
            }

            writer.close();
            reader.close();


        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }
}