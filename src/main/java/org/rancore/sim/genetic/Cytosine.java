package org.rancore.sim.genetic;

public class Cytosine extends Nucleotide {

    public Cytosine() {
        this.symbol = 'C';
    }

    @Override
    public String getName() {
        return "Cytosine";
    }

    @Override
    public Nucleotide getComplement() {
        return new Guanine(); // Cytosine pairs with Guanine
    }
}

