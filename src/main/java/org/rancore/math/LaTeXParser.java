package org.rancore.math;

import java.util.*;
import java.util.regex.*;

public class LaTeXParser {
    private static final Map<String, String> LATEX_TO_FUNCTION = new HashMap<>();
    static {
        LATEX_TO_FUNCTION.put("\\sin", "sin");
        LATEX_TO_FUNCTION.put("\\cos", "cos");
        LATEX_TO_FUNCTION.put("\\tan", "tan");
        LATEX_TO_FUNCTION.put("\\log", "log");
        LATEX_TO_FUNCTION.put("\\ln", "log");
        LATEX_TO_FUNCTION.put("\\sqrt", "sqrt");
        LATEX_TO_FUNCTION.put("\\exp", "exp");
    }

    private static final Map<String, String> LATEX_TO_OPERATOR = new HashMap<>();
    static {
        LATEX_TO_OPERATOR.put("+", "+");
        LATEX_TO_OPERATOR.put("-", "-");
        LATEX_TO_OPERATOR.put("\\cdot", "*");
        LATEX_TO_OPERATOR.put("\\times", "*");
        LATEX_TO_OPERATOR.put("\\div", "/");
        LATEX_TO_OPERATOR.put("^", "^");
    }

    public static String parse(String latex) {
        // Remove whitespace
        latex = latex.replaceAll("\\s+", "");

        // Replace LaTeX functions
        for (Map.Entry<String, String> entry : LATEX_TO_FUNCTION.entrySet()) {
            latex = latex.replace(entry.getKey(), entry.getValue());
        }

        // Replace LaTeX operators
        for (Map.Entry<String, String> entry : LATEX_TO_OPERATOR.entrySet()) {
            latex = latex.replace(entry.getKey(), entry.getValue());
        }

        // Handle fractions
        latex = handleFractions(latex);

        // Handle subscripts and superscripts
        latex = handleSubSupScripts(latex);

        // Handle Greek letters
        latex = handleGreekLetters(latex);

        return latex;
    }

    private static String handleFractions(String latex) {
        Pattern pattern = Pattern.compile("\\\\frac\\{([^}]+)\\}\\{([^}]+)\\}");
        Matcher matcher = pattern.matcher(latex);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String numerator = matcher.group(1);
            String denominator = matcher.group(2);
            matcher.appendReplacement(sb, "((" + numerator + ")/(" + denominator + "))");
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static String handleSubSupScripts(String latex) {
        // Handle superscripts
        latex = latex.replaceAll("\\^\\{([^}]+)\\}", "^($1)");

        // Handle subscripts (we'll just remove them as they're typically for notation, not calculation)
        latex = latex.replaceAll("_\\{[^}]+\\}", "");

        return latex;
    }

    private static String handleGreekLetters(String latex) {
        latex = latex.replace("\\pi", "PI");
        latex = latex.replace("\\alpha", "alpha");
        latex = latex.replace("\\beta", "beta");
        // Add more Greek letters as needed
        return latex;
    }

    public static void main(String[] args) {
        String[] testCases = {
                "2 + 3",
                "\\frac{1}{2} + \\frac{3}{4}",
                "\\sin(x) + \\cos(y)",
                "a^2 + b^2 = c^2",
                "\\log_2(8) = 3",
                "\\alpha + \\beta = \\gamma",
                "x_1 + x_2 = 5"
        };

        for (String testCase : testCases) {
            System.out.println("LaTeX: " + testCase);
            System.out.println("Parsed: " + parse(testCase));
            System.out.println();
        }
    }
}