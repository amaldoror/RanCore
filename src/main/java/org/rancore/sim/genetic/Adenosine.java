package org.rancore.sim.genetic;

public class Adenosine extends Nucleotide {

    public Adenosine() {
        this.symbol = 'A';
    }

    @Override
    public String getName() {
        return "Adenosine";
    }

    @Override
    public Nucleotide getComplement() {
        return new Thymine(); // Adenosine pairs with Thymine
    }
}


