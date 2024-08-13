package org.rancore.math;

import java.util.Scanner;

/**
 * Represents a complex number.
 * A complex number consists of a real part and an imaginary part.
 */
public class ComplexNumber {
    private double real;
    private double imaginary;

    /**
     * <p>The entry point of the ComplexNumber program.</p>
     *
     * <p>This method can run in two modes:</p>
     * <ol>
     *   <li>Demo mode: Demonstrates operations on complex numbers using hardcoded values.</li>
     *   <li>Interactive mode: Allows users to perform operations on complex numbers specified via command-line arguments.</li>
     * </ol>
     *
     * <p>Usage:</p>
     * <ul>
     *   <li>For demo mode: {@code java ComplexNumber} or {@code java ComplexNumber -demo}</li>
     *   <li>For interactive mode: {@code java ComplexNumber <real1> <imag1> <real2> <imag2>}</li>
     * </ul>
     *
     * <p>In interactive mode, the program accepts four command-line arguments representing two complex numbers:
     * {@code <real1> <imag1>} for the first complex number and {@code <real2> <imag2>} for the second.</p>
     *
     * @param args Command-line arguments.
     *             If empty or "-demo", runs in demo mode.
     *             If 4 arguments are provided, they should be numbers representing two complex numbers.
     *
     * @throws NumberFormatException If the provided arguments in interactive mode cannot be parsed as doubles.
     */
    public static void main(String[] args) {
        ComplexNumber c1, c2;

        if (args.length == 0 || args[0].equals("-demo")) {
            // Run demo with hardcoded values
            c1 = new ComplexNumber(3, 4);
            c2 = new ComplexNumber(1, -2);
            runDemo(c1, c2);
        } else if (args.length == 4) {
            // Use command-line arguments
            try {
                double real1 = Double.parseDouble(args[0]);
                double imag1 = Double.parseDouble(args[1]);
                double real2 = Double.parseDouble(args[2]);
                double imag2 = Double.parseDouble(args[3]);

                c1 = new ComplexNumber(real1, imag1);
                c2 = new ComplexNumber(real2, imag2);
                runInteractive(c1, c2);
            } catch (NumberFormatException e) {
                System.out.println("Error: All arguments must be valid numbers.");
                System.exit(1);
            }
        } else {
            System.out.println("Usage: java ComplexNumber [-demo] or java ComplexNumber <real1> <imag1> <real2> <imag2>");
            System.exit(1);
        }
    }

    private static void runDemo(ComplexNumber c1, ComplexNumber c2) {
        System.out.println("Running demo with hardcoded values:");
        System.out.println("Complex number 1: " + c1);
        System.out.println("Complex number 2: " + c2);
        System.out.println("Addition: " + c1.add(c2));
        System.out.println("Subtraction: " + c1.subtract(c2));
        System.out.println("Multiplication: " + c1.multiply(c2));
        System.out.println("Magnitude of c1: " + c1.magnitude());
        System.out.println("Conjugate of c2: " + c2.conjugate());

        ComplexNumber result = c1.add(c2).multiply(c1.conjugate());
        System.out.println("(c1 + c2) * conjugate(c1): " + result);
    }

    private static void runInteractive(ComplexNumber c1, ComplexNumber c2) {
        System.out.println("Complex number 1: " + c1);
        System.out.println("Complex number 2: " + c2);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Magnitude of c1");
            System.out.println("5. Conjugate of c2");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Addition: " + c1.add(c2));
                    break;
                case 2:
                    System.out.println("Subtraction: " + c1.subtract(c2));
                    break;
                case 3:
                    System.out.println("Multiplication: " + c1.multiply(c2));
                    break;
                case 4:
                    System.out.println("Magnitude of c1: " + c1.magnitude());
                    break;
                case 5:
                    System.out.println("Conjugate of c2: " + c2.conjugate());
                    break;
                case 6:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }

    /**
     * Creates a new complex number.
     *
     * @param real The real part of the complex number.
     * @param imaginary The imaginary part of the complex number.
     */
    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * Returns the real part of the complex number.
     *
     * @return The real part as a double.
     */
    public double getReal() {
        return real;
    }

    /**
     * Returns the imaginary part of the complex number.
     *
     * @return The imaginary part as a double.
     */
    public double getImaginary() {
        return imaginary;
    }


    /**
     * Adds this complex number to another.
     *
     * @param other The other complex number to be added.
     * @return A new ComplexNumber representing the result of the addition.
     */
    public ComplexNumber add(ComplexNumber other) {
        return new ComplexNumber(this.real + other.real, this.imaginary + other.imaginary);
    }


    /**
     * Subtracts another complex number from this one.
     *
     * @param other The complex number to be subtracted.
     * @return A new ComplexNumber representing the result of the subtraction.
     */
    public ComplexNumber subtract(ComplexNumber other) {
        return new ComplexNumber(this.real - other.real, this.imaginary - other.imaginary);
    }

    /**
     * Multiplies this complex number by another.
     *
     * @param other The complex number to multiply by.
     * @return A new ComplexNumber representing the result of the multiplication.
     */
    public ComplexNumber multiply(ComplexNumber other) {
        double newReal = this.real * other.real - this.imaginary * other.imaginary;
        double newImaginary = this.real * other.imaginary + this.imaginary * other.real;
        return new ComplexNumber(newReal, newImaginary);
    }

    /**
     * Calculates the magnitude (absolute value) of this complex number.
     * The magnitude is defined as the square root of (real² + imaginary²).
     *
     * @return The magnitude of the complex number as a double.
     */
    public double magnitude() {
        return Math.sqrt(real * real + imaginary * imaginary);
    }

    /**
     * Generates the complex conjugate of this number.
     * The complex conjugate has the same real part but a negated imaginary part.
     *
     * @return A new ComplexNumber representing the complex conjugate.
     */
    public ComplexNumber conjugate() {
        return new ComplexNumber(this.real, -this.imaginary);
    }

    /**
     * Returns a string representation of the complex number.
     * The representation is in the form "a + bi" or "a - bi".
     *
     * @return A string representation of the complex number.
     */
    @Override
    public String toString() {
        if (imaginary >= 0) {
            return real + " + " + imaginary + "i";
        } else {
            return real + " - " + (-imaginary) + "i";
        }
    }
}