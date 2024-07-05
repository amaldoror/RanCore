package rancore.math;

/**
 * <p><u><b>Class Name:</b></u></p>
 * <p>HarmonicSeries</p>
 *
 * <p><u><b>Description:</b></u></p>
 * <p>The HarmonicSeries class provides methods for calculating the harmonic series.</p>
 *
 * <p><u><b>Instance Variables:</b></u></p>
 * <p>None</p>
 *
 * <p><u><b>Constructor:</b></u></p>
 * <p>None</p>
 *
 * <p><u><b>Methods:</b></u></p>
 * <p><code>harmonicSeriesIterative(int n):</code> Calculates and returns the sum of the harmonic series up to the specified value <code>n</code> iteratively.</p>
 * <p><code>harmonicSeriesRecursive(int n):</code> Calculates and returns the sum of the harmonic series up to the specified value <code>n</code> recursively.</p>
 */

public class HarmonicSeries {

    public float harmonicSeriesIterative(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Input must be positive!");
        }

        float wert = 0;
        for (int i = 1; i <= n; i++) {
            wert += 1.0f / i;
        }
        return wert;
    }

    public float harmonicSeriesRecursive(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Input must be positive!");
        }

        if (n == 1) {
            return 1;
        }
        return 1.0f / n + harmonicSeriesRecursive(n - 1);
    }

    public static void main(String[] args) {
        HarmonicSeries hr = new HarmonicSeries();
        System.out.println(hr.harmonicSeriesIterative(5));
        System.out.println(hr.harmonicSeriesRecursive(5));
    }
}
