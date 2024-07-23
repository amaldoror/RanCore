package org.rancore.math;

import uk.ac.ed.ph.snuggletex.*;

public class LaTeXParser2 {

    private final SnuggleEngine engine;
    private final XMLStringOutputOptions options;

    public LaTeXParser2() {
        this.engine = new SnuggleEngine();
        this.options = new XMLStringOutputOptions();
        this.options.setSerializationMethod(SerializationMethod.XML);
        this.options.setIndenting(true);
        this.options.setEncoding("UTF-8");
    }

    public String parse(String latex) {
        try {
            SnuggleSession session = engine.createSession();
            session.parseInput(new SnuggleInput(latex));
            String result = session.buildXMLString(options);

            // Clean up the output
            result = result.replaceAll("<\\?xml.*\\?>", "")  // Remove XML declaration
                    .replaceAll("<math.*?>", "")      // Remove opening math tag
                    .replaceAll("</math>", "")        // Remove closing math tag
                    .trim();                          // Trim whitespace

            return result;
        } catch (Exception e) {
            return "Error parsing LaTeX: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        LaTeXParser2 parser = new LaTeXParser2();
        String[] testCases = {
                "2 + 3",
                "\\frac{1}{2} + \\frac{3}{4}",
                "\\sin(x) + \\cos(y)",
                "a^2 + b^2 = c^2",
                "\\log_2(8) = 3",
                "\\alpha + \\beta = \\gamma",
                "x_1 + x_2 = 5",
                "\\int_{0}^{\\infty} e^{-x^2} dx = \\frac{\\sqrt{\\pi}}{2}"
        };

        for (String testCase : testCases) {
            System.out.println("LaTeX: " + testCase);
            System.out.println("Parsed: " + parser.parse(testCase));
            System.out.println();
        }
    }
}