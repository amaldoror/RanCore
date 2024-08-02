package org.rancore.ad.abgabe02.jonas;

public class Stopwatch {

    private long start;
    public Stopwatch() {
        start = System.nanoTime();
    }


    /**
     * Returns the elapsed CPU time (in seconds) since the stopwatch was created.
     *
     * @return elapsed CPU time (in seconds) since the stopwatch was created
     */
    public double elapsedTime() {
        long now = System.nanoTime();
        return (now - start) /1e6;
    }
}
