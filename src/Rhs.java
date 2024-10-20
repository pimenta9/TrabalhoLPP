import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rhs {

        public static void process(String line, ArrayList<String> lines) {

            Pattern pattern;
            Matcher matcher;

            String[] regexes = {"^new ", "\\(", "\\.", "[0-9]+", "[+\\-*/]"};

            int i;
            for (i = 0; i < regexes.length; i++)
            {
                pattern = Pattern.compile(regexes[i]);

                if (pattern.matcher(line).find())
                    break;
            }

            switch (i)
            {
                case 0:
                    lines.add(line);
                    break;

                case 1:
                    MethodCall.process(line, lines);
                    break;

                case 2:
                    pattern = Pattern.compile("([a-zA-Z]+).([a-zA-Z]+)");
                    matcher = pattern.matcher(line);
                    if (matcher.find())
                    {
                        lines.add("load " + matcher.group(1));
                        lines.add("get " + matcher.group(2));
                    }
                    break;

                case 3:
                    lines.add("const " + line);
                    break;

                case 4:
                    pattern = Pattern.compile("([a-zA-Z]+) ([+\\-*/]) ([a-zA-Z]+)");
                    matcher = pattern.matcher(line);
                    if (matcher.find())
                    {
                        lines.add("load " + matcher.group(1));
                        lines.add("load " + matcher.group(3));
                        switch (matcher.group(2))
                        {
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
                                break;
                        }
                    }
                    break;

                default:
                    lines.add("load " + line);
                    break;
            }
        }
}
