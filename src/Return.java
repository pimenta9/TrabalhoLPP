import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Return {

    public static void process (String line, ArrayList<String> lines) {

        // line = "return x"

        Pattern pattern = Pattern.compile(" [a-zA-z]+");
        Matcher matcher = pattern.matcher(line);

        matcher.find();

        lines.add("load" + matcher.group());
        lines.add("ret");
    }
}