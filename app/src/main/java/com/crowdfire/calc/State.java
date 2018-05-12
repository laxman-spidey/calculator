package com.crowdfire.calc;

import java.util.ArrayList;
import java.util.HashMap;

public class State {
    public static String result;
    public static ArrayList<HashMap<String, String>> history;

    public static final String ZERO = "0";

    public static class Expression {
        private static String expression;
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
            return expression.concat(op);
        }

        public static String evaluate() {
            return "";
        }
    }

}
