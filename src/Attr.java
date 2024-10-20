import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Attr {

    public static void process(String line, ArrayList<String> lines) {
        Pattern simplePattern = Pattern.compile("([a-zA-Z]+)\\s*=\\s*([a-zA-Z0-9.]+)\\s*([a-zA-Z0-9.]+)?");
        Pattern opPattern = Pattern.compile("([a-zA-Z]+)\\s*=\\s*([a-zA-Z0-9]+)\\s*([+\\-*/])\\s*([a-zA-Z0-9]+)");
        Pattern p = Pattern.compile("([a-zA-Z]+).([a-zA-Z]+)\\s*=\\s*([a-zA-Z0-9.]+)");
        Pattern op2Pattern = Pattern.compile("([a-zA-Z]+).([a-zA-Z]+)\\s*=\\s*([a-zA-Z0-9]+)\\s*([+\\-*/])\\s*([a-zA-Z0-9]+)");

        Matcher matcher;
        // Padrão para operação com operadores (e.g., lhs = arg1 + arg2)
        matcher = opPattern.matcher(line);
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
        }
        else if((matcher = op2Pattern.matcher(line)).find()) {
            String lhs = matcher.group(1);
            String arg1 = matcher.group(2);
            String arg2 = matcher.group(3);
            String op = matcher.group(4);
            String arg3 = matcher.group(5);

            lines.add("load " + arg1);
            lines.add("load " + arg3);

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

        }
        else if ((matcher = p.matcher(line)).find()) {
            String lhs = matcher.group(1);
            String rhs = matcher.group(3);

            Rhs.process(lhs, rhs, lines);
        } else if ((matcher = simplePattern.matcher(line)).find()) {
            String lhs = matcher.group(1);
            String rhs = matcher.group(2);

            if (matcher.group(3) != null) {

                rhs = "new " + matcher.group(3);
            }

            Rhs.process(lhs, rhs, lines);
        }
    }
}
