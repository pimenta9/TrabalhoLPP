package Compiler;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Attr {

    public static void process(String line, ArrayList<String> lines) {

        Pattern rhsPattern = Pattern.compile("= ([a-zA-Z0-9(),. +\\-*/]+)");
        Matcher rhsMatcher = rhsPattern.matcher(line);

        if (rhsMatcher.find())
        {
            Rhs.process(rhsMatcher.group(1), lines);
        }

        Pattern lhsPattern = Pattern.compile("([a-zA-Z.]+) =");
        Matcher lhsMatcher = lhsPattern.matcher(line);

        if (lhsMatcher.find())
        {
            Lhs.process(lhsMatcher.group(1), lines);
        }
    }
}