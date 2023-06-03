package rancore.math;

/**
 * <p><u><b>Class Name:</b></u></p>
 * <p>Fraction</p>
 *
 * <p><u><b>Description:</b></u></p>
 * <p>The Fraction class represents a fraction with a numerator and denominator.</p>
 *
 * <p><u><b>Instance Variables:</b></u></p>
 * <p><code>numerator:</code> An integer representing the numerator of the fraction.</p>
 * <p><code>denominator:</code> An integer representing the denominator of the fraction.</p>
 * <p><code>result:</code> A floating-point value representing the result of dividing the numerator by the denominator.</p>
 *
 * <p><u><b>Constructor:</b></u></p>
 * <p><code>Fraction(int numerator, int denominator):</code> Initializes a Fraction object with the given numerator and denominator. Also calculates the result of the fraction.</p>
 *
 * <p><u><b>Methods:</b></u></p>
 * <p><code>getNumerator():</code> Returns the numerator of the fraction.</p>
 * <p><code>getDenominator():</code> Returns the denominator of the fraction.</p>
 * <p><code>getResult():</code> Returns the result of the fraction as a floating-point value.</p>
 *
 * <p><u><b>License:</b></u></p>
 * <p>Version 1.0</p>
 * <p>2023/05/20</p>
 * <p>Attribution: <a href="https://creativecommons.org/licenses/by/4.0/">CC BY</a></p>
 * <p>Adrian Morgenthal <a href="https://github.com/Voraxx">Github</a></p>
 */

public class Fraction {
    private final int numerator;
    private final int denominator;
    private final float result;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        result = (float) numerator /denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public float getResult(){
        return result;
    }
}
