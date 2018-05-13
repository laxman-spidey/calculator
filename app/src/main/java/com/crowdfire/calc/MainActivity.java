package com.crowdfire.calc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "calc.TAG";
    public static final String ZERO = "0";

    EditText expressionView;
    String expression = "";

    Button equalsButton;
    Button clearButton;
    Button ceButton;

    ButtonLayout buttonLayout = new ButtonLayout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiateViews();
        setupClickListener();
    }


    private void setupClickListener() {
        clearButton.setOnClickListener((view) -> clearExpression());
        ceButton.setOnClickListener((view) -> clearAtEnd());
        equalsButton.setOnClickListener((view) -> evaluate());
        buttonLayout.addOnClickListener((string) -> addToExpression(string));
    }


    private void addToExpression(String value) {
        setExpression(State.Expression.addOp(value));
    }

    private void clearExpression() {
        setExpression(State.Expression.reset());
    }

    private void clearAtEnd() {

        setExpression(State.Expression.clearAtEnd());
    }

    private void setExpression(String expression) {
        expressionView.setText(expression);
        expressionView.setSelection(expression.length());
    }

    private void initiateViews() {
        equalsButton = findViewById(R.id.buttonEquals);
        clearButton = findViewById(R.id.buttonCL);
        ceButton = findViewById(R.id.buttonCE);
        expressionView = findViewById(R.id.expressionView);
        expressionView.setSelection(1);
        buttonLayout.instantiate(this);
    }

    private void evaluate() {
        State.Expression.set(expressionView.getText().toString());
        Log.i(TAG, "final result " + State.Expression.get());
        Double result;
        try {
            result = ExpressionEvaluator.evaluate(State.Expression.get());
            if (result % 1 == 0) {
                State.Expression.set("" + ((int) Math.floor(result)));
            } else {
                State.Expression.set("" + result);
            }
            State.evaluated = true;
            setExpression(State.Expression.get());
        } catch (UnsupportedOperationException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Invalid expression", Toast.LENGTH_SHORT).show();
        }


    }

}
