package org.rancore.math;

import java.util.*;

public class MathParser {
    private static final String OPERATORS = "+-*/^";
    private static final String FUNCTIONS = "sin|cos|tan|log|exp";

    public static double evaluate(String expression) {
        Queue<String> tokens = tokenize(expression);
        Node root = parse(tokens);
        return evaluate(root);
    }

    private static Queue<String> tokenize(String expression) {
        Queue<String> tokens = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for (char c : expression.toCharArray()) {
            if (Character.isWhitespace(c)) {
                continue;
            }
            if (Character.isDigit(c) || c == '.') {
                sb.append(c);
            } else {
                if (!sb.isEmpty()) {
                    tokens.offer(sb.toString());
                    sb.setLength(0);
                }
                tokens.offer(String.valueOf(c));
            }
        }
        if (!sb.isEmpty()) {
            tokens.offer(sb.toString());
        }
        return tokens;
    }

    private static Node parse(Queue<String> tokens) {
        Stack<Node> operands = new Stack<>();
        Stack<String> operators = new Stack<>();

        while (!tokens.isEmpty()) {
            String token = tokens.poll();
            if (isNumber(token)) {
                operands.push(new NumberNode(Double.parseDouble(token)));
            } else if (isVariable(token)) {
                operands.push(new VariableNode(token));
            } else if (isFunction(token)) {
                operators.push(token);
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.peek().equals("(")) {
                    Node right = operands.pop();
                    Node left = operands.pop();
                    operands.push(new OperatorNode(operators.pop(), left, right));
                }
                operators.pop(); // Remove "("
                if (!operators.isEmpty() && isFunction(operators.peek())) {
                    operands.push(new FunctionNode(operators.pop(), operands.pop()));
                }
            } else if (isOperator(token)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token)) {
                    Node right = operands.pop();
                    Node left = operands.pop();
                    operands.push(new OperatorNode(operators.pop(), left, right));
                }
                operators.push(token);
            }
        }

        while (!operators.isEmpty()) {
            Node right = operands.pop();
            Node left = operands.pop();
            operands.push(new OperatorNode(operators.pop(), left, right));
        }

        return operands.pop();
    }

    private static double evaluate(Node node) {
        if (node instanceof NumberNode) {
            return ((NumberNode) node).value;
        } else if (node instanceof VariableNode) {
            // For simplicity, we'll assume x = 2 here. In a real implementation, you'd want to pass in variable values.
            return 2;
        } else if (node instanceof OperatorNode op) {
            double left = evaluate(op.left);
            double right = evaluate(op.right);
            return switch (op.operator) {
                case "+" -> left + right;
                case "-" -> left - right;
                case "*" -> left * right;
                case "/" -> left / right;
                case "^" -> Math.pow(left, right);
                default -> throw new IllegalArgumentException("Unknown operator: " + op.operator);
            };
        } else if (node instanceof FunctionNode func) {
            double arg = evaluate(func.argument);
            return switch (func.name) {
                case "sin" -> Math.sin(arg);
                case "cos" -> Math.cos(arg);
                case "tan" -> Math.tan(arg);
                case "log" -> Math.log(arg);
                case "exp" -> Math.exp(arg);
                default -> throw new IllegalArgumentException("Unknown function: " + func.name);
            };
        }
        throw new IllegalArgumentException("Unknown node type");
    }

    private static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isVariable(String token) {
        return token.matches("[a-zA-Z]");
    }

    private static boolean isOperator(String token) {
        return OPERATORS.contains(token);
    }

    private static boolean isFunction(String token) {
        return token.matches(FUNCTIONS);
    }

    private static int precedence(String operator) {
        return switch (operator) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "^" -> 3;
            default -> 0;
        };
    }

    private static abstract class Node {}

    private static class NumberNode extends Node {
        double value;
        NumberNode(double value) { this.value = value; }
    }

    private static class VariableNode extends Node {
        String name;
        VariableNode(String name) { this.name = name; }
    }

    private static class OperatorNode extends Node {
        String operator;
        Node left, right;
        OperatorNode(String operator, Node left, Node right) {
            this.operator = operator;
            this.left = left;
            this.right = right;
        }
    }

    private static class FunctionNode extends Node {
        String name;
        Node argument;
        FunctionNode(String name, Node argument) {
            this.name = name;
            this.argument = argument;
        }
    }

    public static void main(String[] args) {
        String expression = "(sin(x)^(2*x-1))+4*x";
        double result = evaluate(expression);
        System.out.println("Result: " + result);
    }
}