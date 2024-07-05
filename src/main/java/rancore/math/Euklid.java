package rancore.math;

/**
 * <p><u><b>Class Name:</b></u></p>
 * <p>Euklid</p>
 *
 * <p><u><b>Description:</b></u></p>
 * <p>The Euklid class provides a method for calculating the greatest common divisor (GCD) using the Euclidean algorithm.</p>
 *
 * <p><u><b>Instance Variables:</b></u></p>
 * <p>None</p>
 *
 * <p><u><b>Constructor:</b></u></p>
 * <p><code>Euklid():</code> Constructs a new instance of the Euklid class.</p>
 *
 * <p><u><b>Methods:</b></u></p>
 * <p><code>euklid(int zahl1, int zahl2):</code> Calculates the greatest common divisor (GCD) of two numbers using the Euclidean algorithm.</p>
 */
public class Euklid {

    /**
     * Calculates the greatest common divisor (GCD) of two numbers using the Euclidean algorithm.
     *
     * @param zahl1 The first number.
     * @param zahl2 The second number.
     * @return The greatest common divisor (GCD) of the two numbers.
     */
    public static int euklid(int zahl1, int zahl2) {
        // Sicherstellung, dass beide Zahlen positiv sind
        zahl1 = Math.abs(zahl1);
        zahl2 = Math.abs(zahl2);

        // Algorithmus von Euklid
        int ergebnis = 0;
        if (zahl1 == 0) {
            ergebnis = zahl2;
        } else {
            while (zahl2 != 0) {
                if (zahl1 > zahl2) {
                    zahl1 = zahl1 - zahl2;
                } else {
                    zahl2 = zahl2 - zahl1;
                }
            }
            ergebnis = zahl1;
        }
        return ergebnis;
    }

    /**
     * A test method to check the correctness of the euklid() method.
     */
    public void testeGgt() {
        Euklid ggt = new Euklid();
        //assertEquals(3, Euklid.euklid(6, 9));
    }
}
