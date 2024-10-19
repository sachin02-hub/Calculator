package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView display;
    private String currentDisplay = "";
    private String operator = "";
    private double firstOperand = 0;
    private boolean isNewInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        setNumberButtonListeners();
        setOperatorButtonListeners();
    }

    private void setNumberButtonListeners() {
        int[] numberButtonIds = {R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
                R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.btn_dot};

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if (isNewInput) {
                    currentDisplay = "";
                    isNewInput = false;
                }
                currentDisplay += button.getText().toString();
                display.setText(currentDisplay);
            }
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorButtonListeners() {
        findViewById(R.id.btn_plus).setOnClickListener(v -> handleOperator("+"));
        findViewById(R.id.btn_minus).setOnClickListener(v -> handleOperator("-"));
        findViewById(R.id.btn_multiply).setOnClickListener(v -> handleOperator("*"));
        findViewById(R.id.btn_divide).setOnClickListener(v -> handleOperator("/"));
        findViewById(R.id.btn_equals).setOnClickListener(v -> calculateResult());
        findViewById(R.id.btn_clear).setOnClickListener(v -> clearDisplay());
        findViewById(R.id.btn_backspace).setOnClickListener(v -> backspace());
    }

    private void handleOperator(String op) {
        if (!currentDisplay.isEmpty()) {
            firstOperand = Double.parseDouble(currentDisplay);
            operator = op;
            isNewInput = true;
        }
    }

    private void calculateResult() {
        if (!currentDisplay.isEmpty()) {
            double secondOperand = Double.parseDouble(currentDisplay);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "*":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    result = firstOperand / secondOperand;
                    break;
            }

            display.setText(String.valueOf(result));
            isNewInput = true;
        }
    }

    private void clearDisplay() {
        currentDisplay = "0";
        display.setText(currentDisplay);
        firstOperand = 0;
        operator = "";
        isNewInput = true;
    }

    private void backspace() {
        if (!currentDisplay.isEmpty()) {
            currentDisplay = currentDisplay.substring(0, currentDisplay.length() - 1);
            if (currentDisplay.isEmpty()) {
                currentDisplay = "0";
            }
            display.setText(currentDisplay);
        }
    }
}
