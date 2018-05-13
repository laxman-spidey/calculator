package com.crowdfire.calc;

import android.util.Log;

import java.util.Stack;

public class ExpressionEvaluator {

    private static final String supportedOperators = "+-*/%^";


    public static double evaluate(String expression) {
        char[] tokens = expression.toCharArray();
        // Stack for numbers: 'values'
        Stack<Double> values = new Stack();

        // Stack for Operators: 'operators'
        Stack<Character> ops = new Stack();

        for (int i = 0; i < tokens.length; i++) {

            // Current token is a whitespace, skip it
            if (tokens[i] == ' ')
                continue;

            // Current token is a number, push it to stack for numbers
            if ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.') {
                StringBuffer sbuf = new StringBuffer();
                // There may be more than one digits in number
                while (i < tokens.length && ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.')) {
                    sbuf.append(tokens[i++]);
                }
                i--;
                values.push(Double.parseDouble(sbuf.toString()));
            }
            // Current token is an operator.
            else if (isOperator(tokens[i])) {
                while (!ops.empty())
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

    private static boolean isOperator(char token) {
        if (supportedOperators.indexOf(token) == -1)
            return false;
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
