import java.util.ArrayList;

public class Useful {

    public static void translateBody (ArrayList<String> bodyLines, ArrayList<String> lines) {

        for (String line : bodyLines) {

            lines.add("-> " + line + " <-");
        }
    }

    public static String format (String text) {

        return text.trim().replaceAll(" +", " ");
    }
}
