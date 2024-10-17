import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class If {

    public static void process (ArrayList<String> body, ArrayList<String> lines) {

        //lines.add(" -> " + body.get(0));
        Pattern pattern = Pattern.compile(" ([a-zA-Z]+) ([a-zA-Z]+) ([a-zA-Z]+) ");
        Matcher matcher = pattern.matcher(body.get(0));

        matcher.find();

        lines.add("load " + matcher.group(1));
        lines.add("load " + matcher.group(3));
        lines.add(matcher.group(2));

        pattern = Pattern.compile("(^else)|(^end-if)");
        boolean isThereElse = false;
        ArrayList<String> ifBody = new ArrayList<>();
        for (int i = 1; i < body.size(); i++) {

            String line = body.get(i);

            if (!line.isEmpty()) {

                matcher = pattern.matcher(line);

                if (matcher.find()) {

                    isThereElse = matcher.group(1) != null;

                    break;
                }

                ifBody.add(line);
            }
        }

        ArrayList<String> translatedIfBody = new ArrayList<>();

        Useful.translateBody(ifBody, translatedIfBody);

        lines.add("if " + translatedIfBody.size());
        lines.addAll(translatedIfBody);

        if (!isThereElse)
        {

        }

        /**/
        for (String line : body) {

            if(!line.isEmpty()) {
                lines.add(line + " (IF)");
            }
        }
        /**/
    }
}