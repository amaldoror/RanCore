package org.rancore.sim.genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static org.rancore.sim.genetic.Individual.crossover;
import static org.rancore.sim.genetic.Individual.selectParent;

public class GeneticAlgorithm {

    static final int POPULATION_SIZE = 100;
    static final int CHROMOSOME_LENGTH = 10;
    static final int MAX_GENERATIONS = 100;
    static final double MUTATION_RATE = 0.01;

    public static void main(String[] args) {
        ArrayList<Individual> population = initializePopulation();

        for (int generation = 0; generation < MAX_GENERATIONS; generation++) {
            Collections.sort(population);

            ArrayList<Individual> newPopulation = new ArrayList<>();

            for (int i = 0; i < POPULATION_SIZE / 2; i++) {
                Individual parent1 = selectParent(population);
                Individual parent2 = selectParent(population);
                Individual offspring1 = crossover(parent1, parent2);
                Individual offspring2 = crossover(parent2, parent1);

                mutate(offspring1);
                mutate(offspring2);

                newPopulation.add(offspring1);
                newPopulation.add(offspring2);
            }

            population = newPopulation;
        }

        Collections.sort(population);
        System.out.println("Best solution: " + population.get(0).chromosome + " with fitness " + population.get(0).fitness);
    }

    static ArrayList<Individual> initializePopulation() {
        ArrayList<Individual> population = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < POPULATION_SIZE; i++) {
            StringBuilder chromosome = new StringBuilder();
            for (int j = 0; j < CHROMOSOME_LENGTH; j++) {
                chromosome.append(random.nextBoolean() ? "1" : "0");
            }
            population.add(new Individual(chromosome.toString()));
        }
        return population;
    }


    static void mutate(Individual individual) {
        Random random = new Random();
        StringBuilder mutatedChromosome = new StringBuilder(individual.chromosome);

        for (int i = 0; i < CHROMOSOME_LENGTH; i++) {
            if (random.nextDouble() < MUTATION_RATE) {
                mutatedChromosome.setCharAt(i, mutatedChromosome.charAt(i) == '0' ? '1' : '0');
            }
        }

        individual.chromosome = mutatedChromosome.toString();
        individual.fitness = individual.calculateFitness();
    }
}
