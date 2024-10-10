import java.util.ArrayList;

public class MethodCall {

    public static void process (String line, ArrayList<String> lines) {

        lines.add(line + " (METHOD-CALL)");
    }
}