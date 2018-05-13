package com.crowdfire.calc;


import java.util.Stack;

public class ExpressionEvaluator {

    public static double evaluate(String expression) {

        char[] tokens = expression.toCharArray();

        // Stack for numbers: 'values'
        Stack<Double> values = new Stack<>();

        // Stack for Operators: 'ops'
        Stack<Character> ops = new Stack<>();
        char previousToken = ' ';
        boolean isUnaryOperatorFound = false;
        char unaryOperator = '+';


        for (int i = 0; i < tokens.length; i++) {

            if (i > 0) // Preventing IndexOutOfBounds for first token
                previousToken = tokens[i - 1];
            // Current token is a whitespace, skip it
            if (tokens[i] == ' ')
                continue;

            // Current token is a number, push it to stack for numbers
            if ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.') {
                StringBuffer sbuf = new StringBuffer();
                // There may be more than one digits in number
                if (isUnaryOperatorFound) {
                    sbuf.append(unaryOperator);
                    isUnaryOperatorFound = false;
                }
                while (i < tokens.length && ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.')) {
                    sbuf.append(tokens[i++]);
                }
                i--;
                values.push(Double.parseDouble(sbuf.toString()));
            }

            // Current token is an opening brace, push it to 'ops'
            else if (tokens[i] == '(')
                ops.push(tokens[i]);

                // Closing brace encountered, solve entire brace
            else if (tokens[i] == ')') {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }

            // Current token is an operator.
            else if (isOperator(tokens[i])) {
                if (isUnaryOperator(tokens[i], previousToken)) {
                    isUnaryOperatorFound = true;
                    unaryOperator = tokens[i];
                    continue;
                }

                // While top of 'ops' has same or greater precedence to current
                // token, which is an operator. Apply operator on top of 'ops'
                // to top two elements in values stack
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));

                // Push current token to 'ops'.
                ops.push(tokens[i]);
            }
        }

        // Entire expression has been parsed at this point, apply remaining
        // ops to remaining values
        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));

        // Top of 'values' contains result, return it
        return values.pop();
    }

    private static final String supportedOperators = "+-*/%^";

    private static boolean isOperator(char token) {
        if (supportedOperators.indexOf(token) == -1)
            return false;
        return true;
    }

    private static boolean isUnaryOperator(char token, char previousToken) {
        String unaryOperators = "+-";

        if (unaryOperators.indexOf(token) != -1) {
            if (previousToken == ' ') {
                return true;
            }
            if (isOperator(previousToken)) {
                return true;
            }
        }
        return false;
    }

    // Returns true if 'op2' has higher or same precedence as 'op1',
    // otherwise returns false.
    public static boolean hasPrecedence(char op1, char op2) {
        // Highest priority is given to ^
        if ((op1 == '^') && (op2 == '*' || op2 == '/' || op2 == '%' || op2 == '+' || op2 == '-'))
            return false;
        if ((op1 == '*' || op1 == '/' || op1 == '^' || op1 == '%') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    // A utility method to apply an operator 'op' on operands 'a'
    // and 'b'. Return the result.
    public static double applyOp(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new
                            UnsupportedOperationException("Cannot divide by zero");
                return a / b;
            case '^':
                return Math.pow(a, b);
            case '%':
                if (b == 0)
                    throw new
                            UnsupportedOperationException("Cannot modulus by zero");
                return a % b;
        }
        return 0;
    }
}
