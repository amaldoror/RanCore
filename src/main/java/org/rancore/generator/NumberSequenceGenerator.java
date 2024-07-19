package org.rancore.generator;

public class NumberSequenceGenerator {
    private int current;
    private final int step;

    public NumberSequenceGenerator(int start, int step) {
        this.current = start;
        this.step = step;
    }

    public int next() {
        int value = current;
        current += step;
        return value;
    }

    public static void main(String[] args) {
        NumberSequenceGenerator generator = new NumberSequenceGenerator(1, 2);
        for (int i = 0; i < 10; i++) {
            System.out.println(generator.next());
        }
    }
}

