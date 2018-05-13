package com.crowdfire.calc;

import java.util.ArrayList;
import java.util.HashMap;

public class State {
    public static ArrayList<HashMap<String, String>> history;
    public static boolean evaluated = false;
    public static String symbols = ".+-*/^%";

    public static final String ZERO = "0";

    public static class Expression {
        private static String expression = "0";

        public static String reset() {
            expression = ZERO;
            return ZERO;
        }

        public static String clearAtEnd() {
            if (expression.length() == 0 || expression.equals(ZERO) || expression.length() == 1) {
                return reset();
            }
            expression = expression.substring(0, expression.length() - 1);
            return expression;
        }

        public static void set(String newExpression) {
            expression = newExpression;
        }

        public static String get() {
            return expression;
        }

        public static String addOp(String op) {
            if (expression.equals(ZERO)) {
                if (!symbols.contains(op)) { // If the character is number then replace zero with number
                    return expression = op;
                }
            }
            return expression = expression + op;
        }

        public static String evaluate() {
            evaluated = true;
            return "";
        }
    }

    public void createHistoryRecord(String expression, String value)
    {

    }

}
