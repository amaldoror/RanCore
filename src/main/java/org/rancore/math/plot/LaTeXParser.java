// -*- coding: utf-8 -*-

package org.rancore.math.plot;

import java.util.*;
import java.util.regex.*;

public class LaTeXParser {
    private static final Map<String, String> LATEX_TO_FUNCTION = new HashMap<>();
    static {
        LATEX_TO_FUNCTION.put("\\sin", "sin");
        LATEX_TO_FUNCTION.put("\\cos", "cos");
        LATEX_TO_FUNCTION.put("\\tan", "tan");
        LATEX_TO_FUNCTION.put("\\arcsin", "asin");
        LATEX_TO_FUNCTION.put("\\arccos", "acos");
        LATEX_TO_FUNCTION.put("\\arctan", "atan");
        LATEX_TO_FUNCTION.put("\\log", "log");
        LATEX_TO_FUNCTION.put("\\ln", "log");
        LATEX_TO_FUNCTION.put("\\sqrt", "sqrt");
        LATEX_TO_FUNCTION.put("\\exp", "exp");
        LATEX_TO_FUNCTION.put("\\abs", "abs");
        LATEX_TO_FUNCTION.put("\\floor", "floor");
        LATEX_TO_FUNCTION.put("\\ceil", "ceil");
    }

    private static final Map<String, String> LATEX_TO_OPERATOR = new HashMap<>();
    static {
        LATEX_TO_OPERATOR.put("+", "+");
        LATEX_TO_OPERATOR.put("-", "-");
        LATEX_TO_OPERATOR.put("\\cdot", "*");
        LATEX_TO_OPERATOR.put("\\times", "*");
        LATEX_TO_OPERATOR.put("\\div", "/");
        LATEX_TO_OPERATOR.put("^", "^");
        LATEX_TO_OPERATOR.put("\\pm", "±");
        LATEX_TO_OPERATOR.put("\\geq", ">=");
        LATEX_TO_OPERATOR.put("\\leq", "<=");
        LATEX_TO_OPERATOR.put("\\neq", "!=");
    }

    private static final Map<String, String> LATEX_TO_GREEK = new HashMap<>();
    static {
        LATEX_TO_GREEK.put("\\alpha", "\\u03B1");
        LATEX_TO_GREEK.put("\\beta", "\\u03B2");
        LATEX_TO_GREEK.put("\\gamma", "\\u03B3");
        LATEX_TO_GREEK.put("\\delta", "\\u03B4");
        LATEX_TO_GREEK.put("\\epsilon", "\\u03B5");
        LATEX_TO_GREEK.put("\\zeta", "\\u03B6");
        LATEX_TO_GREEK.put("\\eta", "\\u03B7");
        LATEX_TO_GREEK.put("\\theta", "\\u03B8");
        LATEX_TO_GREEK.put("\\iota", "\\u03B9");
        LATEX_TO_GREEK.put("\\kappa", "\\u03BA");
        LATEX_TO_GREEK.put("\\lambda", "\\u03BB");
        LATEX_TO_GREEK.put("\\mu", "\\u03BC");
        LATEX_TO_GREEK.put("\\nu", "\\u03BD");
        LATEX_TO_GREEK.put("\\xi", "\\u03BE");
        LATEX_TO_GREEK.put("\\pi", "\\u03C0");
        LATEX_TO_GREEK.put("\\rho", "\\u03C1");
        LATEX_TO_GREEK.put("\\sigma", "\\u03C3");
        LATEX_TO_GREEK.put("\\tau", "\\u03C4");
        LATEX_TO_GREEK.put("\\upsilon", "\\u03C5");
        LATEX_TO_GREEK.put("\\phi", "\\u03C6");
        LATEX_TO_GREEK.put("\\chi", "\\u03C7");
        LATEX_TO_GREEK.put("\\psi", "\\u03C8");
        LATEX_TO_GREEK.put("\\omega", "\\u03C9");
    }

    public static String parse(String latex) {
        // Remove whitespace
        latex = latex.replaceAll("\\s+", "");

        // Handle special cases
        latex = handleSpecialCases(latex);

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

        // Handle integrals
        latex = handleIntegrals(latex);

        // Handle summations
        latex = handleSummations(latex);

        // Handle limits
        latex = handleLimits(latex);

        return latex;
    }

    private static String handleSpecialCases(String latex) {
        // Handle \left( and \right)
        latex = latex.replace("\\left(", "(").replace("\\right)", ")");

        // Handle \text{}
        latex = latex.replaceAll("\\\\text\\{([^}]+)\\}", "$1");

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

        // Handle subscripts
        latex = latex.replaceAll("_\\{([^}]+)\\}", "_$1");

        return latex;
    }

    private static String handleGreekLetters(String latex) {
        for (Map.Entry<String, String> entry : LATEX_TO_GREEK.entrySet()) {
            latex = latex.replace(entry.getKey(), entry.getValue());
        }
        return latex;
    }

    private static String handleIntegrals(String latex) {
        Pattern pattern = Pattern.compile("\\\\int_(\\{[^}]+\\}|\\S)\\^(\\{[^}]+\\}|\\S)");
        Matcher matcher = pattern.matcher(latex);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String lowerBound = matcher.group(1).replaceAll("[{}]", "");
            String upperBound = matcher.group(2).replaceAll("[{}]", "");
            matcher.appendReplacement(sb, "integral(" + lowerBound + "," + upperBound + ")");
        }
        matcher.appendTail(sb);
        return sb.toString().replace("\\int", "integral");
    }

    private static String handleSummations(String latex) {
        Pattern pattern = Pattern.compile("\\\\sum_(\\{[^}]+\\}|\\S)\\^(\\{[^}]+\\}|\\S)");
        Matcher matcher = pattern.matcher(latex);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String lowerBound = matcher.group(1).replaceAll("[{}]", "");
            String upperBound = matcher.group(2).replaceAll("[{}]", "");
            matcher.appendReplacement(sb, "sum(" + lowerBound + "," + upperBound + ")");
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static String handleLimits(String latex) {
        Pattern pattern = Pattern.compile("\\\\lim_(\\{[^}]+\\}|\\S)");
        Matcher matcher = pattern.matcher(latex);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String limit = matcher.group(1).replaceAll("[{}]", "");
            matcher.appendReplacement(sb, "limit(" + limit + ")");
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] testCases = {
                "2 + 3",
                "\\frac{1}{2} + \\frac{3}{4}",
                "\\sin(x) + \\cos(y)",
                "a^2 + b^2 = c^2",
                "\\log_2(8) = 3",
                "\\alpha + \\beta = \\gamma",
                "x_1 + x_2 = 5",
                "\\int_{0}^{\\infty} e^{-x^2} dx",
                "\\sum_{i=1}^{n} i^2",
                "\\lim_{x \\to 0} \\frac{\\sin(x)}{x}",
                "\\left(\\frac{a}{b}\\right)^2",
                "\\text{f}(x) = x^2 + 2x + 1",
                "\\frac{d}{dx}\\left(x^2\\right) = 2x",
                "\\sqrt[3]{x^2 + y^2}",
                "a \\pm b \\geq c",
                "\\arcsin(\\frac{1}{2}) = \\frac{\\pi}{6}"
        };

        for (String testCase : testCases) {
            System.out.println("LaTeX: " + testCase);
            System.out.println("Parsed: " + parse(testCase));
            System.out.println();
        }
    }
}