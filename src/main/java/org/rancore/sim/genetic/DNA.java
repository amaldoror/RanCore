package org.rancore.sim.genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// DNA class
public class DNA {

    private static final Random RANDOM = new Random();
    private final List<Nucleotide> sequence;

    // Constructor with random sequence
    public DNA(int length) {
        this.sequence = generateRandomSequence(length);
    }

    // Constructor with a given sequence
    public DNA(List<Nucleotide> sequence) {
        this.sequence = new ArrayList<>(sequence);
    }

    // Generate a random DNA sequence of given length
    private List<Nucleotide> generateRandomSequence(int length) {
        List<Nucleotide> seq = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            seq.add(randomNucleotide());
        }
        return seq;
    }

    // Randomly select a nucleotide
    private Nucleotide randomNucleotide() {
        return switch (RANDOM.nextInt(4)) {
            case 0 -> new Adenosine();
            case 1 -> new Cytosine();
            case 2 -> new Guanine();
            case 3 -> new Thymine();
            default -> throw new IllegalStateException("Unexpected value");
        };
    }

    // Check if a sequence is valid
    private boolean isValidSequence(List<Nucleotide> sequence) {
        for (Nucleotide nucleotide : sequence) {
            if (!(nucleotide instanceof Adenosine || nucleotide instanceof Cytosine ||
                    nucleotide instanceof Guanine || nucleotide instanceof Thymine)) {
                return false;
            }
        }
        return true;
    }

    // Get the length of the DNA sequence
    public int getLength() {
        return sequence.size();
    }

    // Get the DNA sequence as a string
    public String getSequence() {
        StringBuilder sb = new StringBuilder(sequence.size());
        for (Nucleotide nucleotide : sequence) {
            sb.append(nucleotide.getSymbol());
        }
        return sb.toString();
    }

    // Mutate the DNA sequence by changing a single nucleotide
    public void mutate(double mutationRate) {
        for (int i = 0; i < sequence.size(); i++) {
            if (RANDOM.nextDouble() < mutationRate) {
                sequence.set(i, randomNucleotide());
            }
        }
    }

    // Perform crossover between this DNA and another DNA to create a new DNA
    public DNA crossover(DNA other, int crossoverPoint) {
        if (crossoverPoint < 0 || crossoverPoint > this.sequence.size()) {
            throw new IllegalArgumentException("Invalid crossover point.");
        }
        List<Nucleotide> newSequence = new ArrayList<>();
        newSequence.addAll(this.sequence.subList(0, crossoverPoint));
        newSequence.addAll(other.sequence.subList(crossoverPoint, other.sequence.size()));
        return new DNA(newSequence);
    }

    // Calculate the fitness of the DNA (example: number of 'A' nucleotides)
    public int calculateFitness() {
        int fitness = 0;
        for (Nucleotide nucleotide : sequence) {
            if (nucleotide instanceof Adenosine) {
                fitness++;
            }
        }
        return fitness;
    }

    @Override
    public String toString() {
        return getSequence();
    }

    public static void main(String[] args) {
        DNA dna1 = new DNA(10);
        DNA dna2 = new DNA(10);

        System.out.println("DNA 1: " + dna1);
        System.out.println("DNA 2: " + dna2);

        DNA crossoverDNA = dna1.crossover(dna2, 5);
        System.out.println("Crossover DNA: " + crossoverDNA);

        dna1.mutate(0.1);  // 10% mutation rate
        System.out.println("Mutated DNA 1: " + dna1);

        System.out.println("Fitness of DNA 1: " + dna1.calculateFitness());
    }
}