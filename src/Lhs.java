import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lhs {

    public static void process(String line, ArrayList<String> lines) {
        Pattern p = Pattern.compile("([a-zA-Z]+.[a-zA-Z]+)");

        Matcher m = p.matcher(line);
        if(m.find()) {
            lines.add("load "+m.group(1));
            lines.add("set "+m.group(2));

        }else{
            lines.add("store "+line);
        }
    }
}
