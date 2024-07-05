package rancore.math;

/**
 <p><u><b>Class Name:</b></u></p>
 <p>Fibonacci</p>
 <p><u><b>Description:</b></u></p>
 <p>The Fibonacci class provides methods for calculating Fibonacci numbers.</p>
 <p><u><b>Instance Variables:</b></u></p>
 <p>None</p>
 <p><u><b>Constructor:</b></u></p>
 <p>None</p>
 <p><u><b>Methods:</b></u></p>
 <p><code>main(String[] args):</code> The entry point of the program. Calculates and prints Fibonacci numbers up to a specified position.</p>
 <p><code>fibonacci(int n):</code> Calculates and returns the Fibonacci number at the specified index.</p>
 */
public class Fibonacci {
    public static void main(String[] args) {
        int n = 10; // Die Anzahl der Fibonacci-Zahlen, die berechnet werden sollen
        System.out.println("Fibonacci numbers n = " + n + ":");
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }

    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
