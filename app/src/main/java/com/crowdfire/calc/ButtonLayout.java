package com.crowdfire.calc;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class ButtonLayout {

    Button numberButtons[] = new Button[11];
    Button operatorButtons[] = new Button[6];


    public void instantiate(Activity view) {
        int numberButtonIndex = 0;
        int operatorButtonIndex = 0;

        //operands
        numberButtons[numberButtonIndex++] = view.findViewById(R.id.buttonZero);
        numberButtons[numberButtonIndex++] = view.findViewById(R.id.buttonOne);
        numberButtons[numberButtonIndex++] = view.findViewById(R.id.buttonTwo);
        numberButtons[numberButtonIndex++] = view.findViewById(R.id.buttonThree);
        numberButtons[numberButtonIndex++] = view.findViewById(R.id.buttonFour);
        numberButtons[numberButtonIndex++] = view.findViewById(R.id.buttonFive);
        numberButtons[numberButtonIndex++] = view.findViewById(R.id.buttonSix);
        numberButtons[numberButtonIndex++] = view.findViewById(R.id.buttonSeven);
        numberButtons[numberButtonIndex++] = view.findViewById(R.id.buttonEight);
        numberButtons[numberButtonIndex++] = view.findViewById(R.id.buttonNine);
        numberButtons[numberButtonIndex++] = view.findViewById(R.id.buttonDot);

        //operators
        operatorButtons[operatorButtonIndex++] = view.findViewById(R.id.buttonPlus);
        operatorButtons[operatorButtonIndex++] = view.findViewById(R.id.buttonMinus);
        operatorButtons[operatorButtonIndex++] = view.findViewById(R.id.buttonAsterix);
        operatorButtons[operatorButtonIndex++] = view.findViewById(R.id.buttonDivide);
        operatorButtons[operatorButtonIndex++] = view.findViewById(R.id.buttonPercentile);
        operatorButtons[operatorButtonIndex++] = view.findViewById(R.id.buttonPower);

    }

    public void addOnClickListener(ButtonLayout.OnClickListener listener) {
        View.OnClickListener numberClickedListener = (view) -> {
            if (State.evaluated) {
                State.Expression.reset();
                State.evaluated = false;
            }
            listener.onClick(((Button) view).getText().toString());
        };

        for (Button button : numberButtons) {
            button.setOnClickListener(numberClickedListener);
        }


        View.OnClickListener operandClickedListener = (view) -> {
            State.evaluated = false;
            listener.onClick(((Button) view).getText().toString());
        };
        for (Button button : operatorButtons) {
            button.setOnClickListener(operandClickedListener);
        }





    }

    public static interface OnClickListener {
        public void onClick(String value);
    }
}
