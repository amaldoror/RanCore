package org.rancore.sim.genetic;

import java.util.ArrayList;
import java.util.Random;

import static org.rancore.sim.genetic.GeneticAlgorithm.CHROMOSOME_LENGTH;
import static org.rancore.sim.genetic.GeneticAlgorithm.POPULATION_SIZE;

public class Individual implements Comparable<Individual> {
    String chromosome;
    int fitness;

    Individual(String chromosome) {
        this.chromosome = chromosome;
        this.fitness = calculateFitness();
    }

    int calculateFitness() {
        return Integer.parseInt(chromosome, 2);
    }

    static Individual selectParent(ArrayList<Individual> population) {
        Random random = new Random();
        int index = random.nextInt(POPULATION_SIZE / 2);  // Top 50% are more likely to be selected
        return population.get(index);
    }

    static Individual crossover(Individual parent1, Individual parent2) {
        Random random = new Random();
        int crossoverPoint = random.nextInt(CHROMOSOME_LENGTH);
        String offspringChromosome = parent1.chromosome.substring(0, crossoverPoint) +
                parent2.chromosome.substring(crossoverPoint);
        return new Individual(offspringChromosome);
    }

    @Override
    public int compareTo(Individual other) {
        return Integer.compare(other.fitness, this.fitness);  // Descending order
    }
}