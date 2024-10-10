import java.util.ArrayList;
import java.util.regex.Pattern;

public class Useful {

    public static void translateBody (ArrayList<String> bodyLines, ArrayList<String> lines) {

        Pattern pattern;

        String[] regexes = {"._", "=", "^if ", "\\(", "^return"};

        for (String line : bodyLines) {

            int bodyStmt = 5;

            for (int i = 0; i <= 4; i++)
            {
                pattern = Pattern.compile(regexes[i]);

                if (pattern.matcher(line).find())
                {
                    bodyStmt = i;
                    break;
                }
            }

            switch (bodyStmt)
            {
                case 0: // prototype
                    Prototype.process(line, lines);
                    break;

                case 1: // attr
                    Attr.process(line, lines);
                    break;

                case 2: // if
                    // CODE HERE
                    break;

                case 3: // method-call
                    MethodCall.process(line, lines);
                    break;

                case 4:
                    Return.process(line, lines);
                    break;

                default:
                    break;
            }

            // lines.add("-> " + line + " <-");
        }
    }

    public static String format (String text) {

        return text.trim().replaceAll(" +", " ");
    }
}
