package com.crowdfire.calc;

import android.util.Log;

import java.util.Stack;

public class ExpressionEvaluator {
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
        }
        Log.i("TAG", values.toString());
        return 0.0;
    }
}
