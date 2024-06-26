# <u>RanCore</u>

This is my personal Java library for educational purposes.
It provides classes, methods and interfaces for several (unrelated) domains
and is meant to stay in a purely experimental state. '

---

### <u>rancore.ai</u>
The Neural Net class was my first experiment with Machine Learning:


> <p><u><b>Class Name</b></u></p>
> <p>NeuralNet</p>
>
> <p><u><b>Description</b></u></p>
> <p>The NeuralNet class represents a simple feedforward neural network. It consists of an input layer, a hidden layer, and an output layer. The network is initialized with random weights, and it provides a method for performing feedforward computation.</p>
>
> <p><u><b>Instance Variables</b></u></p>
> <p><code>inputSize</code>: An integer representing the number of neurons in the input layer.</p>
> <p><code>hiddenSize</code>: An integer representing the number of neurons in the hidden layer.</p>
> <p><code>outputSize</code>: An integer representing the number of neurons in the output layer.</p>
> <p><code>weights</code>: A 2D array representing the weights connecting the neurons in the input and hidden layers, and the hidden and output layers.</p>
>
> <p><u><b>Constructor</b></u></p>
> <p><code>NeuralNet(int inputSize, int hiddenSize, int outputSize)</code>: Initializes the NeuralNet object with the specified number of neurons in the input, hidden, and output layers. It also initializes the weights with random values.</p>
>
> <p><u><b>Methods</b></u></p>
> <p><code>feedForward(double[] input)</code>: Performs the feedforward computation on the neural network given an input vector. It returns the output vector computed by the network.</p>
>
> <p><u><b>Helper Method</b></u></p>
> <p><code>sigmoid(double x)</code>: Computes the sigmoid function for a given input value.</p>
>
> <p><u><b>License</b></u></p>
> <p>Version 1.0</p>
> <p>2023/06/03</p>
> <p>Attribution: <a href="https://creativecommons.org/licenses/by/4.0/">CC BY</a></p>
> <p>Adrian Morgenthal <a href="https://github.com/Voraxx">Github</a></p>

---

## rancore.aleph

This package contains only one class with experimental String methods.

---

## rancore.crypto

Encryption algorithms:

- KSHA3
- SHA3
- Turing Machine

The respective papers for the algorithms can be found online.

> <p><u><b>Class Name</b></u></p>
> <p>KSHA3</p>
>
> <p><u><b>Description</b></u></p>
> KSHA3 is an implementation of the Keccak-f function, which is a component of the SHA-3 cryptographic hash function.
> The calculateHash method takes an input string, converts it to bytes using UTF-8 encoding,
> and then applies the Keccak-f function to generate a hash value.
> The resulting hash is returned as a hexadecimal string.
> <p>
> Code breakdown:
> calculateHash() receives a string input as input.
> The input string is converted to bytes using the UTF-8 encoding.
> keccakF() is called with the input bytes to perform the Keccak-f operation and obtain the encrypted bytes.
> The encrypted bytes are converted to a hexadecimal string using the bytesToHex method.
> The hexadecimal string representing the hash value is returned as the result.
> <p>
> The keccakF method performs the Keccak-f operation on the input state.
> It consists of multiple rounds, each of which applies different transformations to the state.
> The rounds include operations such as theta, rho, pi, chi, and iota.
> These operations modify the state to achieve diffusion and confusion,
> which are important properties of cryptographic hash functions.
> <p>
> The other methods in the code are utility methods for converting between bytes and longs,
> reshaping and flattening the state, and performing bitwise operations required for the Keccak-f algorithm.
> <p>
> It's important to note that the code is specific to the SHA-3-256 variant, where the hash size is 256 bits.
> The constants and parameters used in the code are tailored for this specific variant.

---

## rancore.math

Mathematical Algorithms & Functions:

- Euklid
- Euler
- Facorials
- Fibonacci
- Fractions
- Harmonic Series
- Pi
- Prime Numbers

---

## rancore.path

A few pathfinding algorithms:

- A* Pathfinding
- Bellman Ford Algorithm
- Dijkstra's Algorithm

---

## rancore.playground

Well...

---

## rancore.space

Here I implemented a class that retrieves live data from the ISS to output in the console.
I love everything space and sci-fi related, so there's that :)
