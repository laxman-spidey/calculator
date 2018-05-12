package com.crowdfire.calc;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class ButtonLayout {

    Button buttons[] = new Button[17];

    public void instantiate(Activity view) {
        int i = 0;
        //operands
        buttons[i++] = view.findViewById(R.id.buttonZero);
        buttons[i++] = view.findViewById(R.id.buttonOne);
        buttons[i++] = view.findViewById(R.id.buttonTwo);
        buttons[i++] = view.findViewById(R.id.buttonThree);
        buttons[i++] = view.findViewById(R.id.buttonFour);
        buttons[i++] = view.findViewById(R.id.buttonFive);
        buttons[i++] = view.findViewById(R.id.buttonSix);
        buttons[i++] = view.findViewById(R.id.buttonSeven);
        buttons[i++] = view.findViewById(R.id.buttonEight);
        buttons[i++] = view.findViewById(R.id.buttonNine);
        buttons[i++] = view.findViewById(R.id.buttonDot);

        //operators
        buttons[i++] = view.findViewById(R.id.buttonPlus);
        buttons[i++] = view.findViewById(R.id.buttonMinus);
        buttons[i++] = view.findViewById(R.id.buttonAsterix);
        buttons[i++] = view.findViewById(R.id.buttonDivide);
        buttons[i++] = view.findViewById(R.id.buttonPercentile);
        buttons[i++] = view.findViewById(R.id.buttonPower);

    }

    public void addOnClickListener(ButtonLayout.OnClickListener listener) {
        View.OnClickListener viewClickedListener = (view) -> listener.onClick(((Button) view).getText().toString());
        for (Button button : buttons
                ) {
            button.setOnClickListener(viewClickedListener);

        }
    }

    public static interface OnClickListener {
        public void onClick(String value);
    }
}
