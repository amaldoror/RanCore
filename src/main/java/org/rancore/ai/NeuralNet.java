package org.rancore.ai;

/**
 * <p><u><b>Class Name</b></u></p>
 * <p>NeuralNet</p>
 *
 * <p><u><b>Description</b></u></p>
 * <p>The NeuralNet class represents a simple feedforward neural network. It consists of an input layer, a hidden layer, and an output layer. The network is initialized with random weights, and it provides a method for performing feedforward computation.</p>
 *
 * <p><u><b>Instance Variables</b></u></p>
 * <p><code>inputSize</code>: An integer representing the number of neurons in the input layer.</p>
 * <p><code>hiddenSize</code>: An integer representing the number of neurons in the hidden layer.</p>
 * <p><code>outputSize</code>: An integer representing the number of neurons in the output layer.</p>
 * <p><code>weights</code>: A 2D array representing the weights connecting the neurons in the input and hidden layers, and the hidden and output layers.</p>
 *
 * <p><u><b>Constructor</b></u></p>
 * <p><code>NeuralNet(int inputSize, int hiddenSize, int outputSize)</code>: Initializes the NeuralNet object with the specified number of neurons in the input, hidden, and output layers. It also initializes the weights with random values.</p>
 *
 * <p><u><b>Methods</b></u></p>
 * <p><code>feedForward(double[] input)</code>: Performs the feedforward computation on the neural network given an input vector. It returns the output vector computed by the network.</p>
 *
 * <p><u><b>Helper Method</b></u></p>
 * <p><code>sigmoid(double x)</code>: Computes the sigmoid function for a given input value.</p>
 *
 * <p><u><b>License</b></u></p>
 * <p>Version 1.0</p>
 * <p>2023/06/03</p>
 * <p>Attribution: <a href="https://creativecommons.org/licenses/by/4.0/">CC BY</a></p>
 * <p>Adrian Morgenthal <a href="https://github.com/Voraxx">Github</a></p>
 */

public class NeuralNet {
    private int inputSize;
    private int hiddenSize;
    private int outputSize;
    private double[][] weights;

    public NeuralNet(int inputSize, int hiddenSize, int outputSize) {
        this.inputSize = inputSize;
        this.hiddenSize = hiddenSize;
        this.outputSize = outputSize;

        // Initialise weights with random values
        weights = new double[inputSize + 1][hiddenSize];
        for (int i = 0; i <= inputSize; i++) {
            for (int j = 0; j < hiddenSize; j++) {
                weights[i][j] = Math.random();
            }
        }
    }

    public double[] feedForward(double[] input) {
        // Calculate the weighted sum of the input neurons onto the hidden layer
        double[] hiddenLayerOutput = new double[hiddenSize];
        for (int j = 0; j < hiddenSize; j++) {
            double sum = 0;
            for (int i = 0; i < inputSize; i++) {
                sum += input[i] * weights[i][j];
            }
            // Add the threshold (bias)
            sum += weights[inputSize][j];
            hiddenLayerOutput[j] = sigmoid(sum);
        }

        // Calculate the weighted sum of the hidden layer onto the input neurons
        double[] output = new double[outputSize];
        for (int k = 0; k < outputSize; k++) {
            double sum = 0;
            for (int j = 0; j < hiddenSize; j++) {
                sum += hiddenLayerOutput[j] * weights[j][k];
            }
            output[k] = sigmoid(sum);
        }

        return output;
    }

    private double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }
}
