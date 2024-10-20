import java.util.ArrayList;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Rhs {

        public static void process(String lhs, String rhs, ArrayList<String> lines) {

            if (rhs.matches("\\d+")) {
                lines.add("const " + rhs);  // Número
            }
            else if (rhs.contains(".")) {
                String[] parts = rhs.split("\\.");
                if (rhs.matches("[a-zA-Z]+\\.[a-zA-Z]+\\(.*\\)")) {
                    MethodCall.process(rhs, lines);
                } else {
                    lines.add("load " + parts[0]);
                    lines.add("get " + parts[1]);
                }
            }
            else if (rhs.matches("\\s*new\\s+([a-zA-Z]+)")) {
                Matcher matcher = Pattern.compile("\\s*new\\s+([a-zA-Z]+)").matcher(rhs);
                if (matcher.find()) {
                    String className = matcher.group(1);
                    lines.add("new " + className);
                }
            }
            else {
                lines.add("load " + rhs);
            }
            Lhs.process(lhs, lines);
        }
        /*
        if (matcher.find()) { // ESTA CORRETO
        String lhs = matcher.group(1);
        String arg1 = matcher.group(2);
        String op = matcher.group(3);
        String arg2 = matcher.group(4);

        lines.add("load " + arg1);
        lines.add("load " + arg2);

        switch (op) {
            case "+":
                lines.add("add");
                break;
            case "-":
                lines.add("sub");
                break;
            case "*":
                lines.add("mul");
                break;
            case "/":
                lines.add("div");
                break;
            default:
                throw new IllegalArgumentException("Erro. Operador desconhecido: " + op);
        }
        Lhs.process(lhs, lines);
    }*/
}
