import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodCall {

    public static void process (String line, ArrayList<String> lines) {

        // line = obj.method(a, b)

        // se necessário : formatar com line = line.replaceAll(" ", "");

        // DÚVIDA.: pode haver mais de um espaço depois da virgula q separa os params?
        Pattern pattern = Pattern.compile("\\(([a-zA-z]+)|, ([a-zA-z]+)");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {

            if (matcher.group(1) != null)
                lines.add("load " + matcher.group(1));

            if (matcher.group(2) != null)
                lines.add("load " + matcher.group(2));
        }

        pattern = Pattern.compile("([a-zA-z]+).");
        matcher = pattern.matcher(line);
        matcher.find();

        lines.add("load " + matcher.group(1));

        pattern = Pattern.compile("\\.([a-zA-z]+)");
        matcher = pattern.matcher(line);
        matcher.find();

        lines.add("call " + matcher.group(1));
    }
}