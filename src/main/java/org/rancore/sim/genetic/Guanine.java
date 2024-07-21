package org.rancore.sim.genetic;

public class Guanine extends Nucleotide {

    public Guanine() {
        this.symbol = 'G';
    }

    @Override
    public String getName() {
        return "Guanine";
    }

    @Override
    public Nucleotide getComplement() {
        return new Cytosine(); // Guanine pairs with Cytosine
    }
}
