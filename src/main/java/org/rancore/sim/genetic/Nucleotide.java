package org.rancore.sim.genetic;

public abstract class Nucleotide {
    protected char symbol;

    public char getSymbol() {
        return symbol;
    }

    public abstract String getName();

    public abstract Nucleotide getComplement();

    @Override
    public String toString() {
        return getName() + " (" + getSymbol() + ")";
    }
}
