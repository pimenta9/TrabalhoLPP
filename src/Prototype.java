import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Prototype {

    public static void process (String line, ArrayList<String> lines) {

        Pattern pattern = Pattern.compile("([a-zA-Z]+)._prototype = ([a-zA-Z]+)");
        Matcher matcher = pattern.matcher(line);

        if (matcher.find())
        {
            lines.add("load " + matcher.group(2));
            lines.add("load " + matcher.group(1));
            lines.add("set _prototype");
        }
    }
}