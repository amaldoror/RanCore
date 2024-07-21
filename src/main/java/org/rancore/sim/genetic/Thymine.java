package org.rancore.sim.genetic;

public class Thymine extends Nucleotide {

    public Thymine() {
        this.symbol = 'T';
    }

    @Override
    public String getName() {
        return "Thymine";
    }

    @Override
    public Nucleotide getComplement() {
        return new Adenosine(); // Thymine pairs with Adenosine
    }
}

