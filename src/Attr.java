import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Attr {

    public static void process(String line, ArrayList<String> lines) {
        Pattern attrPattern = Pattern.compile("([a-zA-Z]+)\\.([a-zA-Z]+)\\s*=\\s*([a-zA-Z0-9.]+)");
        Pattern simplePattern = Pattern.compile("([a-zA-Z]+)\\s*=\\s*([a-zA-Z0-9.]+)");
        Pattern opPattern = Pattern.compile("([a-zA-Z]+)\\s*=\\s*([a-zA-Z0-9]+)\\s*([+\\-*/])\\s*([a-zA-Z0-9]+)"); // o "-" será tratado para subtração e não como caracter especial

        Matcher matcher;
        //do tipo <lhs> = <arg-bin> <op> <arg-bin> ‘\n’
        matcher = opPattern.matcher(line);
        if (matcher.find()) {
            String lhs = matcher.group(1);
            String arg1 = matcher.group(2);
            String op = matcher.group(3);
            String arg2 = matcher.group(4);

            lines.add("load " + arg1);
            lines.add(op + " " + arg2);
            lines.add("store " + lhs);
        }
        //do tipo <lhs> = <arg> ‘\n’
        else if ((matcher = attrPattern.matcher(line)).find()) {
            String lhs = matcher.group(1);
            String attribute = matcher.group(2);
            String rhs = matcher.group(3);

            if (rhs.matches("\\d+")) { // verificação se é um numero
                lines.add("const " + rhs);
            } else if (rhs.contains(".")) {
                String[] parts = rhs.split("\\.");
                lines.add("load " + parts[0]);
                lines.add("get " + parts[1]);   // Obtém o atributo
            } else {
                lines.add("load " + rhs);       // Carrega a variável
            }
            lines.add("load " + lhs);
            lines.add("set " + attribute);
        }
        // Verificação se é uma atribuição simples
        else if ((matcher = simplePattern.matcher(line)).find()) {
            String lhs = matcher.group(1);  // Nome da variável à esquerda
            String rhs = matcher.group(2);  // Valor ou variável à direita

            // Verifica se o lado direito é uma constante
            if (rhs.matches("\\d+")) {
                lines.add("const " + rhs);      // Carrega o valor constante
            } else if (rhs.contains(".")) {
                // Se for um atributo de outro objeto, gera o código apropriado
                String[] parts = rhs.split("\\.");
                lines.add("load " + parts[0]);  // Carrega o objeto
                lines.add("get " + parts[1]);   // Obtém o atributo
            } else {
                lines.add("load " + rhs);
            }
            lines.add("store " + lhs);
        }
    }
}
