package rancore.math;

/**
 * <p><u><b>Class Name:</b></u></p>
 * <p>Pi</p>
 *
 * <p><u><b>Description:</b></u></p>
 * <p>The Pi class provides four methods to approximate the value of Pi using different mathematical techniques.</p>
 *
 * <p><u><b>Instance Variables:</b></u></p>
 * <p><code>None</code></p>
 *
 * <p><u><b>Constructor:</b></u></p>
 * <p><code>None</code></p>
 *
 * <p><u><b>Methods:</b></u></p>
 * <p><code>pi(int n)</code>: Approximates Pi using the Ramanujan formula, a rapidly converging series with the specified number of terms.</p>
 * <p><code>archimedesMethod(int sides)</code>: Approximates Pi using the Archimedes Method by calculating the perimeter of an inscribed polygon with the specified number of sides.</p>
 * <p><code>leibnizSeries(int terms)</code>: Approximates Pi using the Leibniz series, an infinite series with the specified number of terms.</p>
 * <p><code>monteCarloMethod(int numPoints)</code>: Approximates Pi using the Monte Carlo method by generating random points within a square and calculating the ratio of points inside a unit circle.</p>
 * <p><code>ramanujanFormula(int terms)</code>: Approximates Pi using the Ramanujan formula, a rapidly converging series with the specified number of terms.</p>
 * <p><u><b>Main Method:</b></u></p>
 * <p><code>None</code></p>
 *
 * <p>It's important to note that the approximations provided by these methods are not exact and may have varying levels of accuracy depending on the input parameters.</p>
 *
 * <p><u><b>License:</b></u></p>
 * <p>Version 1.0</p>
 * <p>2023/06/03</p>
 * <p>Attribution: <a href="https://creativecommons.org/licenses/by/4.0/">CC BY</a></p>
 * <p>Adrian Morgenthal <a href="https://github.com/Voraxx">Github</a></p>
 */
public class Pi {
    public static void main(String[] args) {
        int archimedes = 1;
        int leibniz = 2;
        int monteCarlo = 3;
        int ramanujan = 4;

        int a = 100000;
        int l = 1000000;
        int m = 95000000;
        int r = 2;

        System.out.println("---------------------------------------");
        System.out.println("Different methods for approximating pi:");
        System.out.println("---------------------------------------");
        System.out.println("Archimedes: " + Pi.pi(archimedes,   a   ));
        System.out.println("Leibniz:    " + Pi.pi(leibniz,      l   ));
        System.out.println("MonteCarlo: " + Pi.pi(monteCarlo,   m   ));
        System.out.println("Ramanujan:  " + Pi.pi(ramanujan,    r   ));
        System.out.println("Math.PI:    " + Math.PI);
        System.out.println("---------------------------------------");
    }
    /**
     * <p><strong><u>Pi Approximation</u></strong></p>
     * Approximates Pi using the specified method based on the value of 'n'.
     *
     * @param method the method selector
     * @return an approximation of Pi using the specified method
     */
    public static double pi(int method, int n) {
        switch (method) {
            case 1:
                return archimedesMethod(n); // Example value = 6
            case 2:
                return leibnizSeries(n); // Example value = 100000
            case 3:
                return monteCarloMethod(n); // Example value = 10000000
            case 4:
                return ramanujanFormula(n); // Example value = 10
            default:
                throw new IllegalArgumentException("Invalid method selector. Available options: 1-4");
        }
    }

    /**
     * <p><strong><u>Archimedes Method</u></strong></p>
     * Approximates Pi by calculating the perimeter of a polygon inscribed within a circle.
     * The more sides the polygon has, the closer the approximation to Pi.
     *
     * @param sides the number of sides of the inscribed polygon
     * @return an approximation of Pi using the Archimedes Method
     */
    public static double archimedesMethod(int sides) {
        double polygonSides = sides;
        double innerAngle = 360.0 / polygonSides;
        double halfSideLength = Math.sin(Math.toRadians(innerAngle / 2));
        double polygonPerimeter = polygonSides * 2 * halfSideLength;
        return polygonPerimeter/2;
    }

    /**
     * <p><strong><u>Leibniz Series</u></strong></p>
     * Approximates Pi using the Leibniz series, an infinite series.
     * The more terms added to the series, the closer the approximation to Pi.
     *
     * @param terms the number of terms in the Leibniz series
     * @return an approximation of Pi using the Leibniz Series
     */
    public static double leibnizSeries(int terms) {
        double approximation = 0.0;
        for (int i = 0; i < terms; i++) {
            double term = (i % 2 == 0) ? 1.0 / (2 * i + 1) : -1.0 / (2 * i + 1);
            approximation += term;
        }
        return approximation * 4;
    }

    /**
     * <p><strong><u>Monte Carlo Method</u></strong></p>
     * Approximates Pi using the Monte Carlo method, which relies on random numbers.
     * By generating random points within a square, the ratio of points inside a unit circle approximates Pi/4.
     *
     * @param numPoints the number of random points to generate
     * @return an approximation of Pi using the Monte Carlo Method
     */
    public static double monteCarloMethod(int numPoints) {
        int pointsInsideCircle = 0;
        for (int i = 0; i < numPoints; i++) {
            double x = Math.random();
            double y = Math.random();
            double distance = Math.sqrt(x * x + y * y);
            if (distance <= 1) {
                pointsInsideCircle++;
            }
        }
        double ratio = (double) pointsInsideCircle / numPoints;
        return ratio * 4;
    }

    /**
     * <p><strong><u>Ramanujan Formula</u></strong></p>
     * Approximates Pi using the Ramanujan formula, a rapidly converging formula.
     * The more terms added to the series, the closer the approximation to Pi.
     *
     * @param terms the number of terms in the Ramanujan formula
     * @return an approximation of Pi using the Ramanujan Formula
     */
    public static double ramanujanFormula(int terms) {
        double approximation = 0.0;
        for (int i = 0; i < terms; i++) {
            double numerator = Factorial.factorial(4 * i) * (1103 + 26390 * i);
            double denominator = Math.pow(Factorial.factorial(i), 4) * Math.pow(396, 4 * i);
            approximation += numerator / denominator;
        }
        approximation *= 2 * Math.sqrt(2) / 9801;
        return 1 / approximation;
    }
}
